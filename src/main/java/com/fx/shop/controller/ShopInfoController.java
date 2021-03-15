package com.fx.shop.controller;


import com.fx.shop.dto.cart.resp.Resp;
import com.fx.shop.dto.shopInfo.req.InfoEditReq;
import com.fx.shop.dto.shopInfo.resp.InfoResp;
import com.fx.shop.entity.Info;
import com.fx.shop.service.InfoService;

import com.fx.shop.util.result.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fx
 * @since 2021-03-11
 */
@Api(tags = "商城首页等信息")
@RestController
@RequestMapping("/shop/info")
public class ShopInfoController {
    @Autowired
    private  InfoService infoService;
    /**
     * 查询
     * @return
     */
    @ApiOperation(value = "查询",notes = "查询")
    @PostMapping("/query")
    public ApiResult<InfoResp> query(){
        InfoResp info=infoService.queryDetail();
        return ApiResult.success(info);
    }

    /**
     * 修改
     * @return
     */
    @ApiOperation(value = "修改",notes = "修改")
    @PostMapping("/edit")
    public ApiResult<String> edit(@RequestBody InfoEditReq req){
        infoService.edit(req);
        return ApiResult.success("修改成功!");
    }
    /**
     * 增加
     * @return
     */
    @ApiOperation(value = "增加",notes = "增加")
    @PostMapping("/add")
    public ApiResult<String> add(@RequestBody InfoEditReq req){
        infoService.add(req);
        return ApiResult.success("增加成功!");
    }
}
