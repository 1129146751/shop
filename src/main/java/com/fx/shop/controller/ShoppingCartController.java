package com.fx.shop.controller;


import com.fx.shop.dto.cart.req.AddReq;
import com.fx.shop.dto.cart.req.DelReq;
import com.fx.shop.dto.cart.req.QueryReq;
import com.fx.shop.dto.cart.resp.Resp;
import com.fx.shop.service.ShoppingCartService;

import com.fx.shop.util.result.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 购物车 前端控制器
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Api(tags = "购物车")
@RestController
@RequestMapping("/shop/cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService cartService;

    /**
     * 查询
     * @return
     */
    @ApiOperation(value = "查询",notes = "查询")
    @GetMapping("/query")
    public ApiResult<List<Resp>> query(Long userId){
        List<Resp> respList=cartService.queryCart(userId);
       return ApiResult.success(respList);
    }

    /**
     * 增加
     * @return
     */
    @ApiOperation(value = "增加",notes = "增加")
    @PostMapping("/add")
    public ApiResult<String> add(@RequestBody AddReq req){
        cartService.add(req);
        return ApiResult.success("操作成功!");
    }

    /**
     * 删除
     * @return
     */
    @ApiOperation(value = "删除",notes = "删除")
    @PostMapping("/del")
    public ApiResult<String> del(@RequestBody DelReq req){
        cartService.del(req);
        return ApiResult.success("操作成功!");
    }

}
