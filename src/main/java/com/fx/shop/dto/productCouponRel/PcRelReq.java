package com.fx.shop.dto.productCouponRel;

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
 * 商品可用优惠券
 * </p>
 *
 * @author fx
 * @since 2021-02-04
 */
@Data
@ApiModel(value="ProductCouponRel对象", description="修改商品可用优惠券")
public class PcRelReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "商品id不能为空")
    @ApiModelProperty(value = "商品id",required = true)
    private Long productId;

    @ApiModelProperty(value = "优惠券ids(多个以逗号隔开)",required = true)
    private Set<Long> couponId;


}
