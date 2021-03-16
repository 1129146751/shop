package com.fx.shop.dto.order.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 创建订单
 */
@Data
@ApiModel(value="创建订单", description="创建订单")
public class OrderCreateReq implements Serializable {
    @NotNull(message = "收件人地址不能为空")
    @ApiModelProperty(value = "收件人地址",required = true)
    private Long addressId;
    @NotNull(message = "用户id")
    @ApiModelProperty(value = "用户id",required = true)
    private Long  customerId;

    @ApiModelProperty(value = "使用积分数")
    private Integer integralCount;


    @ApiModelProperty(value = "优惠券ids")
    private String  couponIds;

    @NotNull(message = "商品数据不能为空")
    @ApiModelProperty(value = "商品数据",required = true)
    private List<Product> productDetail;

    @ApiModelProperty(value = "客户备注")
    private String remark;

    @Data
    public static class Product{
        @NotNull(message = "商品id不能为空")
        @ApiModelProperty(value = "商品id",required = true)
        private Long productId;

        @NotNull(message = "购买数量不能为空")
        @ApiModelProperty(value = "购买数量",required = true)
        private Integer num;
    }

}
