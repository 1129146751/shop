package com.fx.shop.dto.login.req;

import com.sineyun.commons.base.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 解密微信敏感数据请求
 *
 * @author 46105
 */
@Data
@ApiModel(value = "解密微信敏感数据请求", description = "解密微信敏感数据请求")
public class DecodeSensitiveDataReq extends BaseDTO {

    @NotBlank(message = "应用标识appId不能为空")
    @ApiModelProperty(value = "appId", required = true)
    private String appId;

    @NotBlank(message = "用户标识openId不能为空")
    @ApiModelProperty(value = "openId", required = true)
    private String openId;

    @NotBlank(message = "加密数据不能为空")
    @ApiModelProperty(value = "加密数据，wx.getUserInfo返回", required = true)
    private String encryptedData;

    @NotBlank(message = "对称解密算法不能为空")
    @ApiModelProperty(value = "对称解密算法，wx.getUserInfo返回", required = true)
    private String ivStr;

/*    @NotNull(message = "数据类型不能为空")
    @ApiModelProperty(value = "数据类型{0:用户信息,1:手机号信息}", required = true)
    private Integer dataType;*/

}
