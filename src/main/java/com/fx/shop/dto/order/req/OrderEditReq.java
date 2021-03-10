package com.fx.shop.dto.order.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
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
@ApiModel(value="OrderInfo对象", description="修改订单")
public class OrderEditReq implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull(message = "主键不能为空")
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "配送状态（-1:已取消 0:待支付 1:待发货 2:待收货,3 完成 4 退款申请中  5 已经退款）")
    private Integer orderStatus;

    @ApiModelProperty(value = "退款原因")
    private String refundReason;

}
