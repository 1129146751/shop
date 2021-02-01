package com.fx.shop.service.impl;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fx.shop.dto.login.req.LoginCodeReq;
import com.fx.shop.dto.login.resp.WxOpenIdResp;
import com.fx.shop.entity.CustomerUser;
import com.fx.shop.service.CustomerUserService;
import com.fx.shop.service.LoginService;
import com.fx.shop.service.WXService;
import com.fx.shop.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private WXService wxService;
    @Autowired
    private CustomerUserService customerUserService;
    @Resource
    private RedisUtil redisUtil;
    /**
     * 通过code获取微信的openId
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public WxOpenIdResp getOpenId(LoginCodeReq req) throws Exception {
        String appId=req.getAppId();
        //TODO 获取openid
        WxMaJscode2SessionResult session = wxService.getWxMaService(appId).getUserService()
                .getSessionInfo(req.getCode());
        WxOpenIdResp resp=new WxOpenIdResp();
        BeanUtil.copyProperties(session,resp);
        //TODO 存入数据库
        String openId=resp.getOpenId();
        QueryWrapper<CustomerUser> userWrapper=new QueryWrapper<>();
        userWrapper.eq("app_id",appId);
        userWrapper.eq("open_id",openId);
        CustomerUser count=customerUserService.getOne(userWrapper);
        CustomerUser customerUser=new CustomerUser();
        if(null==count){
            customerUser.setAppId(appId);
            customerUser.setOpenId(openId);
            customerUserService.save(customerUser);
        }
        //TODO 会话密钥
        redisUtil.set("openId",session.getSessionKey());
        return resp;
    }
}
