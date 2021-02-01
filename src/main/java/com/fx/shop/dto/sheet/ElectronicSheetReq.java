package com.fx.shop.dto.sheet;


import com.alibaba.fastjson.annotation.JSONField;
import com.fx.shop.dto.appoint.AddService;
import com.fx.shop.dto.appoint.Commodity;
import com.fx.shop.dto.appoint.Receiver;
import com.fx.shop.dto.appoint.Sender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 电子面单API请求参数
 *
 * @author zhangyi
 * @date 2020-8-25
 */
@Data
@ApiModel(value = "电子面单API请求参数", description = "电子面单API请求参数")
public class ElectronicSheetReq {

    private static final long serialVersionUID = 1L;

    /**
     * ERP系统、电商平台等系统或平台类型用户的会员ID或店铺账号等唯一性标识，用于区分其用户
     */
    @ApiModelProperty(value = "ERP系统、电商平台等系统或平台类型用户的会员ID或店铺账号等唯一性标识，用于区分其用户")
    private String MemberID;

    /**
     * 电子面单客户号
     */
    @ApiModelProperty(value = "电子面单客户号")
    private String CustomerName;

    private String CustomerPwd;

    private String SendSite;

    private String SendStaff;

    @ApiModelProperty(value = "月结单号")
    private String MonthCode;

    /**
     * 商家自定义区域
     */
    @ApiModelProperty(value = "商家自定义区域")
    private String CustomArea;

    /**
     * 发货仓编码
     */
    @ApiModelProperty(value = "发货仓编码")
    private String WareHouseID;

    /**
     * 运输方式 1- 陆运 2- 空运 不填默认为1
     */
    @ApiModelProperty(value = "运输方式 1- 陆运 2- 空运 不填默认为1")
    private Integer TransType;

    /**
     * 快递公司编码
     */
    @ApiModelProperty(value = "快递公司编码")
    private String ShipperCode;

    /**
     * 快递单号(仅宅急送可用)
     */
    @ApiModelProperty(value = "快递单号(仅宅急送可用)")
    private String LogisticCode;

    /**
     * 第三方订单号 (ShipperCode为JD且ExpType为1时必填)
     */
    @ApiModelProperty(value = "第三方订单号 (ShipperCode为JD且ExpType为1时必填)")
    private String ThrOrderCode;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String OrderCode;

    /**
     * 邮费支付方式:1-现付，2-到付，3-月结，4-第三方支付
     */
    @ApiModelProperty(value = "邮费支付方式:1-现付，2-到付，3-月结，4-第三方支付")
    private Integer PayType;


    /**
     * 快递类型：1-标准快件
     */
    @ApiModelProperty(value = "快递类型：1-标准快件")
    private String ExpType;

    /**
     * 签收回单：1-需要，0-不需要,默认为0
     */
    @ApiModelProperty(value = "签收回单：1-需要，0-不需要,默认为0")
    private Integer IsReturnSignBill;

    /**
     * 签回单操作要求(如：签名、盖章、身份证复印件等)
     */
    @ApiModelProperty(value = "签回单操作要求(如：签名、盖章、身份证复印件等)")
    private String OperateRequire;

    /**
     * 快递运费
     */
    @ApiModelProperty(value = "快递运费")
    private Double Cost;

    /**
     * 其他费用
     */
    @ApiModelProperty(value = "其他费用")
    private Double OtherCost;

    /**
     * 是否通知快递员上门揽件 0- 通知 1- 不通知 不填则默认为1
     */
    @ApiModelProperty(value = "是否通知快递员上门揽件 0- 通知 1- 不通知 不填则默认为1")
    private Integer IsNotice;

    /**
     * 上门取货时间段:"yyyy-MM-dd HH:mm:ss"格式化，本文中所有时间格式相同
     */
    @ApiModelProperty(value = "上门取货时间段:\"yyyy-MM-dd HH:mm:ss\"格式化，本文中所有时间格式相同")
    private Date StartDate;

    /**
     * 上门取货时间段:"yyyy-MM-dd HH:mm:ss"格式化，本文中所有时间格式相同
     */
    @ApiModelProperty(value = "上门取货时间段:\"yyyy-MM-dd HH:mm:ss\"格式化，本文中所有时间格式相同")
    private Date EndDate;

    /**
     * 物品总重量kg
     */
    @ApiModelProperty(value = "物品总重量kg")
    private Double Weight;

    /**
     * 件数/包裹数
     */
    @ApiModelProperty(value = "件数/包裹数")
    private Integer Quantity;

    /**
     * 物品总体积m3
     */
    @ApiModelProperty(value = "物品总体积m3")
    private Double Volume;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String Remark;

    /**
     * 返回电子面单模板：0-不需要；1-需要
     */
    @ApiModelProperty(value = "返回电子面单模板：0-不需要；1-需要")
    private String IsReturnPrintTemplate;

    /**
     * 是否订阅短信：0-不需要；1-需要
     */
    @ApiModelProperty(value = "是否订阅短信：0-不需要；1-需要")
    private Integer IsSendMessage;

    /**
     * 模板规格(默认的模板无需传值，非默认模板传对应模板尺寸)
     */
    @ApiModelProperty(value = "模板规格(默认的模板无需传值，非默认模板传对应模板尺寸)")
    private String TemplateSize;

    /**
     * 包装类型(快运字段)默认为0； 0- 纸 1- 纤 2- 木 3- 托膜 4- 木托 99-其他
     */
    @ApiModelProperty(value = "包装类型(快运字段)默认为0； 0- 纸 1- 纤 2- 木 3- 托膜 4- 木托 99-其他")
    private Integer PackingType;

    /**
     * 送货方式(快运字段)默认为0； 0- 自提 1- 送货上门（不含上楼） 2- 送货上楼
     */
    @ApiModelProperty(value = "送货方式(快运字段)默认为0； 0- 自提 1- 送货上门（不含上楼） 2- 送货上楼")
    private Integer DeliveryMethod;

    /**
     * 收件人信息
     */
    @ApiModelProperty(value = "收件人信息")
    private com.fx.shop.dto.appoint.Receiver Receiver = new Receiver();

    /**
     * 发件人信息
     */
    @ApiModelProperty(value = "发件人信息")
    private com.fx.shop.dto.appoint.Sender Sender = new Sender();

    /**
     * 增值服务
     */
    @ApiModelProperty(value = "增值服务")
    private com.fx.shop.dto.appoint.AddService[] AddService = new AddService[16];

    /**
     * 商品
     */
    @ApiModelProperty(value = "商品")
    private com.fx.shop.dto.appoint.Commodity[] Commodity = new Commodity[16];

    @JSONField(name = "MemberID")
    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
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

    @JSONField(name = "SendSite")
    public String getSendSite() {
        return SendSite;
    }

    public void setSendSite(String sendSite) {
        SendSite = sendSite;
    }

    @JSONField(name = "SendStaff")
    public String getSendStaff() {
        return SendStaff;
    }

    public void setSendStaff(String sendStaff) {
        SendStaff = sendStaff;
    }

    @JSONField(name = "MonthCode")
    public String getMonthCode() {
        return MonthCode;
    }

    public void setMonthCode(String monthCode) {
        MonthCode = monthCode;
    }

    @JSONField(name = "CustomArea")
    public String getCustomArea() {
        return CustomArea;
    }

    public void setCustomArea(String customArea) {
        CustomArea = customArea;
    }

    @JSONField(name = "WareHouseID")
    public String getWareHouseID() {
        return WareHouseID;
    }

    public void setWareHouseID(String wareHouseID) {
        WareHouseID = wareHouseID;
    }

    @JSONField(name = "TransType")
    public Integer getTransType() {
        return TransType;
    }

    public void setTransType(Integer transType) {
        TransType = transType;
    }

    @JSONField(name = "ShipperCode")
    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String shipperCode) {
        ShipperCode = shipperCode;
    }

    @JSONField(name = "LogisticCode")
    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        LogisticCode = logisticCode;
    }

    @JSONField(name = "ThrOrderCode")
    public String getThrOrderCode() {
        return ThrOrderCode;
    }

    public void setThrOrderCode(String thrOrderCode) {
        ThrOrderCode = thrOrderCode;
    }

    @JSONField(name = "OrderCode")
    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    @JSONField(name = "PayType")
    public Integer getPayType() {
        return PayType;
    }

    public void setPayType(Integer payType) {
        PayType = payType;
    }

    @JSONField(name = "ExpType")
    public String getExpType() {
        return ExpType;
    }

    public void setExpType(String expType) {
        ExpType = expType;
    }

    @JSONField(name = "IsReturnSignBill")
    public Integer getIsReturnSignBill() {
        return IsReturnSignBill;
    }

    public void setIsReturnSignBill(Integer isReturnSignBill) {
        IsReturnSignBill = isReturnSignBill;
    }

    @JSONField(name = "OperateRequire")
    public String getOperateRequire() {
        return OperateRequire;
    }

    public void setOperateRequire(String operateRequire) {
        OperateRequire = operateRequire;
    }

    @JSONField(name = "Cost")
    public Double getCost() {
        return Cost;
    }

    public void setCost(Double cost) {
        Cost = cost;
    }

    @JSONField(name = "OtherCost")
    public Double getOtherCost() {
        return OtherCost;
    }

    public void setOtherCost(Double otherCost) {
        OtherCost = otherCost;
    }

    @JSONField(name = "IsNotice")
    public Integer getIsNotice() {
        return IsNotice;
    }

    public void setIsNotice(Integer isNotice) {
        IsNotice = isNotice;
    }

    @JSONField(name = "StartDate")
    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    @JSONField(name = "EndDate")
    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    @JSONField(name = "Weight")
    public Double getWeight() {
        return Weight;
    }

    public void setWeight(Double weight) {
        Weight = weight;
    }

    @JSONField(name = "Quantity")
    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    @JSONField(name = "Volume")
    public Double getVolume() {
        return Volume;
    }

    public void setVolume(Double volume) {
        Volume = volume;
    }

    @JSONField(name = "Remark")
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    @JSONField(name = "IsReturnPrintTemplate")
    public String getIsReturnPrintTemplate() {
        return IsReturnPrintTemplate;
    }

    public void setIsReturnPrintTemplate(String isReturnPrintTemplate) {
        IsReturnPrintTemplate = isReturnPrintTemplate;
    }

    @JSONField(name = "IsSendMessage")
    public Integer getIsSendMessage() {
        return IsSendMessage;
    }

    public void setIsSendMessage(Integer isSendMessage) {
        IsSendMessage = isSendMessage;
    }

    @JSONField(name = "TemplateSize")
    public String getTemplateSize() {
        return TemplateSize;
    }

    public void setTemplateSize(String templateSize) {
        TemplateSize = templateSize;
    }

    @JSONField(name = "PackingType")
    public Integer getPackingType() {
        return PackingType;
    }

    public void setPackingType(Integer packingType) {
        PackingType = packingType;
    }

    @JSONField(name = "DeliveryMethod")
    public Integer getDeliveryMethod() {
        return DeliveryMethod;
    }

    public void setDeliveryMethod(Integer deliveryMethod) {
        DeliveryMethod = deliveryMethod;
    }

    @JSONField(name = "Receiver")
    public Receiver getReceiver() {
        return Receiver;
    }

    public void setReceiver(Receiver receiver) {
        Receiver = receiver;
    }

    @JSONField(name = "Sender")
    public Sender getSender() {
        return Sender;
    }

    public void setSender(Sender sender) {
        Sender = sender;
    }

    @JSONField(name = "AddService")
    public AddService[] getAddService() {
        return AddService;
    }

    public void setAddService(AddService[] addService) {
        AddService = addService;
    }

    @JSONField(name = "Commodity")
    public Commodity[] getCommodity() {
        return Commodity;
    }

    public void setCommodity(Commodity[] commodity) {
        Commodity = commodity;
    }
}
