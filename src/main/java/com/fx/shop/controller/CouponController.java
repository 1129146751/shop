package com.fx.shop.controller;


import com.fx.shop.dto.area.req.AreaQueryReq;
import com.fx.shop.dto.area.resp.AreaResp;
import com.fx.shop.dto.coupon.req.CouponAddReq;
import com.fx.shop.dto.coupon.req.CouponEditReq;
import com.fx.shop.dto.coupon.req.CouponReq;
import com.fx.shop.dto.coupon.resp.CouponResp;
import com.fx.shop.service.CouponService;
import com.sineyun.commons.base.dto.response.PageResp;
import com.sineyun.commons.core.result.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 店铺优惠券 前端控制器
 * </p>
 *
 * @author fx
 * @since 2021-02-04
 */
@Api(tags = "店铺优惠券")
@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;
    /**
     *查询店铺优惠券
     * @param req
     * @return
     */
    @PostMapping("/query")
    @ApiOperation(value = "查询店铺优惠券")
    public ApiResult<PageResp<CouponResp>> query(@RequestBody CouponReq req) {
        PageResp<CouponResp> pageResp=couponService.query(req);
        return ApiResult.success(pageResp);
    }
    /**
     *增加店铺优惠券
     * @param req
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "增加店铺优惠券")
    public ApiResult<String> add(@RequestBody CouponAddReq req) {
        couponService.add(req);
        return ApiResult.success("增加店铺优惠券成功!");
    }
    /**
     *修改店铺优惠券
     * @param req
     * @return
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改店铺优惠券")
    public ApiResult<String> edit(@RequestBody CouponEditReq req) {
        couponService.edit(req);
        return ApiResult.success("修改店铺优惠券!");
    }
}
