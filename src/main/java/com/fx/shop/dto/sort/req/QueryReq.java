package com.fx.shop.dto.sort.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import com.fx.shop.dto.BasePageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品分类
 * </p>
 *
 * @author fx
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductSort对象", description="查询商品分类")
public class QueryReq extends BasePageReq {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "商品分类名称")
    private String sortName;

    @ApiModelProperty(value = "状态（1可用，0不可用）")
    private Integer state;



}
