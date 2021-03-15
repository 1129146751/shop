package com.fx.shop.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础请求类，所有请求dto都要继承它
 *
 * @author: John
 * @create: 2019-05-22 11:47
 * @desc:
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "基础请求类", description = "基础请求类")
public class BaseReq extends BaseDTO {

//    @ApiModelProperty(value = "当前登录用户信息", hidden = true)
//    private TokenInfo tokenInfo;
}
