package com.fx.shop.dto.appoint;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 发件方API请求参数
 *
 * @author zhangyi
 * @date 2020-8-24
 */
@Data
@ApiModel(value = "发件方API请求参数", description = "发件方API请求参数")
public class Sender {

    private static final long serialVersionUID = 1L;
    // 发件人公司
    @ApiModelProperty(value = "发件人公司")
    private String Company;
    // 发件人
    @ApiModelProperty(value = "发件人")
    private String Name;
    // 电话
    @ApiModelProperty(value = "电话")
    private String Tel;
    // 手机
    @ApiModelProperty(value = "手机")
    private String Mobile;
    // 发件人邮编
    @ApiModelProperty(value = "发件人邮编")
    private String PostCode;
    // 发件省（如广东省，不要缺少“省”）
    @ApiModelProperty(value = "发件省（如广东省，不要缺少“省”）")
    private String ProvinceName;
    // 发件市（如深圳市，不要缺少“市”）
    @ApiModelProperty(value = "发件市（如深圳市，不要缺少“市”）")
    private String CityName;
    // 发件区（如福田区，不要缺少“区”或“县”）
    @ApiModelProperty(value = "发件区（如福田区，不要缺少“区”或“县”）")
    private String ExpAreaName;
    // 发件人详细地址
    @ApiModelProperty(value = "发件人详细地址")
    private String Address;


    @JSONField(name = "Company")
    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }
    @JSONField(name = "Name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    @JSONField(name = "Tel")
    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }
    @JSONField(name = "Mobile")
    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
    @JSONField(name = "PostCode")
    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }
    @JSONField(name = "ProvinceName")
    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }
    @JSONField(name = "CityName")
    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }
    @JSONField(name = "ExpAreaName")
    public String getExpAreaName() {
        return ExpAreaName;
    }

    public void setExpAreaName(String expAreaName) {
        ExpAreaName = expAreaName;
    }
    @JSONField(name = "Address")
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
