package com.fx.shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 基础分页请求类，所有分页请求dto都要继承它
 *
 * @author jx
 * @date 2020/8/7 17:31
 */
@Data
@ApiModel(value = "基础分页请求类", description = "基础分页请求类")
public class BasePageReq extends BaseReq {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "当前页，默认1", example = "1", position = -2)
    private Long current = 1L;

    @ApiModelProperty(value = "每页数量，默认10", example = "10", position = -1)
    private Long size = 10L;


}
