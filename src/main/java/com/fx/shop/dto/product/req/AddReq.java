package com.fx.shop.dto.product.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@ApiModel(value="ProductInfo对象", description="增加商品信息")
public class AddReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "商品名称不能为空!")
    @ApiModelProperty(value = "商品名称",required = true)
    private String name;

    @NotBlank(message = "商品轮播主图不能为空")
    @ApiModelProperty(value = "商品轮播主图,逗号分隔",required = true)
    private String masterImg;

    @NotBlank(message = "商品缩阅图不能为空")
    @ApiModelProperty(value = "商品缩阅图",required = true)
    private String minImg;

    @NotBlank(message = "商品属性分类不能为空")
    @ApiModelProperty(value = "商品属性分类",required = true)
    private String sortCode;

    @NotNull(message = "价格不能为空")
    @ApiModelProperty(value = "价格",required = true)
    private BigDecimal price;

    @NotBlank(message = "副标题不能为空")
    @ApiModelProperty(value = "副标题",required = true)
    private String subTitle;

    @NotBlank(message = "商品描述富文本不能为空")
    @ApiModelProperty(value = "商品描述，富文本",required = true)
    private String description;

    @NotNull(message = "NotNull不能为空")
    @ApiModelProperty(value = "NotNull",required = true)
    private Integer stock;

    @ApiModelProperty(value = "商品重量，默认为克")
    private BigDecimal weight;

    @ApiModelProperty(value = "关键字")
    private String keywords;

    @ApiModelProperty(value = "限购数量")
    private Integer perLimit;

   /* @ApiModelProperty(value = "产品详情网页内容")
    private String detailHtml;*/

    @ApiModelProperty(value = "排序")
    private Integer sortNo;




}
