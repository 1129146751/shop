package com.fx.shop.dto.sheet;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 电子面单订单取消API请求参数
 *
 * @author zhangyi
 * @date 2020-11-24
 */
@Data
@ApiModel(value = "电子面单订单取消API请求参数", description = "电子面单订单取消API请求参数")
public class ElectronicCancelReq {

    private static final long serialVersionUID = 1L;


    /**
     * 快递公司编码
     */
    @ApiModelProperty(value = "快递公司编码")
    private String ShipperCode;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String OrderCode;

    /**
     * 快递单号
     */
    @ApiModelProperty(value = "快递单号")
    private String ExpNo;

    /**
     * 电子面单客户号
     */
    @ApiModelProperty(value = "电子面单客户号")
    private String CustomerName;

    /**
     * 电子面单密码
     */
    @ApiModelProperty(value = "电子面单密码")
    private String CustomerPwd;

    @JSONField(name = "ShipperCode")
    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String shipperCode) {
        ShipperCode = shipperCode;
    }

    @JSONField(name = "OrderCode")
    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    @JSONField(name = "ExpNo")
    public String getExpNo() {
        return ExpNo;
    }

    public void setExpNo(String expNo) {
        ExpNo = expNo;
    }

    @JSONField(name = "CustomerName")
    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    @JSONField(name = "CustomerPwd")
    public String getCustomerPwd() {
        return CustomerPwd;
    }

    public void setCustomerPwd(String customerPwd) {
        CustomerPwd = customerPwd;
    }
}
