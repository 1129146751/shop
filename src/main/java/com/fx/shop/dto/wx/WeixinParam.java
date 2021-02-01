package com.fx.shop.dto.wx;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信参数
 */
@Data
@Configuration //表示会引用资源文件
@ConfigurationProperties(prefix = "wx") //prefix前缀
public class WeixinParam {
    /**
     * 小程序AppId
     */
    private String appId;

    /**
     * 小程序AppSecret
     */
    private String appSecret;

    /**
     * 小程序Token
     */
    private String token;

    /**
     * 小程序AesKey
     */
    private String aesKey;

    /**
     * 微信商户Id
     */
    private String mchId;

    /**
     * 微信商户Key
     */
    private String mchKey;

    /**
     * 微信商户APIKeyPath
     */
    private String keyPath;
}
