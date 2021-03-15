package com.fx.shop.dto.product.resp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author fx
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductInfo对象", description="返回商品信息")
public class Resp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品轮播主图")
    private String masterImg;

    @ApiModelProperty(value = "商品缩阅图")
    private String minImg;

    @ApiModelProperty(value = "商品属性分类ids")
    private String sortIds;

    @ApiModelProperty(value = "商品属性分类名称")
    private String sortName;

    @ApiModelProperty(value = "销量")
    private Integer saleQuantity;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "副标题")
    private String subTitle;

    @ApiModelProperty(value = "商品描述，富文本")
    private String description;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "商品重量，默认为克")
    private BigDecimal weight;

    /*@ApiModelProperty(value = "关键字")
    private String keywords;*/

    @ApiModelProperty(value = "限购数量")
    private Integer perLimit;

   /* @ApiModelProperty(value = "产品详情网页内容")
    private String detailHtml;*/

    @ApiModelProperty(value = "排序")
    private Integer sortNo;

    @ApiModelProperty(value = "上架状态：0->售罄；1->上架；2->仓库")
    private Integer publishStatus;

    @ApiModelProperty(value = "更新时间")
    private Date createTime;

    @ApiModelProperty(value = "创建时间")
    private Date updateTime;

    //TODO 优惠券信息

    @ApiModelProperty(value = "优惠券信息")
    private String couponName;

    @ApiModelProperty(value = "优惠券ids")
    private String couponIds;


}
