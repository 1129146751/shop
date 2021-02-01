package com.fx.shop.dto.pay.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "创建预支付参数返回", description = "创建预支付参数返回")
public class CreatePayResp {
    private String appId;
    private String timeStamp;
    private String nonceStr;
    @JsonProperty("package")
    private String package_;
    private String signType;
    private String paySign;
}
