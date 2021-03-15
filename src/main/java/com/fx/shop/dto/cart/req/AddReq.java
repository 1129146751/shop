package com.fx.shop.dto.cart.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 添加购物车
 */
@Data
@ApiModel(value="添加购物车", description="添加购物车")
public class AddReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "商品id不能为空")
    @ApiModelProperty(value = "商品id",required = true)
    private Long pid;

    @NotNull(message = "数量不能为空")
    @ApiModelProperty(value = "数量",required = true)
    private Integer num;

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id",required = true)
    private Long userId;
}
