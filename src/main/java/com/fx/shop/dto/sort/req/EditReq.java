package com.fx.shop.dto.sort.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 商品分类
 * </p>
 *
 * @author fx
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductSort对象", description="修改商品分类")
public class EditReq extends AddReq {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

}
