package com.fx.shop.service;

import com.fx.shop.dto.pay.req.CreatePayReq;
import com.fx.shop.dto.pay.req.WxPayRefundReq;
import com.fx.shop.dto.pay.resp.CreatePayResp;
import com.github.binarywang.wxpay.exception.WxPayException;

public interface PayService {
    CreatePayResp createWxPay(CreatePayReq req) throws Exception;

    void wxRefund(WxPayRefundReq req) throws WxPayException;
}
