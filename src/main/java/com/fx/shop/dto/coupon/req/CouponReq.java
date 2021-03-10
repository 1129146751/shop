package com.fx.shop.dto.coupon.req;

import com.sineyun.commons.base.dto.request.BasePageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 店铺优惠券
 * </p>
 *
 * @author fx
 * @since 2021-02-04
 */
@Data
@ApiModel(value="Coupon对象", description="查询店铺优惠券")
public class CouponReq extends BasePageReq {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "优惠券名称")
    private String name;


    @ApiModelProperty(value = "优惠券类型（1商品券，2运费券）")
    private Integer type;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "状态（1可用，0不可用）")
    private Integer state;

    @ApiModelProperty(value = "用户id")
    private Long customerId;
}
