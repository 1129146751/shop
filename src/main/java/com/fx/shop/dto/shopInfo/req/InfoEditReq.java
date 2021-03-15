package com.fx.shop.dto.shopInfo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fx
 * @since 2021-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Info对象", description="")
public class InfoEditReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "主键不能为空")
    @ApiModelProperty(value = "主键",required = true)
    private Long id;

    @ApiModelProperty(value = "联系电话")
    private String moble;

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "订单失效时间(分)")
    private Integer failureTime;

    @ApiModelProperty(value = "商城首页简介")
    private String homePage;


}
