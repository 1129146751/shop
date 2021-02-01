package com.fx.shop.service;

import com.fx.shop.dto.login.req.LoginCodeReq;
import com.fx.shop.dto.login.resp.WxOpenIdResp;

public interface LoginService {
    WxOpenIdResp getOpenId(LoginCodeReq req) throws Exception;
}
