package com.fx.shop.dto.coupon.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 店铺优惠券
 * </p>
 *
 * @author fx
 * @since 2021-02-04
 */
@Data
@ApiModel(value="Coupon对象", description="增加店铺优惠券")
public class CouponAddReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "优惠券名称不能为空!")
    @ApiModelProperty(value = "优惠券名称",required = true)
    private String name;

    @NotNull(message = "优惠券面额不能为空!")
    @ApiModelProperty(value = "优惠券面额",required = true)
    private BigDecimal price;

    @NotNull(message = "优惠券数量不能为空!")
    @ApiModelProperty(value = "优惠券数量",required = true)
    private Integer count;

    @NotNull(message = "优惠券类型不能为空!")
    @ApiModelProperty(value = "优惠券类型（1商品券，2运费券）",required = true)
    private Integer type;

    @NotBlank(message = "开始时间不能为空!")
    @ApiModelProperty(value = "开始时间",required = true)
    private String startTime;

    @NotBlank(message = "结束时间不能为空!")
    @ApiModelProperty(value = "结束时间",required = true)
    private String endTime;

    @ApiModelProperty(value = "状态（1可用，0不可用）")
    private Integer state;


}
