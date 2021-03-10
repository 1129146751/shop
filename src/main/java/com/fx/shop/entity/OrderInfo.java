package com.fx.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OrderInfo对象", description="订单")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "下单人id")
    private Long userId;

    @ApiModelProperty(value = "配送状态（-1:已取消 0:待支付 1:待发货 2:待收货,3 完成 4 已经退款）")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单总重量")
    private BigDecimal totalWeight;

    @ApiModelProperty(value = "商品总金额")
    private BigDecimal productTotalAmount;

    @ApiModelProperty(value = "运费金额")
    private BigDecimal freightAmount;

    @ApiModelProperty(value = "其他金额，预留")
    private BigDecimal otherAmount;

    @ApiModelProperty(value = "优惠金额，预留")
    private BigDecimal discountAmount;

    @ApiModelProperty(value = "总金额")
    private BigDecimal totalAmount;


    @ApiModelProperty(value = "积分数")
    private Integer integralAmount;

    @ApiModelProperty(value = "实付款")
    private BigDecimal payAmount;

    @ApiModelProperty(value = "支付时间")
    private Date payTime;

    @ApiModelProperty(value = "支付方式，1微信，2支付宝，3网银")
    private Integer payType;

    @ApiModelProperty(value = "是否配送，1-自取、2-配送")
    private Integer distributType;

    @ApiModelProperty(value = "收货地址id")
    private Long addressId;

    @ApiModelProperty(value = "物流信息id")
    private Long logisticsId;

    @ApiModelProperty(value = "失效时间")
    private Date failureTime;

    @ApiModelProperty(value = "失效原因")
    private String failureReason;

    @ApiModelProperty(value = "退款原因")
    private String refundReason;

    @ApiModelProperty(value = "退款时间")
    private Date refundTime;

    @ApiModelProperty(value = "退款失败原因")
    private String refundFailReason;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty(value = "交易订单号")
    private String outTradeNo;

    @ApiModelProperty(value = "交易流水号")
    private String tradeNo;

    @ApiModelProperty(value = "客户备注")
    private String remark;

    @ApiModelProperty(value = "更新时间")
    private Date createTime;

    @ApiModelProperty(value = "创建时间")
    private Date updateTime;


}
