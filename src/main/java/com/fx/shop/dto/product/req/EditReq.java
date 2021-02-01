package com.fx.shop.dto.product.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductInfo对象", description="修改商品信息")
public class EditReq extends AddReq {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键")
    private Long id;
}
