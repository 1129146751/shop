package com.fx.shop.controller;


import com.fx.shop.dto.productSortRel.PsRelReq;
import com.fx.shop.service.ProductSortRelService;

import com.fx.shop.util.result.ApiResult;
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
 * 商品-分类关系表 前端控制器
 * </p>
 *
 * @author fx
 * @since 2021-02-04
 */
@Api(tags = "商品-分类关系")
@RestController
@RequestMapping("/productSortRel")
public class ProductSortRelController {
    @Autowired
    private ProductSortRelService relService;
    /**
     * 增加
     * @param req
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "增加/修改",notes = "增加/修改")
    public ApiResult<String> add(@RequestBody @Valid PsRelReq req){
        relService.add(req);
        return ApiResult.success("操作成功!");
    }


}
