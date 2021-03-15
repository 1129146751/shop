package com.fx.shop.controller;

import com.fx.shop.dto.login.req.DecodeSensitiveDataReq;
import com.fx.shop.dto.login.req.LoginCodeReq;
import com.fx.shop.dto.login.resp.WxOpenIdResp;
import com.fx.shop.dto.login.resp.WxUserInfoResp;
import com.fx.shop.service.LoginService;
import com.fx.shop.service.WXService;

import com.fx.shop.util.result.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "登录")
@Slf4j
@RestController
@RequestMapping("userLogin")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private WXService wxService;

    /**
     * 通过code获取微信的openId
     * @param req
     * @return
     * @throws Exception
     */
    @PostMapping("getOpenId")
    @ApiOperation(value = "通过code获取微信的openId", notes = "通过code获取微信的openId")
    public ApiResult<WxOpenIdResp> getOpenId(@RequestBody LoginCodeReq req) throws  Exception{
        WxOpenIdResp resp=loginService.getOpenId(req);
        return ApiResult.success(resp);
    }
    /**
     * 解密微信敏感数据接口
     *
     * @return
     */
    @PostMapping("/wxMiniapp/decode")
    @ApiOperation(value = "解密微信敏感数据接口", notes = "解密微信敏感数据接口")
    public ApiResult<WxUserInfoResp> wxMiniappDecode(@RequestBody @Valid DecodeSensitiveDataReq req) {
        WxUserInfoResp resp = wxService.wxMiniappDecode(req);
        return ApiResult.success(resp);
    }

}
