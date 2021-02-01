package com.fx.shop.dto.visit;


import com.alibaba.fastjson.annotation.JSONField;
import com.fx.shop.dto.appoint.AddService;
import com.fx.shop.dto.appoint.Commodity;
import com.fx.shop.dto.appoint.Receiver;
import com.fx.shop.dto.appoint.Sender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 上门取件API请求参数
 *
 * @author zhangyi
 * @date 2020-8-25
 */
@Data
@ApiModel(value = "上门取件API请求参数", description = "上门取件API请求参数")
public class VisitReq {

    private static final long serialVersionUID = 1L;
    /**
     * 仓库标识
     */
    @ApiModelProperty(value = "仓库标识")
    private String WarehouseID;

    /**
     * 仓库地址
     */
    @ApiModelProperty(value = "仓库地址")
    private String WarehouseAddress;

    /**
     * 商户标识
     */
    @ApiModelProperty(value = "商户标识")
    private String CallBack;

    /**
     * 会员标识
     */
    @ApiModelProperty(value = "会员标识")
    private String MemberID;

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
     * 月结编码
     */
    @ApiModelProperty(value = "月结编码")
    private String MonthCode;

    /**
     * 快递类型：1-标准快件
     */
    @ApiModelProperty(value = "快递类型：1-标准快件")
    private Integer ExpType;

    /**
     * 签收回单：1-需要，0-不需要,默认为0
     */
    @ApiModelProperty(value = "签收回单：1-需要，0-不需要,默认为0")
    private Integer IsReturnSignBill;

    /**
     * 包装类型：包装类型(快运字段)默认为 0； 0- 纸 1- 纤 2- 木 3- 托膜 4- 木托 99-其他
     */
    @ApiModelProperty(value = "包装类型：包装类型(快运字段)默认为 0； 0- 纸 1- 纤 2- 木 3- 托膜 4- 木托 99-其他")
    private Integer PackingType;

    /**
     * 送货方式：0-自提，1-送货上门（不含上楼）2-送货上楼。（适用于快运类型订单，物流公司可能会收取费用），默认为0
     */
    @ApiModelProperty(value = "送货方式：0-自提，1-送货上门（不含上楼）2-送货上楼。（适用于快运类型订单，物流公司可能会收取费用），默认为0")
    private Integer DeliveryMethod;

    /**
     * 发件人详细地址
     */
    @ApiModelProperty(value = "发件人详细地址")
    private String SenderShowAddress;

    /**
     * 上门取货时间段:"yyyy-MM-dd HH:mm:ss"格式化，本文中所有时间格式相同
     */
    @ApiModelProperty(value = "上门取货时间段:\"yyyy-MM-dd HH:mm:ss\"格式化，本文中所有时间格式相同")
    private String StartDate;

    /**
     * 上门取货时间段:"yyyy-MM-dd HH:mm:ss"格式化，本文中所有时间格式相同
     */
    @ApiModelProperty(value = "上门取货时间段:\"yyyy-MM-dd HH:mm:ss\"格式化，本文中所有时间格式相同")
    private String EndDate;

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

    @JSONField(name = "WarehouseID")
    public String getWarehouseID() {
        return WarehouseID;
    }

    public void setWarehouseID(String warehouseID) {
        WarehouseID = warehouseID;
    }

    @JSONField(name = "WarehouseAddress")
    public String getWarehouseAddress() {
        return WarehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        WarehouseAddress = warehouseAddress;
    }

    @JSONField(name = "CallBack")
    public String getCallBack() {
        return CallBack;
    }

    public void setCallBack(String callBack) {
        CallBack = callBack;
    }

    @JSONField(name = "MemberID")
    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
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

    @JSONField(name = "MonthCode")
    public String getMonthCode() {
        return MonthCode;
    }

    public void setMonthCode(String monthCode) {
        MonthCode = monthCode;
    }

    @JSONField(name = "ExpType")
    public Integer getExpType() {
        return ExpType;
    }

    public void setExpType(Integer expType) {
        ExpType = expType;
    }

    @JSONField(name = "IsReturnSignBill")
    public Integer getIsReturnSignBill() {
        return IsReturnSignBill;
    }

    public void setIsReturnSignBill(Integer isReturnSignBill) {
        IsReturnSignBill = isReturnSignBill;
    }

    @JSONField(name = "SenderShowAddress")
    public String getSenderShowAddress() {
        return SenderShowAddress;
    }

    public void setSenderShowAddress(String senderShowAddress) {
        SenderShowAddress = senderShowAddress;
    }

    @JSONField(name = "StartDate")
    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    @JSONField(name = "EndDate")
    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
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

    @JSONField(name = "Sender")
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
}
