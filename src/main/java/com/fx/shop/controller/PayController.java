package com.fx.shop.controller;

import com.fx.shop.dto.pay.req.CreatePayReq;
import com.fx.shop.dto.pay.req.WxPayRefundReq;
import com.fx.shop.dto.pay.resp.CreatePayResp;
import com.fx.shop.service.PayService;
import com.fx.shop.util.result.ApiResult;
import com.github.binarywang.wxpay.exception.WxPayException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  支付
 * </p>
 *
 * @author fx
 * @since 2021-01-19
 */
@Api(tags = "支付")
@Slf4j
@RestController
@RequestMapping("/payController")
public class PayController {
    @Autowired
    private  PayService payService;
    /**
     * 创建微信预支付
     * @return
     */
    @ApiOperation(value = "创建微信预支付", notes = "创建微信预支付")
    @PostMapping("/createWxPay")
    public ApiResult<CreatePayResp> createWxPay(@RequestBody CreatePayReq req) throws  Exception{
        CreatePayResp resp=payService.createWxPay(req);
        return  ApiResult.success(resp);
    }
    /**
     * 微信退款
     * @return
     */
    @ApiOperation(value = "微信退款", notes = "微信退款")
    @PostMapping("/wxRefund")
    public ApiResult<CreatePayResp> wxRefund(@RequestBody WxPayRefundReq req) throws WxPayException {
        payService.wxRefund(req);
        return  ApiResult.success();
    }

}
