package com.fx.shop.controller.notify;


import com.fx.shop.dto.notify.WxPayNotifyOrderResponse;

public interface WxPayExecutor {
    public final static String RESP_ERROR_MSG = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[ERROR]]></return_msg></xml>";

    /**
     * 执行状态等业务
     * 
     * @param notifyOrder
     */
    void doAction(WxPayNotifyOrderResponse notifyOrder);
}
