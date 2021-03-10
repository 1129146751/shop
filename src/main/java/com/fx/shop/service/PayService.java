package com.fx.shop.service;

import com.fx.shop.dto.notify.WxPayNotifyCheckRequest;
import com.fx.shop.dto.notify.WxPayNotifyOrderResponse;
import com.fx.shop.dto.pay.req.CreatePayReq;
import com.fx.shop.dto.pay.req.WxPayRefundReq;
import com.fx.shop.dto.pay.resp.CreatePayResp;
import com.github.binarywang.wxpay.exception.WxPayException;

import java.util.Map;

public interface PayService {
    CreatePayResp createWxPay(CreatePayReq req) throws Exception;

    void wxRefund(WxPayRefundReq req) throws WxPayException;
    /**
     * 微信支付回调参数解析校验接口
     *
     * @param request
     */
    WxPayNotifyOrderResponse validateNotifyParam(WxPayNotifyCheckRequest request);

    void payNotify(WxPayNotifyOrderResponse notifyOrder);
}
