package com.fx.shop.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fx.shop.dto.PageResp;
import com.fx.shop.dto.exFee.req.QueryReq;
import com.fx.shop.dto.exFee.resp.Resp;
import com.fx.shop.dto.order.req.OrderCreateReq;
import com.fx.shop.dto.order.req.OrderEditReq;
import com.fx.shop.dto.order.req.OrderQueryReq;
import com.fx.shop.dto.order.resp.OrderResp;
import com.fx.shop.entity.*;
import com.fx.shop.mapper.OrderInfoMapper;
import com.fx.shop.service.*;

import com.fx.shop.util.order.OrderNoUtil;
import com.fx.shop.util.redis.RedisUtil;
import com.fx.shop.util.state.StateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Slf4j
@Service
@Transactional
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {
    @Autowired
    private CustomerUserService customerUserService;
    @Autowired
    private UserShippingAddressService addressService;
    @Autowired
    private ProductInfoService prodService;
    @Autowired
    private ExpressFeeService feeService;
    @Autowired
    private OrderLogisticsService logisticsService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private InfoService infoService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 创建订单
     * @param req
     * @return
     */
    @Override
    public String create(OrderCreateReq req) {
        Long customerId=req.getCustomerId();
        String orderNo=OrderNoUtil.createOrderNo();
        CustomerUser customerUser=customerUserService.getById(customerId);
        if(null==customerUser){
            throw new    RuntimeException("当前用户信息有误，请联系管理员!");
        }
        Long addressId=req.getAddressId();
        UserShippingAddress address=addressService.getById(addressId);
        if(null==address){
            throw new    RuntimeException("收货地址信息有误，请联系管理员!");
        }
        //TODO 快递费
        BigDecimal freightAmount=new BigDecimal("0");
        QueryReq expReq=new QueryReq();
        expReq.setName(address.getProvinceCode());
        PageResp<Resp> pageResp= feeService.query(expReq);
        List<Resp> resps=pageResp.getRecords();
        if(null==resps||resps.size()==0){
            throw new    RuntimeException(address.getProvince()+"地区不支持快递配送，请联系管理员!");
        }
        freightAmount=resps.get(0).getExpress();
        //TODO 物流信息
        OrderLogistics orderLogistics=new OrderLogistics();
        BeanUtil.copyProperties(address,orderLogistics);
        orderLogistics.setId(null);
        orderLogistics.setFreightAmount(freightAmount);
        orderLogistics.setOrderNo(orderNo);
        orderLogistics.setCreateTime(new Date());
        logisticsService.save(orderLogistics);
        Long  logisticsId=orderLogistics.getId();
        //TODO 订单重量
        BigDecimal totalWeight=new BigDecimal("0");
        //TODO 商品总金额
        BigDecimal productTotalAmount=new BigDecimal("0");
        //TODO 商品中可以用积分抵扣的金额
        BigDecimal canProductTotalAmount=new BigDecimal("0");
        List<OrderCreateReq.Product> productDetail=req.getProductDetail();
        for(OrderCreateReq.Product product:productDetail){
            Long productId=product.getProductId();
            ProductInfo productInfo=prodService.getById(productId);
            BigDecimal weight=productInfo.getWeight();
            BigDecimal price=productInfo.getPrice();
            Integer num=product.getNum();
            BigDecimal subTotalAmount=NumberUtil.mul(price,num);
            productTotalAmount=NumberUtil.add(productTotalAmount,subTotalAmount);
            BigDecimal subTotalWeight=NumberUtil.mul(weight,num);
            totalWeight=totalWeight.add(subTotalWeight);
            //TODO 订单详情
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setOrderNo(orderNo);
            orderDetail.setPrice(productInfo.getPrice());
            orderDetail.setProductCount(num);
            orderDetail.setProductId(productId);
            orderDetail.setMinImg(productInfo.getMinImg());
            orderDetail.setProductName(productInfo.getName());
            orderDetail.setSubTotalAmount(subTotalAmount);
            orderDetail.setSubTotalWeight(subTotalWeight);
            orderDetailService.save(orderDetail);
            //TODO 是否可以积分抵扣
            if(1==productInfo.getIntegralStatus()){
                canProductTotalAmount=canProductTotalAmount.add(subTotalAmount);
            }
        }
        List<Info> shoInfos=infoService.list();
        //TODO 使用积分数
        Integer integralCount=req.getIntegralCount();
        //TODO 积分优惠
        BigDecimal integralAmount=new BigDecimal("0");
        if(null!=integralCount) {
            //TODO 用户积分
            Integer userIntegral = customerUser.getIntegral();
            if (userIntegral < integralCount) {
                throw new RuntimeException("当前积分["+userIntegral+"]，使用积分["+integralCount+"],积分不足请取消订单重新购买");
            }
            Integer  integralRatio=100;
            if(null!=shoInfos||shoInfos.size()>0){
                integralRatio=shoInfos.get(0).getIntegralRatio();
            }
            integralAmount=new BigDecimal(integralCount/integralRatio);
            //TODO  最多消费积分数
            Integer productIntegralCount=NumberUtil.mul(integralRatio,canProductTotalAmount).intValue();
            if(integralCount>productIntegralCount){
                throw new RuntimeException("最多使用积分["+productIntegralCount+"]，使用积分["+integralCount+"],请取消订单重新购买");
            }
        }
        //TODO 优惠券ids
        BigDecimal couponAmount=new BigDecimal("0");
        String couponDetail="";
        String  couponIds=req.getCouponIds();
        if(StringUtils.isNotBlank(couponIds)){
            String[] couponArray=couponIds.split(",");
            QueryWrapper<Coupon> couponQueryWrapper=new QueryWrapper<>();
            couponQueryWrapper.select("id,price,startTime,endTime");
            couponQueryWrapper.in("id",couponArray);
            couponQueryWrapper.gt("end_time",new Date());
            List<Coupon> couponList=couponService.list(couponQueryWrapper);
            if(null==couponList||couponArray.length!=couponList.size()){
                throw new RuntimeException("勾选的优惠券有误，请联系管理员");
            }
            for(Coupon coupon:couponList){
                couponAmount=couponAmount.add(coupon.getPrice());
            }
            couponDetail= JSONUtil.toJsonStr(couponList);
        }
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setOrderNo(orderNo);
        orderInfo.setIntegralAmount(integralAmount);
        orderInfo.setIntegralCount(integralCount);
        orderInfo.setCouponAmount(couponAmount);
        orderInfo.setCouponDetail(couponDetail);
        orderInfo.setUserId(customerId);
        orderInfo.setOrderStatus(StateUtil.orderStatus.os0);
        orderInfo.setTotalWeight(totalWeight);
        orderInfo.setFreightAmount(freightAmount);
        orderInfo.setProductTotalAmount(productTotalAmount);
        //TODO 总费用
        BigDecimal totalAmount=NumberUtil.add(productTotalAmount,freightAmount);
        orderInfo.setTotalAmount(totalAmount);
        //TODO 支付金额金额
        BigDecimal payAmount=totalAmount.subtract(integralAmount).subtract(couponAmount);
        orderInfo.setPayAmount(payAmount.floatValue()>0?payAmount:new BigDecimal("0"));
        orderInfo.setIntegralCount(integralCount);
        orderInfo.setIntegralAmount(integralAmount);
        orderInfo.setDistributType(StateUtil.distributType.dt2);
        orderInfo.setAddressId(addressId);
        orderInfo.setLogisticsId(logisticsId);
        orderInfo.setRemark(req.getRemark());
        orderInfo.setCreateTime(new Date());
        if(null!=shoInfos&&shoInfos.size()>0){
//            orderInfo
        }

        this.save(orderInfo);
        //TODO 商品购买记录

        return orderNo;
    }

    /**
     * 查询订单
     * @param req
     * @return
     */
    @Override
    public PageResp<OrderResp> query(OrderQueryReq req) {
//        redisUtil.set("1234",1234);
        IPage<OrderInfo> page=new Page(req.getCurrent(),req.getSize());
        QueryWrapper<OrderInfo> queryWrapper=new QueryWrapper<>();
        Long id=req.getId();
        if(null!=id){
            queryWrapper.eq("id",id);
        }
        String orderNo=req.getOrderNo();
        if(StringUtils.isNotBlank(orderNo)){
            queryWrapper.eq("order_no",orderNo);
        }
        Integer orderStatus=req.getOrderStatus();
        if(null!=orderStatus){
            queryWrapper.eq("order_status",orderStatus);
        }
        String payTimeStart=req.getPayTimeStart();
        if(StringUtils.isNotBlank(payTimeStart)){
            queryWrapper.gt("pay_time",payTimeStart);
        }
        String payTimeEnd=req.getPayTimeEnd();
        if(StringUtils.isNotBlank(payTimeEnd)){
            queryWrapper.le("pay_time",payTimeEnd+" 23:59:59");
        }
        Long userId=req.getUserId();
        if(null!=userId){
            queryWrapper.eq("user_id",userId);
        }
        page=this.page(page,queryWrapper);
        PageResp<OrderResp> pageResp=PageResp.convertEntityToDto(page,OrderResp.class);
        return pageResp;
    }

    /**
     * 修改订单
     * @param req
     */
    @Override
    public void edit(OrderEditReq req) {
        Long id=req.getId();
        OrderInfo orderInfo=this.getById(id);
        if(null==orderInfo){
            throw new    RuntimeException("错误的订单信息,请联系管理员!");
        }
        OrderInfo info=new OrderInfo();
        BeanUtil.copyProperties(req,info);
        this.updateById(info);
    }
}
