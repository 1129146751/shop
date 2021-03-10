package com.fx.shop.dto.productSortRel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 商品-分类关系表
 * </p>
 *
 * @author fx
 * @since 2021-02-04
 */
@Data
@ApiModel(value="ProductSortRel对象", description="增加商品-分类关系表")
public class PsRelReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "商品id不能为空")
    @ApiModelProperty(value = "商品id",required = true)
    private Long productId;

    @NotNull(message = "分类ids不能为空")
    @ApiModelProperty(value = "分类ids",required = true)
    private Set<Long> sortIds;


}
