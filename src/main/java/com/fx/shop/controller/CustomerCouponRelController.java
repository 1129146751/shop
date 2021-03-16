package com.fx.shop.controller;


import com.fx.shop.dto.PageResp;
import com.fx.shop.dto.coupon.req.CouponReq;
import com.fx.shop.dto.coupon.resp.CouponResp;
import com.fx.shop.dto.customerCouponRel.req.CouponAddReq;
import com.fx.shop.service.CustomerCouponRelService;
import com.fx.shop.util.result.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户拥有的优惠券 前端控制器
 * </p>
 *
 * @author fx
 * @since 2021-02-04
 */
@Api(tags = "用户-优惠券")
@RestController
@RequestMapping("/customerCouponRel")
public class CustomerCouponRelController {
    @Autowired
    CustomerCouponRelService relService;
    /**
     *用户增加优惠券
     * @param req
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "用户增加优惠券")
    public ApiResult<String>  add(@RequestBody CouponAddReq req) {
        relService.add(req);
        return ApiResult.success("增加成功!");
    }


}
