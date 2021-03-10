package com.fx.shop.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fx.shop.dto.coupon.req.CouponAddReq;
import com.fx.shop.dto.coupon.req.CouponEditReq;
import com.fx.shop.dto.coupon.req.CouponReq;
import com.fx.shop.dto.coupon.resp.CouponResp;
import com.fx.shop.entity.Coupon;
import com.fx.shop.entity.CustomerCouponRel;
import com.fx.shop.mapper.CouponMapper;
import com.fx.shop.service.CouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fx.shop.service.CustomerCouponRelService;
import com.sineyun.commons.base.dto.response.PageResp;
import com.sineyun.commons.core.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 店铺优惠券 服务实现类
 * </p>
 *
 * @author fx
 * @since 2021-02-04
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {
    @Autowired
    private CustomerCouponRelService cCRelService;
    /**
     * 查询优惠券
     * @param req
     * @return
     */
    @Override
    public PageResp<CouponResp> query(CouponReq req) {
        IPage<Coupon> page=new Page<>(req.getCurrent(),req.getSize());
        QueryWrapper<Coupon> queryWrapper=new QueryWrapper<>();
        Long id=req.getId();
        if(null!=id){
            queryWrapper.eq("id",id);
        }
        String name=req.getName();
        if(StringUtils.isNotBlank(name)){
            queryWrapper.like("name",name);
        }
        Integer state=req.getState();
        if(null!=state){
            queryWrapper.eq("state",state);
        }
        Integer type=req.getType();
        if(null!=type){
            queryWrapper.eq("type",type);
        }
        String startTime=req.getStartTime();
        if(StringUtils.isNotBlank(startTime)){
            queryWrapper.gt("start_time",startTime);
        }
        String endTime=req.getEndTime();
        if(StringUtils.isNotBlank(endTime)){
            queryWrapper.le("end_time",endTime+" 23:59:59");
        }
        //TODO 获取自己的优惠券
        Long customerId=req.getCustomerId();
        if(null!=customerId){
            QueryWrapper<CustomerCouponRel> cCQueryWrapper=new QueryWrapper<>();
            cCQueryWrapper.eq("customerId",customerId);
            List<CustomerCouponRel>  couponRelList=cCRelService.list();
            if(null==couponRelList||couponRelList.size()==0){
                return null;
            }
            Set<Long> couponIds=new HashSet<>();
            for(CustomerCouponRel customerCouponRel:couponRelList){
                couponIds.add(customerCouponRel.getCouponId());
            }
            queryWrapper.in("id",couponIds);
        }
        page=this.page(page,queryWrapper);
        PageResp<CouponResp> pageResp=PageResp.convertEntityToDto(page,CouponResp.class);
        return pageResp;
    }

    /**
     * 增加店铺优惠券
     * @param req
     */
    @Override
    public void add(CouponAddReq req) {
        Coupon coupon=new Coupon();
        BeanUtil.copyProperties(req,coupon);
        this.save(coupon);
    }

    /**
     * 修改店铺优惠券
     * @param req
     */
    @Override
    public void edit(CouponEditReq req) {
        Long id=req.getId();
        Coupon coupon=this.getById(id);
        if(null==coupon){
            throw new CustomException("该优惠券信息不存在,请联系管理员!");
        }
        Coupon coupon1=new Coupon();
        BeanUtil.copyProperties(req,coupon1);
        coupon1.setUpdateTime(new Date());
        this.updateById(coupon1);
    }
}
