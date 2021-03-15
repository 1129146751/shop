package com.fx.shop.controller;


import com.fx.shop.dto.wx.WeixinParam;
import com.fx.shop.service.CccService;
import com.fx.shop.util.redis.RedisUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fx
 * @since 2021-01-19
 */
@Api(tags = "测试")
@Slf4j
@RestController
@RequestMapping("/shop/ccc")
public class CccController {
    @Autowired
    CccService service;
    @Autowired
    WeixinParam param;
    /*@PostMapping("method")
    public Object method(){
       Map map=new HashMap<>();
        long maxAccessExpiration = 24*60*60*1000;//一天
        //过期时间
        Date expiresAt = new Date(System.currentTimeMillis() + maxAccessExpiration);

        Map<String, Object> payload = new HashMap<>();
        payload.put("userName","张三");
        String accessToken = JwtUtil.createToken("4567", payload, maxAccessExpiration);
        // 生成刷新token
//        int maxRefreshExpiration = Ints.tryParse(super.getValue(ConfigKeyConstant.JWT_REFRESH_ACCESS_TOKEN_EXPIRATION));
        long maxRefreshExpiration = 5*24*60*60*1000;//五天

        String refreshToken = JwtUtil.createToken("10086", new HashMap<String, Object>(), maxRefreshExpiration);
        map.put("accessToken",accessToken);
        map.put("refreshToken",refreshToken);
        String userId = JwtUtil.getUserId(accessToken);
        log.info(userId);
        Map userNameMap = JwtUtil.getPayload(accessToken,Map.class);
        log.info(""+userNameMap.get("userName"));
        return map;
    }*/
    @Resource
    private RedisUtil redisUtil;
    @PostMapping("/method1")
    public void method1(){
        redisUtil.set("799305493490434048key",34567778);
        System.out.println(redisUtil.get("799305493490434048key"));
    }
}
