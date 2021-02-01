package com.fx.shop.dto.cart.req;

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
 * 购物车
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Data
@ApiModel(value="购物车", description="查询购物车")
public class QueryReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long sid;

    @ApiModelProperty(value = "用户")
    private Long userId;

    @ApiModelProperty(value = "所属商品")
    private Long productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品缩阅图")
    private String minImg;

    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    @ApiModelProperty(value = "更新时间")
    private Date createTime;

    @ApiModelProperty(value = "创建时间")
    private Date updateTime;


}
