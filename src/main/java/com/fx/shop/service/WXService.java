package com.fx.shop.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.fx.shop.dto.login.req.DecodeSensitiveDataReq;
import com.fx.shop.dto.login.resp.WxUserInfoResp;
import com.github.binarywang.wxpay.service.WxPayService;

public interface WXService {
    /**
     * 获取微信小程序客服端
     * @param appId
     * @return
     */
    WxMaService getWxMaService(String appId);
    /**
     * 获取微信支付业务端
     *
     * @param appId 微信小程序Id
     * @return
     */
     WxPayService getWxPayService(String appId);

    /**
     * 解密微信敏感数据接口
     * @param req
     * @return
     */
    WxUserInfoResp wxMiniappDecode(DecodeSensitiveDataReq req);
}
