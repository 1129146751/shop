package com.fx.shop.dto.appoint;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 增值服务API请求参数
 *
 * @author zhangyi
 * @date 2020-8-24
 */
@Data
@ApiModel(value = "增值服务API请求参数", description = "增值服务API请求参数")
public class AddService {

    private static final long serialVersionUID = 1L;
    // 增值服务名称
    @ApiModelProperty(value = "增值服务名称")
    private String Name;
    // 增值服务值
    @ApiModelProperty(value = "增值服务值")
    private String Value;
    // 客户标识（选填）
    @ApiModelProperty(value = "客户标识（选填）")
    private String CustomerID;

    @JSONField(name = "Name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    @JSONField(name = "Value")
    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
    @JSONField(name = "CustomerID")
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }
}
