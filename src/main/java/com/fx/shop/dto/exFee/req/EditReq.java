package com.fx.shop.dto.exFee.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author fx
 * @since 2021-01-22
 */
@Data
@ApiModel(value="ExpressFee对象", description="修改快递费")
public class EditReq extends AddReq {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

}
