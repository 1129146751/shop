package com.fx.shop.dto.login.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "微信openId返回", description = "微信openId返回")
public class WxOpenIdResp implements Serializable {
    @ApiModelProperty(value = "openId")
    private String openId;

/*    private String sessionKey;

    private String unionid;*/
}
