package com.fx.shop.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
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
    private RedisUtil redisUtil;
    /**
     * 创建订单
     * @param req
     * @return
     */
    @Override
    public String create(OrderCreateReq req) {
        Long userId=req.getUserId();
        String orderNo=OrderNoUtil.createOrderNo();
        CustomerUser customerUser=customerUserService.getById(userId);
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
        orderLogistics.setFreightAmount(freightAmount);
        orderLogistics.setOrderNo(orderNo);
        orderLogistics.setCreateTime(new Date());
        logisticsService.save(orderLogistics);
        Long  logisticsId=orderLogistics.getId();
        //TODO 订单重量
        BigDecimal totalWeight=new BigDecimal("0");
        //TODO 订单总金额
        BigDecimal productTotalAmount=new BigDecimal("0");
        List<OrderCreateReq.Product> productDetail=req.getProductDetail();
        for(OrderCreateReq.Product product:productDetail){
            Long productId=product.getProductId();
            ProductInfo productInfo=prodService.getById(productId);
            BigDecimal weight=productInfo.getWeight();
            totalWeight=totalWeight.add(weight);
            BigDecimal price=productInfo.getPrice();
            BigDecimal num=product.getNum();
            productTotalAmount=NumberUtil.add(productTotalAmount,NumberUtil.mul(price,num));

        }
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setOrderNo(orderNo);
        orderInfo.setUserId(userId);
        orderInfo.setOrderStatus(StateUtil.orderStatus.os0);
        orderInfo.setTotalWeight(totalWeight);
        orderInfo.setFreightAmount(freightAmount);
        orderInfo.setProductTotalAmount(productTotalAmount);
        orderInfo.setTotalAmount(NumberUtil.add(productTotalAmount,freightAmount));
        orderInfo.setDistributType(StateUtil.distributType.dt2);
        orderInfo.setAddressId(addressId);
        orderInfo.setLogisticsId(logisticsId);
        orderInfo.setRemark(req.getRemark());
        orderInfo.setCreateTime(new Date());
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
