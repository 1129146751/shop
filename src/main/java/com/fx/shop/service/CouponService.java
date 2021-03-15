package com.fx.shop.service;

import com.fx.shop.dto.PageResp;
import com.fx.shop.dto.coupon.req.CouponAddReq;
import com.fx.shop.dto.coupon.req.CouponEditReq;
import com.fx.shop.dto.coupon.req.CouponReq;
import com.fx.shop.dto.coupon.resp.CouponResp;
import com.fx.shop.entity.Coupon;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 * 店铺优惠券 服务类
 * </p>
 *
 * @author fx
 * @since 2021-02-04
 */
public interface CouponService extends IService<Coupon> {

    PageResp<CouponResp> query(CouponReq req);

    void add(CouponAddReq req);

    void edit(CouponEditReq req);
}
