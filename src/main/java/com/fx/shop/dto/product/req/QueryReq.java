package com.fx.shop.dto.product.req;


import com.fx.shop.dto.BasePageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

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
@ApiModel(value="ProductInfo对象", description="查询商品信息")
public class QueryReq extends BasePageReq {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品名称/副标题")
    private String name;

    @ApiModelProperty(value = "商品属性ids")
    private Set<Long> sortIds;

    @ApiModelProperty(value = "上架状态：0->售罄；1->上架；2->仓库")
    private Integer publishStatus;
}
