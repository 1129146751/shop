package com.fx.shop.dto.order.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import com.fx.shop.dto.BasePageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Data
@ApiModel(value="OrderInfo对象", description="查询订单")
public class OrderQueryReq extends BasePageReq {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "下单人id")
    private Long userId;

    @ApiModelProperty(value = "配送状态（-1:已取消 0:待支付 1:待发货 2:待收货,3 完成 4 退款中 5 已经退款）")
    private Integer orderStatus;

    @ApiModelProperty(value = "支付开始时间",notes = "yyyy-MM-dd")
    private String payTimeStart;

    @ApiModelProperty(value = "支付结束时间",notes = "yyyy-MM-dd")
    private String payTimeEnd;
}
