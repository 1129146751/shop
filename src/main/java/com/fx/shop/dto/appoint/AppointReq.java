package com.fx.shop.dto.appoint;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 预约取件API请求参数
 *
 * @author zhangyi
 * @date 2020-8-24
 */
@Data
@ApiModel(value = "预约取件API请求参数", description = "预约取件API请求参数")
public class AppointReq {

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
     * 快递公司编码
     */
    @ApiModelProperty(value = "快递公司编码")
    private String ShipperCode;

    /**
     * 快递单号
     */
    @ApiModelProperty(value = "快递单号")
    private String LogisticCode;

    /**
     * 发货方式：0-上门揽件，1-网点自寄，默认为1
     */
    @ApiModelProperty(value = "发货方式：0-上门揽件，1-网点自寄，默认为1")
    private Integer IsNotice;

    /**
     * 包装类型：包装类型(快运字段)默认为 0； 0- 纸 1- 纤 2- 木 3- 托膜 4- 木托 99-其他
     */
    @ApiModelProperty(value = "包装类型：包装类型(快运字段)默认为 0； 0- 纸 1- 纤 2- 木 3- 托膜 4- 木托 99-其他")
    private Integer PackingType;

    /**
     * 签收回单：1-需要，0-不需要,默认为0
     */
    @ApiModelProperty(value = "签收回单：1-需要，0-不需要,默认为0")
    private Integer IsReturnSignBill;

    /**
     * 送货方式：0-自提，1-送货上门（不含上楼）2-送货上楼。（适用于快运类型订单，物流公司可能会收取费用），默认为0
     */
    @ApiModelProperty(value = "送货方式：0-自提，1-送货上门（不含上楼）2-送货上楼。（适用于快运类型订单，物流公司可能会收取费用），默认为0")
    private Integer DeliveryMethod;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String OrderCode;

    /**
     * 月结编码
     */
    @ApiModelProperty(value = "月结编码")
    private String MonthCode;

    /**
     * 邮费支付方式:1-现付，2-到付，3-月结，4-第三方支付
     */
    @ApiModelProperty(value = "邮费支付方式:1-现付，2-到付，3-月结，4-第三方支付")
    private Integer PayType;

    /**
     * 快递类型：1-标准快件
     */
    @ApiModelProperty(value = "快递类型：1-标准快件")
    private Integer ExpType;

    /**
     * 寄件费（运费）
     */
    @ApiModelProperty(value = "寄件费（运费）")
    private Double Cost;

    /**
     * 其他费用
     */
    @ApiModelProperty(value = "其他费用")
    private Double OtherCost;

    /**
     * 收件人信息
     */
    @ApiModelProperty(value = "收件人信息")
    private Receiver Receiver = new Receiver();

    /**
     * 发件人信息
     */
    @ApiModelProperty(value = "发件人信息")
    private Sender Sender = new Sender();

    /**
     * 增值服务
     */
    @ApiModelProperty(value = "增值服务")
    private AddService[] AddService = new AddService[16];

    /**
     * 商品
     */
    @ApiModelProperty(value = "商品")
    private Commodity[] Commodity = new Commodity[16];

//    @Data
//    public static class Receiver{
//        private static final long serialVersionUID = 1L;
//        // 收件人公司
//        private String company;
//        // 收件人
//        private String name;
//        // 电话
//        private String tel;
//        // 手机
//        private String mobile;
//        // 收件人邮编
//        private String postCode;
//        // 收件省（如广东省，不要缺少“省”）
//        private String provinceName;
//        // 收件市（如深圳市，不要缺少“市”）
//        private String cityName;
//        // 收件区（如福田区，不要缺少“区”或“县”）
//        private String expAreaName;
//        // 收件人详细地址
//        private String address;
//
//    }

//    @Data
//    public static class Sender{
//        private static final long serialVersionUID = 1L;
//        // 发件人公司
//        private String company;
//        // 发件人
//        private String name;
//        // 电话
//        private String tel;
//        // 手机
//        private String mobile;
//        // 发件人邮编
//        private String postCode;
//        // 发件省（如广东省，不要缺少“省”）
//        private String provinceName;
//        // 发件市（如深圳市，不要缺少“市”）
//        private String cityName;
//        // 发件区（如福田区，不要缺少“区”或“县”）
//        private String expAreaName;
//        // 发件人详细地址
//        private String address;
//    }

//    @Data
//    public static class AddService{
//        private static final long serialVersionUID = 1L;
//        // 增值服务名称
//        private String name;
//        // 增值服务值
//        private String value;
//        // 客户标识（选填）
//        private String customerID;
//    }

//    @Data
//    public static class Commodity{
//        private static final long serialVersionUID = 1L;
//        // 商品名称
//        private String goodsName;
//        // 商品编码
//        private String goodsCode;
//        // 商品数量
//        private Integer goodsquantity;
//        // 商品价格
//        private BigDecimal goodsPrice;
//        // 商品重量
//        private BigDecimal goodsWeight;
//        // 商品描述
//        private String goodsDesc;
//        // 商品体积
//        private Double goodsVol;
//    }

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
    private String Remark ;


    @JSONField(name = "Receiver")
    public Receiver getReceiver() {
        return Receiver;
    }

    public void setReceiver(Receiver receiver) {
        Receiver = receiver;
    }

    @JSONField(name = "ShipperCode")
    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String shipperCode) {
        ShipperCode = shipperCode;
    }
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
    @JSONField(name = "LogisticCode")
    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        LogisticCode = logisticCode;
    }
    @JSONField(name = "IsNotice")
    public Integer getIsNotice() {
        return IsNotice;
    }

    public void setIsNotice(Integer isNotice) {
        IsNotice = isNotice;
    }
    @JSONField(name = "PackingType")
    public Integer getPackingType() {
        return PackingType;
    }

    public void setPackingType(Integer packingType) {
        PackingType = packingType;
    }
    @JSONField(name = "IsReturnSignBill")
    public Integer getIsReturnSignBill() {
        return IsReturnSignBill;
    }

    public void setIsReturnSignBill(Integer isReturnSignBill) {
        IsReturnSignBill = isReturnSignBill;
    }
    @JSONField(name = "DeliveryMethod")
    public Integer getDeliveryMethod() {
        return DeliveryMethod;
    }

    public void setDeliveryMethod(Integer deliveryMethod) {
        DeliveryMethod = deliveryMethod;
    }
    @JSONField(name = "OrderCode")
    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }
    @JSONField(name = "MonthCode")
    public String getMonthCode() {
        return MonthCode;
    }

    public void setMonthCode(String monthCode) {
        MonthCode = monthCode;
    }
    @JSONField(name = "PayType")
    public Integer getPayType() {
        return PayType;
    }

    public void setPayType(Integer payType) {
        PayType = payType;
    }
    @JSONField(name = "ExpType")
    public Integer getExpType() {
        return ExpType;
    }

    public void setExpType(Integer expType) {
        ExpType = expType;
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
}
