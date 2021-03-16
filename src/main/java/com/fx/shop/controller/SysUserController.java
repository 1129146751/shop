package com.fx.shop.controller;


import com.fx.shop.dto.cart.resp.Resp;
import com.fx.shop.dto.sysuser.req.SysUserLonginReq;
import com.fx.shop.service.SysUserService;
import com.fx.shop.util.result.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  系统后台登录用户
 * </p>
 *
 * @author fx
 * @since 2021-03-15
 */
@Api(tags = "系统后台用户")
@RestController
@RequestMapping("/shop/sysUser")
public class SysUserController {
    @Autowired
    private  SysUserService sysUserService;
    /**
     * 系统用户登录
     * @return
     */
    @ApiOperation(value = "系统用户登录",notes = "系统用户登录")
    @PostMapping("/login")
    public ApiResult login(@RequestBody SysUserLonginReq req){
        Map resultMap=sysUserService.login(req);
        return ApiResult.success(resultMap);
    }

}
