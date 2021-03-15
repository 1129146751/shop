package com.fx.shop.dto.area.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import com.fx.shop.dto.BasePageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *行政机构查询
 */
@Data
@ApiModel(value="行政机构查询", description="行政机构查询")
public class AreaQueryReq extends BasePageReq {
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "区域层级区域层级(0省,1市,2区,3乡镇街道....)")
    private Integer grade;


    @ApiModelProperty(value = "父级区域编码")
    private String parentCode;
}
