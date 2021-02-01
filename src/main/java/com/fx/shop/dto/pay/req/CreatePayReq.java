package com.fx.shop.dto.pay.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "创建预支付", description = "创建预支付")
public class CreatePayReq implements Serializable {
    @ApiModelProperty(value = "订单号")
    private String orderNo;
}
