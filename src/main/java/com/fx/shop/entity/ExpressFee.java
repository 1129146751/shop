package com.fx.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author fx
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ExpressFee对象", description="快递费")
public class ExpressFee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "省名称")
    private String provinceName;

    @ApiModelProperty(value = "省编码")
    private String provinceCode;

    @ApiModelProperty(value = "快递费(元)")
    private BigDecimal express;

    @ApiModelProperty(value = "快递类型（1空运，2 陆运,3顺丰到付）")
    private Integer expressType;

    @ApiModelProperty(value = "快递名称")
    private String expressName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "状态1有效 0无效")
    private Integer status;


}
