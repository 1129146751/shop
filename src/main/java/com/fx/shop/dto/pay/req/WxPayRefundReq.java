package com.fx.shop.dto.pay.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WxPayRefundReq implements Serializable {

    private static final long serialVersionUID = -7479591021356042441L;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

}
