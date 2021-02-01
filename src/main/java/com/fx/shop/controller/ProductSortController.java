package com.fx.shop.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fx.shop.dto.sort.req.AddReq;
import com.fx.shop.dto.sort.req.EditReq;
import com.fx.shop.dto.sort.req.QueryReq;
import com.fx.shop.dto.sort.resp.Resp;
import com.fx.shop.service.ProductSortService;
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

/**
 * <p>
 * 商品分类 前端控制器
 * </p>
 *
 * @author fx
 * @since 2021-01-22
 */
@Api(tags = "商品分类")
@RestController
@RequestMapping("/shop/sort")
public class ProductSortController {
    @Autowired
    private ProductSortService sortService;

    /**
     * 增加
     * @param req
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "增加",notes = "增加")
    public ApiResult<String> add(@RequestBody @Valid AddReq req){
        sortService.add(req);
        return ApiResult.success("增加成功!");
    }

    /**
     * 修改
     * @param req
     * @return
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改",notes = "修改")
    public ApiResult<String> edit(@RequestBody @Valid EditReq req){
        sortService.edit(req);
        return ApiResult.success("修改成功!");
    }

    /**
     * 查询
     * @param req
     * @return
     */
    @PostMapping("/query")
    @ApiOperation(value = "查询",notes = "查询")
    public ApiResult<PageResp<Resp>> query(@RequestBody @Valid QueryReq req){
        PageResp<Resp> respPage=sortService.query(req);
        return ApiResult.success(respPage);
    }
}
