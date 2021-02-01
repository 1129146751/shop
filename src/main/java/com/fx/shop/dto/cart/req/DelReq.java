package com.fx.shop.dto.cart.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
/**
 * 删除购物车商品
 */
@Data
@ApiModel(value="删除购物车商品", description="删除购物车商品")
public class DelReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "购物车id不能为空")
    @ApiModelProperty(value = "购物车id",required = true)
    private Long id;

    @NotNull(message = "删除方式不能为空")
    @ApiModelProperty(value = "删除方式（1是删除1件，2是全部删除）",required = true)
    private Integer delType;
}
