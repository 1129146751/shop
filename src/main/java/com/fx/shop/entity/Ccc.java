package com.fx.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.sineyun.commons.base.pojo.BasePoJo;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fx
 * @since 2021-01-19
 */
@Data
@ApiModel(value="Ccc对象", description="")
public class Ccc implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Long pid;


}
