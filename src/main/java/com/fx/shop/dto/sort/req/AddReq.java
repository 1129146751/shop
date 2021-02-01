package com.fx.shop.dto.sort.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

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
@ApiModel(value="ProductSort对象", description="增加商品分类")
public class AddReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "商品分类名称不能为空")
    @ApiModelProperty(value = "商品分类名称",required = true)
    private String sortName;

    @ApiModelProperty(value = "商品分类图片")
    private String sortImg;

    @ApiModelProperty(value = "状态（1可用，0不可用）")
    private String state;

    @ApiModelProperty(value = "排序")
    private Integer sort;


}
