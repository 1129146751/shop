package com.fx.shop.controller;


import com.fx.shop.dto.order.req.OrderCreateReq;
import com.fx.shop.dto.order.req.OrderEditReq;
import com.fx.shop.dto.order.req.OrderQueryReq;
import com.fx.shop.dto.order.resp.OrderResp;
import com.fx.shop.service.OrderInfoService;
import com.sineyun.commons.base.dto.response.PageResp;
import com.sineyun.commons.core.result.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Api(tags = "订单")
@RestController
@RequestMapping("/order")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderService;
    /**
     *创建订单
     * @param req
     * @return
     */
    @PostMapping("/create")
    @ApiOperation(value = "创建订单")
    public ApiResult<String> create(@RequestBody  @Valid  OrderCreateReq req){
        String orderNo=orderService.create(req);
        return ApiResult.success(orderNo);
    }
    /**
     *查询订单
     * @param req
     * @return
     */
    @PostMapping("/query")
    @ApiOperation(value = "查询订单")
    public ApiResult<PageResp<OrderResp>> query(@RequestBody OrderQueryReq req){
        PageResp<OrderResp> resp= orderService.query(req);
        return ApiResult.success(resp);
    }

    /**
     *修改订单
     * @param req
     * @return
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改订单")
    public ApiResult<String> edit(@RequestBody OrderEditReq req){
        orderService.edit(req);
        return ApiResult.success("操作成功!");
    }
}
