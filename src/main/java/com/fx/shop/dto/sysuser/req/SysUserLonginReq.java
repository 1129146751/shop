package com.fx.shop.dto.sysuser.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fx
 * @since 2021-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="系统用户登录", description="系统用户登录")
public class SysUserLonginReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "用户名称不能为空!")
    @ApiModelProperty(value = "系统用户名称",required = true)
    private String sysUserName;

    @NotBlank(message = "密码不能为空!")
    @ApiModelProperty(value = "密码",required = true)
    private String pwd;


}
