package com.fx.shop.controller;


import com.fx.shop.dto.appoint.AppointReq;
import com.fx.shop.dto.sheet.ElectronicCancelReq;
import com.fx.shop.dto.sheet.ElectronicSheetReq;
import com.fx.shop.dto.visit.VisitReq;
import com.fx.shop.service.OrderService;
import com.sineyun.commons.core.result.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
/*@RestController
@RequestMapping(value = "/sheet")
@Api(tags = "电子面单",description = "电子面单")*/
public class ElectronicSheetController {

    @Resource
    private OrderService orderService;

    @ApiOperation(value = "预约取件API", notes = "预约取件API")
    @PostMapping("/appointPickUp")
    public ApiResult appointPickUp(@RequestBody AppointReq req){
        ApiResult restResult = orderService.appointPickUp(req);
        return restResult;
    }

    @ApiOperation(value = "上门取件API", notes = "上门取件API")
    @PostMapping("/visitPickUp")
    public ApiResult visitPickUp(@RequestBody VisitReq req){
        ApiResult restResult = orderService.visitPickUp(req);
        return restResult;
    }

    @ApiOperation(value = "电子面单API", notes = "电子面单API")
    @PostMapping("/electronicSheet")
    public ApiResult electronicSheet(@RequestBody ElectronicSheetReq req){
        ApiResult restResult = orderService.electronicSheet(req);
        return restResult;
    }

    @ApiOperation(value = "电子面单订单取消API", notes = "电子面单订单取消API")
    @PostMapping("/electronicCancel")
    public ApiResult electronicCancel(@RequestBody ElectronicCancelReq req){
        ApiResult restResult = orderService.electronicCancel(req);
        return restResult;
    }
}
