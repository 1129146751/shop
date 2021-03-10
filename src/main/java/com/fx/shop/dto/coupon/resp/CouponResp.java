package com.fx.shop.dto.coupon.resp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Coupon对象", description="返回店铺优惠券")
public class CouponResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "优惠券名称")
    private String name;

    @ApiModelProperty(value = "优惠券面额")
    private BigDecimal price;

    @ApiModelProperty(value = "优惠券数量")
    private Integer count;

    @ApiModelProperty(value = "优惠券类型（1商品券，2运费券）")
    private Integer type;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "状态（1可用，0不可用）")
    private Integer state;


}
