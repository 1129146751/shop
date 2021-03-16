package com.fx.shop.dto.useraddress.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户收货地址
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Data
@ApiModel(value="用户常用地址增加", description="用户常用地址增加")
public class AddressAddReq implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "所属用户")
    private Long userId;

    @NotBlank(message = "收货人姓名不能为空")
    @ApiModelProperty(value = "收货人姓名",required = true)
    private String consignee;

    @NotBlank(message = "收货人手机号")
    @ApiModelProperty(value = "收货人手机号",required = true)
    private String consigneePhone;

    @NotBlank(message = "省级名称不能为空")
    @ApiModelProperty(value = "省级名称",required = true)
    private String province;

    @NotBlank(message = "省级编码不能为空")
    @ApiModelProperty(value = "省级编码",required = true)
    private String provinceCode;

    @NotBlank(message = "市级名称不能为空")
    @ApiModelProperty(value = "市级名称",required = true)
    private String city;

    @NotBlank(message = "市级编码不能为空")
    @ApiModelProperty(value = "市级编码",required = true)
    private String cityCode;

    @NotBlank(message = "区县级名称不能为空")
    @ApiModelProperty(value = "区县级名称",required = true)
    private String district;

    @NotBlank(message = "区县级编码不能为空")
    @ApiModelProperty(value = "区县级编码",required = true)
    private String districtCode;

    @NotBlank(message = "街道/乡镇名称不能为空")
    @ApiModelProperty(value = "街道/乡镇名称",required = true)
    private String street;

    @NotBlank(message = "街道/乡镇编码不能为空")
    @ApiModelProperty(value = "街道/乡镇编码",required = true)
    private String streetCode;

    @NotBlank(message = "详细地址不能为空")
    @ApiModelProperty(value = "详细地址",required = true)
    private String address;

    @ApiModelProperty(value = "邮政编码")
    private String zipCode;

    @ApiModelProperty(value = "标签名称")
    private String tagName;

    @ApiModelProperty(value = "是否默认{1:是,0:否}")
    private Integer defaultSign;



}
