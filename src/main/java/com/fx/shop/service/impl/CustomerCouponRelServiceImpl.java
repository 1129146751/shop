package com.fx.shop.service.impl;

import com.fx.shop.dto.customerCouponRel.req.CouponAddReq;
import com.fx.shop.entity.Coupon;
import com.fx.shop.entity.CustomerCouponRel;
import com.fx.shop.mapper.CustomerCouponRelMapper;
import com.fx.shop.service.CouponService;
import com.fx.shop.service.CustomerCouponRelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fx.shop.util.common.ObjectUtil;
import com.fx.shop.util.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户拥有的优惠券 服务实现类
 * </p>
 *
 * @author fx
 * @since 2021-02-04
 */
@Service
public class CustomerCouponRelServiceImpl extends ServiceImpl<CustomerCouponRelMapper, CustomerCouponRel> implements CustomerCouponRelService {
    @Autowired
    private CouponService couponService;
    /**
     * 用户增加优惠券
     * @param req
     */
    @Override
    public void add(CouponAddReq req) {
        CustomerCouponRel rel=new CustomerCouponRel();
        Coupon coupon=couponService.getById(req.getCouponId());
        if(null==coupon){
            throw new MyException(MyException.ERROR_400,"该优惠券不存在");
        }
        ObjectUtil.copyProperties(req,rel);
        this.save(rel);
    }
}
