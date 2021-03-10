package com.fx.shop.dto.notify;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信支付回调解析校验响应实体
 * 
 * @author hjzeng-2015
 *
 */
@Data
public class WxPayNotifyOrderResponse implements Serializable {

    private static final long serialVersionUID = -6314131201069625122L;

    /**
     * 小程序AppId
     */
    private String appId;

    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 微信事务Id
     */
    private String transactionId;
    /**
     * 交易金额
     */
    private Long totalFee;

    /**
     * 结果xml
     */
    private String resultXml;

    /**
     * 成功true，失败false
     */
    private boolean success;
}
