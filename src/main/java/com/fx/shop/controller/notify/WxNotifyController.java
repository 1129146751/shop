package com.fx.shop.controller.notify;

import cn.hutool.core.bean.BeanUtil;
import com.fx.shop.dto.notify.WxPayNotifyCheckRequest;
import com.fx.shop.dto.notify.WxPayNotifyOrderResponse;
import com.fx.shop.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@Slf4j
@RequestMapping("notify/")
public class WxNotifyController {
    @Autowired
    private  PayService payService;
    /**
     * 商城订单支付微信回调-购物车
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "{appId}/shop.pay")
    public String shop(@PathVariable("appId") String appId, HttpServletRequest request) {
        return this.handleWxPayRequest(appId, request, new WxPayExecutor() {
            @Override
            public void doAction(WxPayNotifyOrderResponse notifyOrder) {
                log.info("-----------------------商城订单支付微信回调--"+notifyOrder);
                payService.payNotify(notifyOrder);
            }

        });
    }
    /**
     * 处理微信支付回调请求
     *
     * @param appId
     * @param payExecutor
     * @return
     */
    protected String handleWxPayRequest(String appId, HttpServletRequest req, WxPayExecutor payExecutor) {
        try {
            String xmlStr = IOUtils.toString(req.getInputStream(), req.getCharacterEncoding());
            log.debug("微信支付回调参数：{}", xmlStr);

            WxPayNotifyCheckRequest request = new WxPayNotifyCheckRequest();
            request.setAppId(appId);
            request.setXmlStr(xmlStr);
            WxPayNotifyOrderResponse notifyOrder = payService.validateNotifyParam(request);
            if (notifyOrder.isSuccess()) {
                payExecutor.doAction(notifyOrder);
            }
            return notifyOrder.getResultXml();
        } catch (Exception e) {
            log.error("微信支付回调参数校验失败", e);
            return WxPayExecutor.RESP_ERROR_MSG;
        }
    }
}
