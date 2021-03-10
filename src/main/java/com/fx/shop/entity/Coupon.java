package com.fx.shop.entity;

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
 * 店铺优惠券
 * </p>
 *
 * @author fx
 * @since 2021-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Coupon对象", description="店铺优惠券")
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
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
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "状态（1可用，0不可用）")
    private Integer state;


}
