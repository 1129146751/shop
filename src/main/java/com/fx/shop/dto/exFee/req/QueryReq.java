package com.fx.shop.dto.exFee.req;


import com.fx.shop.dto.BasePageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 *
 * </p>
 *
 * @author fx
 * @since 2021-01-22
 */
@Data
@ApiModel(value="ExpressFee对象", description="查询快递费")
public class QueryReq extends BasePageReq {

    @ApiModelProperty(value = "省名称/省编码" )
    private String name;

    /*@ApiModelProperty(value = "")
    private String provinceCode;*/

    @ApiModelProperty(value = "快递类型（1空运，2 陆运,顺丰到付）")
    private Integer expressType;

    /*@ApiModelProperty(value = "快递类型名称")
    private String expressName;*/

    @ApiModelProperty(value = "状态1有效 0无效")
    private Integer status;
}
