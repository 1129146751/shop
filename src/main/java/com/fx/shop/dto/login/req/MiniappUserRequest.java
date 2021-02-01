package com.fx.shop.dto.login.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value = "解密微信信息对象", description = "解密微信信息对象")
public class MiniappUserRequest implements Serializable {
    @NotBlank(message = "应用标识appId不能为空")
    @ApiModelProperty(value = "appId", required = true)
    private String appId;

    @NotBlank(message = "openId不能为空")
    @ApiModelProperty(value = "openId", required = true)
    private String openId;

    @NotBlank(message = "会话密钥不能为空")
    @ApiModelProperty(value = "会话密钥", required = true)
    private String sessionKey;

    @NotBlank(message = "加密数据不能为空")
    @ApiModelProperty(value = "加密数据，wx.getUserInfo返回", required = true)
    private String encryptedData;

    @NotBlank(message = "对称解密算法不能为空")
    @ApiModelProperty(value = "对称解密算法，wx.getUserInfo返回", required = true)
    private String ivStr;
}
