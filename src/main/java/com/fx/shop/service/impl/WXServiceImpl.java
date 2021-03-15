package com.fx.shop.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.fx.shop.dto.login.req.DecodeSensitiveDataReq;
import com.fx.shop.dto.login.req.MiniappUserRequest;
import com.fx.shop.dto.login.resp.WxUserInfoResp;
import com.fx.shop.dto.wx.WeixinParam;
import com.fx.shop.service.WXService;
import com.fx.shop.util.redis.RedisUtil;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WXServiceImpl implements WXService {
    @Autowired
    private  WeixinParam param;
    @Autowired
    private RedisUtil  redisUtil;
    /**
     * 获取微信小程序客服端
     *
     * @param appId 微信小程序Id
     * @return
     */
    @Override
    public WxMaService getWxMaService(String appId) {
        WxMaDefaultConfigImpl wxConfigProvider = new WxMaDefaultConfigImpl();
        wxConfigProvider.setAppid(param.getAppId());
        wxConfigProvider.setSecret(param.getAppSecret());
        wxConfigProvider.setToken(param.getToken());
        wxConfigProvider.setAesKey(param.getAesKey());
        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(wxConfigProvider);
        return wxMaService;
    }
    /**
     * 获取微信支付业务端
     * @param appId 微信小程序Id
     * @return
     */
    @Override
    public WxPayService getWxPayService(String appId) {

       /*BoundHashOperations<String, String, String> boundHashOperations = redisTemplate
                .boundHashOps(RedisKeyConstant.HOSPITAL_WEIXIN_KEY);
        String apiParamJson = boundHashOperations.get(appId);*/
        String apiParamJson =null;
        //TODO 微信配置信息 appid  商户号等
        log.debug("pay apiParamJson==>{}", apiParamJson);
        if (StringUtils.isBlank(apiParamJson)) {
        }
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setUseSandboxEnv(false);
        payConfig.setAppId(param.getAppId());
        payConfig.setMchId(param.getMchId());
        payConfig.setMchKey(param.getMchKey());
        if (StringUtils.isNotBlank(param.getKeyPath())) {
            try {
                byte[] keyContent = Hex.decodeHex(param.getKeyPath().toCharArray());
                payConfig.setKeyContent(keyContent);
            } catch (DecoderException e) {
                log.error("", e);
                throw new    RuntimeException(e);
            }
        }

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        log.info("---------------wxPayService"+wxPayService);
        return wxPayService;
    }

    /**
     * 解密微信敏感数据接口
     * @param req
     * @return
     */
    @Override
    public WxUserInfoResp wxMiniappDecode(DecodeSensitiveDataReq req) {
        String openId=req.getOpenId();
        Object sessionKeyObj=redisUtil.get(openId);
        if(null==sessionKeyObj){
            throw new    RuntimeException("错误的会话密钥!");
        }
        //TODO 会话秘钥
        String sessionKey=sessionKeyObj.toString();
        MiniappUserRequest request=new MiniappUserRequest();
        BeanUtil.copyProperties(req,request);
        request.setSessionKey(sessionKey);
        //TODO 用户信息
        WxUserInfoResp resp=getMiniappUserInfo(request);
        log.info("---------------解密微信敏感数据接口"+resp);
        return resp;
    }

    /**
     * 通过微信敏感数据获取微信用户信息
     *
     * @param request
     * @return
     */

    private WxUserInfoResp getMiniappUserInfo(MiniappUserRequest request) {
        WxMaUserInfo userInfo = this.getWxMaService(request.getAppId()).getUserService()
                .getUserInfo(request.getSessionKey(), request.getEncryptedData(), request.getIvStr());
        log.info("微信用户手机号信息 ==> [{}]", JSONUtil.toJsonStr(userInfo));
        WxUserInfoResp resp = new WxUserInfoResp();
        BeanUtil.copyProperties(userInfo, resp);
        return resp;
    }
    /**
     * 通过微信敏感数据获取微信手机号信息
     *
     * @param request
     * @return
     */
    private WxUserInfoResp getMiniappPhoneInfo(MiniappUserRequest request) {
        WxMaPhoneNumberInfo phoneInfo = this.getWxMaService(request.getAppId()).getUserService()
                .getPhoneNoInfo(request.getSessionKey(), request.getEncryptedData(), request.getIvStr());
        log.info("微信用户手机号信息 ==> [{}]", JSONUtil.toJsonStr(phoneInfo));
        WxUserInfoResp resp = new WxUserInfoResp();
        BeanUtil.copyProperties(phoneInfo, resp);

        return resp;
    }
}
