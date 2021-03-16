package com.fx.shop.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fx.shop.dto.sysuser.req.SysUserLonginReq;
import com.fx.shop.dto.useraddress.req.AddressAddReq;
import com.fx.shop.dto.useraddress.req.AddressQueryReq;
import com.fx.shop.dto.useraddress.req.AddresseEditReq;
import com.fx.shop.dto.useraddress.resp.AddressResp;
import com.fx.shop.entity.UserShippingAddress;
import com.fx.shop.service.UserShippingAddressService;
import com.fx.shop.util.result.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 常用地址 前端控制器
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Api(tags = "常用地址")
@RestController
@RequestMapping("/userAddress")
public class UserShippingAddressController {
    @Autowired
    private UserShippingAddressService addressService;
    /**
     * 增加常用地址
     * @return
     */
    @ApiOperation(value = "增加常用地址",notes = "增加常用地址")
    @PostMapping("/add")
    public ApiResult<String> add(@RequestBody @Valid AddressAddReq req){
        addressService.add(req);
        return ApiResult.success("添加成功!");
    }
    /**
     * 修改常用地址
     * @return
     */
    @ApiOperation(value = "修改常用地址",notes = "修改常用地址")
    @PostMapping("/edit")
    public ApiResult<String> edit(@RequestBody @Valid AddresseEditReq req){
        addressService.edit(req);
        return ApiResult.success("修改成功!");
    }
    /**
     * 查询常用地址
     * @return
     */
    @ApiOperation(value = "查询常用地址",notes = "查询常用地址")
    @PostMapping("/query")
    public ApiResult<Page<AddressResp>> query(@RequestBody AddressQueryReq req){
        Page<AddressResp> respPage= addressService.query(req);
        return ApiResult.success(respPage);
    }
}
