package com.fx.shop.dto.login.resp;


import com.fx.shop.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "微信用户信息返回类", description = "微信用户信息返回类")
public class WxUserInfoResp extends BaseDTO {

    @ApiModelProperty(value = "openId")
    private String openId;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "语言")
    private String language;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "头像")
    private String avatarUrl;

    @ApiModelProperty(value = "unionId")
    private String unionId;

    @ApiModelProperty(value = "用户绑定的手机号（国外手机号会有区号）")
    private String phoneNumber;

    @ApiModelProperty(value = "没有区号的手机号")
    private String purePhoneNumber;

    @ApiModelProperty(value = "区号")
    private String countryCode;
}
