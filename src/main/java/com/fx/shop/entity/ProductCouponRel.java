package com.fx.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品可用优惠券
 * </p>
 *
 * @author fx
 * @since 2021-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductCouponRel对象", description="商品可用优惠券")
public class ProductCouponRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "优惠券id")
    private Long couponId;


}
