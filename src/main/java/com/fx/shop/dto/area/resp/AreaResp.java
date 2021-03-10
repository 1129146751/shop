package com.fx.shop.dto.area.resp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 区域信息表
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Data
@ApiModel(value="ComArea对象", description="区域信息表")
public class AreaResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "区域层级")
    private Integer grade;

    @ApiModelProperty(value = "区域编码")
    private String code;

    @ApiModelProperty(value = "区域名称")
    private String name;

    @ApiModelProperty(value = "父级区域编码")
    private String parentCode;

    @ApiModelProperty(value = "邮政编码")
    private String zipCode;

    @ApiModelProperty(value = "区号")
    private String areaCode;

    @ApiModelProperty(value = "区域简称")
    private String shortName;

    @ApiModelProperty(value = "拼音字母")
    private String pinyin;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "组合名称")
    private String mergerName;


}
