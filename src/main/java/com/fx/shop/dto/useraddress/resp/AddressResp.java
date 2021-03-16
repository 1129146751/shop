package com.fx.shop.dto.useraddress.resp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(value="UserShippingAddress对象", description="用户常用地址返回")
public class AddressResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "所属用户")
    private Long userId;

    @ApiModelProperty(value = "收货人姓名")
    private String consignee;

    @ApiModelProperty(value = "收货人手机号")
    private String consigneePhone;

    @ApiModelProperty(value = "省级名称")
    private String province;

    @ApiModelProperty(value = "省级编码")
    private String provinceCode;

    @ApiModelProperty(value = "市级名称")
    private String city;

    @ApiModelProperty(value = "市级编码")
    private String cityCode;

    @ApiModelProperty(value = "区县级名称")
    private String district;

    @ApiModelProperty(value = "区县级编码")
    private String districtCode;

    @ApiModelProperty(value = "街道/乡镇名称")
    private String street;

    @ApiModelProperty(value = "街道/乡镇编码")
    private String streetCode;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "邮政编码")
    private String zipCode;

    @ApiModelProperty(value = "标签名称")
    private String tagName;

    @ApiModelProperty(value = "是否默认{1:是,0:否}")
    private Integer defaultSign;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime updateTime;


}
