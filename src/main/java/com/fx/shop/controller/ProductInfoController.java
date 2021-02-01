package com.fx.shop.controller;


import com.fx.shop.dto.product.req.AddReq;
import com.fx.shop.dto.product.req.EditReq;
import com.fx.shop.dto.product.req.QueryReq;
import com.fx.shop.dto.product.resp.Resp;
import com.fx.shop.service.ProductInfoService;
import com.sineyun.commons.base.dto.response.PageResp;
import com.sineyun.commons.core.result.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author fx
 * @since 2021-01-22
 */
@Api(tags = "商品信息")
@RestController
@RequestMapping("/shop/product")
public class ProductInfoController {

    @Autowired
    private  ProductInfoService productService;

    /**
     * 增加
     * @param req
     * @return
     */
    @ApiOperation(value = "增加",notes = "增加")
    @PostMapping("/add")
    public ApiResult<String> add(@RequestBody @Valid AddReq req){
        productService.add(req);
        return ApiResult.success("添加成功!");
    }

    /**
     * 修改
     * @param req
     * @return
     */
    @ApiOperation(value = "修改",notes = "修改")
    @PostMapping("/edit")
    public ApiResult<String> edit(@RequestBody EditReq req){
        productService.edit(req);
        return ApiResult.success("修改成功!");
    }

    /**
     * 查询
     * @param req
     * @return
     */
    @ApiOperation(value = "查询",notes = "查询")
    @PostMapping("/query")
    public ApiResult<PageResp<Resp>> query(@RequestBody QueryReq req){
        PageResp<Resp> pageResp=productService.query(req);
        return ApiResult.success(pageResp);
    }
}
