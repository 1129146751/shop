package com.fx.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单配送信息
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OrderLogistics对象", description="订单配送信息")
public class OrderLogistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "用户支付运费")
    private BigDecimal freightAmount;

    @ApiModelProperty(value = "快递退费")
    private BigDecimal refundFreightAmount;

    @ApiModelProperty(value = "快递成本价")
    private BigDecimal freightCostAmount;

    @ApiModelProperty(value = "配送方式，1陆运，2空运，3到付")
    private Integer logisticsType;

    @ApiModelProperty(value = "收货人姓名")
    private String consignee;

    @ApiModelProperty(value = "收货人手机号")
    private String consigneePhone;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "省编码")
    private String provinceCode;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "市编码")
    private String cityCode;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "区编码")
    private String districtCode;

    @ApiModelProperty(value = "街道/镇")
    private String street;

    @ApiModelProperty(value = "街道/镇编码")
    private String streetCode;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "邮政编码")
    private String zipCode;

    @ApiModelProperty(value = "物流公司名称")
    private String logisticsCompany;

    @ApiModelProperty(value = "物流公司编码")
    private String logisticsCompanyCode;

    @ApiModelProperty(value = "快递单号")
    private String expressNo;

    @ApiModelProperty(value = "发货时间")
    private LocalDateTime deliveryTime;

    @ApiModelProperty(value = "收货时间")
    private LocalDateTime receiveTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime updateTime;


}
