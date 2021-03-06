package com.fx.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fx.shop.dto.notify.WxPayNotifyCheckRequest;
import com.fx.shop.dto.notify.WxPayNotifyOrderResponse;
import com.fx.shop.dto.pay.req.CreatePayReq;
import com.fx.shop.dto.pay.req.WxPayRefundReq;
import com.fx.shop.dto.pay.resp.CreatePayResp;
import com.fx.shop.dto.wx.WeixinParam;
import com.fx.shop.entity.CustomerUser;
import com.fx.shop.entity.OrderInfo;
import com.fx.shop.service.CustomerUserService;
import com.fx.shop.service.OrderInfoService;
import com.fx.shop.service.PayService;
import com.fx.shop.service.WXService;
import com.fx.shop.util.state.StateUtil;
import com.fx.shop.util.web.WebUtil;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private WeixinParam weixinParam;
    @Autowired
    private WXService wxService;
    @Autowired
    private OrderInfoService orderService;
    @Autowired
    private CustomerUserService userService;
    /**
     * 创建预支付
     * @param request
     * @return
     */
    @Override
    public CreatePayResp createWxPay(CreatePayReq request) throws Exception{
        String orderNo=request.getOrderNo();
        QueryWrapper<OrderInfo>  infoQueryWrapper=new QueryWrapper<>();
        infoQueryWrapper.eq("order_no",orderNo);
        OrderInfo orderInfo=orderService.getOne(infoQueryWrapper);
        if(null!=orderInfo){
            throw new    RuntimeException("订单不存在,请联系管理员!");
        }
        if(StateUtil.orderStatus.os0!=orderInfo.getOrderStatus()){
            throw new    RuntimeException("订单不存在,请联系管理员!");
        }
        CustomerUser user=userService.getById(orderInfo.getUserId());
        if(null==user){
            throw  new    RuntimeException("错误的登录人信息,请联系管理员!");
        }
        WxPayUnifiedOrderRequest payUnifiedOrder = new WxPayUnifiedOrderRequest();
        payUnifiedOrder.setBody("订单支付");
        payUnifiedOrder.setOutTradeNo(orderNo);
        payUnifiedOrder.setTotalFee(orderInfo.getPayAmount().intValue());
        payUnifiedOrder.setSpbillCreateIp(WebUtil.getCurrenClientIp());
        payUnifiedOrder.setNotifyUrl("https://pay-test.3yicloud.com/notify/wxpay/wx7372ceb4c0f2de60/shop.pay");
        payUnifiedOrder.setTradeType(WxPayConstants.TradeType.JSAPI);
        payUnifiedOrder.setOpenid(user.getOpenId());
        WxPayMpOrderResult result= wxService.getWxPayService(weixinParam.getAppId()).createOrder(payUnifiedOrder);
        log.info("-------------------result-"+result);
        CreatePayResp resp=new CreatePayResp();
        resp.setAppId(result.getAppId());
        resp.setNonceStr(result.getNonceStr());
        resp.setPackage_(result.getPackageValue());
        resp.setPaySign(result.getPaySign());
        resp.setSignType(result.getSignType());
        resp.setTimeStamp(result.getTimeStamp());
        return resp;


    }

    /**
     * 微信退款
     * @param req
     */
    @Override
    public void wxRefund(WxPayRefundReq req) throws WxPayException {
        String orderNo=req.getOrderNo();
       WxPayRefundRequest payRefundRequest = new WxPayRefundRequest();
        QueryWrapper<OrderInfo>  infoQueryWrapper=new QueryWrapper<>();
        infoQueryWrapper.eq("order_no",orderNo);
        OrderInfo orderInfo=orderService.getOne(infoQueryWrapper);
        if(null==orderInfo){
            throw  new    RuntimeException("错误的订单信息,请联系管理员!");
        }

        CustomerUser user=userService.getById(orderInfo.getUserId());
        if(null==user){
            throw  new    RuntimeException("错误的登录人信息,请联系管理员!");
        }
        Integer totalFee=new BigDecimal(orderInfo.getPayAmount().toString()).multiply(new BigDecimal("100")).intValue();
        payRefundRequest.setOutTradeNo(orderNo);
        payRefundRequest.setOutRefundNo(orderNo);
        payRefundRequest.setTotalFee(totalFee);
        payRefundRequest.setRefundFee(totalFee);
        payRefundRequest.setRefundFeeType("CNY");
        payRefundRequest.setOpUserId("管理员");
        payRefundRequest.setRefundAccount("");
        payRefundRequest.setRefundDesc("同一客户退款");
        payRefundRequest.setNotifyUrl("");
        log.debug("refund param==>{}", payRefundRequest);
        WxPayRefundResult result = wxService.getWxPayService(user.getAppId()).refund(payRefundRequest);
        log.debug("refund result==>{}", result);
        String msg=result.getReturnMsg();
        if(!"SUCCESS".equalsIgnoreCase(msg)){
            throw  new    RuntimeException("退款失败,请联系管理员!");
        }
    }
    /**
     * 微信支付回调参数解析校验接口
     *
     * @param request
     */
    @Override
    public WxPayNotifyOrderResponse validateNotifyParam(WxPayNotifyCheckRequest request) {
        if (request == null || StringUtils.isBlank(request.getAppId())) {
            throw new    RuntimeException( "appId为空");
        }
        if (StringUtils.isBlank(request.getXmlStr())) {
            throw new    RuntimeException("xmlStr为空");
        }

        WxPayNotifyOrderResponse resp = new WxPayNotifyOrderResponse();
        try {
            WxPayOrderNotifyResult result = wxService.getWxPayService(request.getAppId())
                    .parseOrderNotifyResult(request.getXmlStr());

            resp.setOrderNo(result.getOutTradeNo());
            resp.setTotalFee(result.getTotalFee().longValue());
            resp.setTransactionId(result.getTransactionId());
            resp.setSuccess(true);
            resp.setResultXml(WxPayNotifyResponse.success("OK"));
        } catch (Exception e) {
            log.error("微信回调结果异常,异常原因{}", e.getMessage());
            resp.setSuccess(false);
            resp.setResultXml(WxPayNotifyResponse.fail("ERROR"));
        }

        return resp;
    }

    /**
     * 支付成功回调
     * @param notifyOrder
     */
    @Override
    public void payNotify(WxPayNotifyOrderResponse notifyOrder) {
        String orderNo=notifyOrder.getOrderNo();
        QueryWrapper<OrderInfo> infoQueryWrapper=new QueryWrapper<>();
        infoQueryWrapper.eq("order_no",orderNo);
        OrderInfo orderInfo=orderService.getOne(infoQueryWrapper);
        if(null==orderInfo){
            throw new    RuntimeException("错误的订单信息,请联系管理员!");
        }
        Integer orderStatus=orderInfo.getOrderStatus();
        //TODO 已支付
        if(StateUtil.orderStatus.os1==orderStatus){
            log.info("------------订单已经支付"+orderNo);
            return;
        }
        //TODO  待支付
        if(StateUtil.orderStatus.os0!=orderStatus){
            throw new    RuntimeException("错误的订单状态,请联系管理员!");
        }
        String tradeNo=notifyOrder.getTransactionId();
        OrderInfo info=new OrderInfo();
        info.setId(orderInfo.getId());
        info.setOrderStatus(StateUtil.orderStatus.os1);
        info.setPayType(StateUtil.payType.pt1);
        info.setTradeNo(tradeNo);
        info.setPayTime(new Date());
        orderService.updateById(info);
    }
}
