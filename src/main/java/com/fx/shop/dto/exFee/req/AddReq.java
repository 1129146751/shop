package com.fx.shop.dto.exFee.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author fx
 * @since 2021-01-22
 */
@Data
@ApiModel(value="ExpressFee对象", description="增加快递费")
public class AddReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "省名称不能为空")
    @ApiModelProperty(value = "省名称",required = true)
    private String provinceName;

    @NotBlank(message = "省编码不能为空")
    @ApiModelProperty(value = "省编码",required = true)
    private String provinceCode;

    @NotNull(message = "快递费不能为空")
    @ApiModelProperty(value = "快递费(元)",required = true)
    private BigDecimal express;

    @NotNull(message = "快递方式不能为空")
    @ApiModelProperty(value = "快递方式（1空运，2 陆运,3顺丰到付）",required = true)
    private Integer expressType;

    @NotBlank(message = "快递方式名称不能为空")
    @ApiModelProperty(value = "快递方式名称",required = true)
    private String expressName;

    @ApiModelProperty(value = "状态1有效 0无效")
    private Integer status;

}
