package com.fx.shop.controller;

import com.fx.shop.service.QueryService;

import com.fx.shop.util.result.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/query")
@Api(tags = "查询物流信息",description = "查询物流信息")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @ApiOperation(value = "即时查询API", notes = "即时查询API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderCode", value = "订单编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "customerName", value = "其他参数(京东单号查询时，需要在CustomerName赋值青龙配送编码，且单号是通过该青龙配送编码发货返回的；\n" +
                    "顺丰单号查询时，需要在CustomerName赋值寄件人或收件人的手机号后四位数字；)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "shipperCode", value = "快递公司编码", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "logisticCode", value = "物流单号", paramType = "query", dataType = "String", required = true)
    })
    @GetMapping("/immediate")
    public ApiResult immediateQuery(@RequestParam(required = false) String orderCode,
                                    @RequestParam(required = false) String customerName,
                                    @RequestParam String shipperCode,
                                    @RequestParam String logisticCode) {
        return queryService.immediateQuery(orderCode, customerName, shipperCode, logisticCode);
    }


    @ApiOperation(value = "常用快递公司编码", notes = "常用快递公司编码")
    @GetMapping("/list")
    public ApiResult commonlyList() {
        return queryService.commonlyList();
    }

}
