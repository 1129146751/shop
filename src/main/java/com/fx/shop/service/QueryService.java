package com.fx.shop.service;


import com.sineyun.commons.core.result.ApiResult;

/**
 * @author ly
 * @date 2020-7-20
 */
public interface QueryService {

    /***
     * 即时查询API
     * 用户提供运单号和快递公司，即可查询当前时刻的最新物流轨迹
     * @param orderCode 订单编号    选填
     * @param customerName  选填
     * 京东单号查询时，需要在CustomerName赋值青龙配送编码，且单号是通过该青龙配送编码发货返回的；
     * 顺丰单号查询时，需要在CustomerName赋值寄件人或收件人的手机号后四位数字；
     * @param shipperCode 快递公司编码    必填
     * @param logisticCode 物流单号     必填
     *         State 物流状态：2-在途中,3-签收,4-问题件
     * @return
     */
    ApiResult immediateQuery(String orderCode, String customerName, String shipperCode, String logisticCode);


    /**
     * 物流跟追 订阅 API
     *
     * @param orderCode
     * @param shipperCode
     * @param logisticCode
     * @return
     */
    ApiResult logisticsTracking(String orderCode, String shipperCode, String logisticCode);

    /**
     * 在途监控 API
     *
     * @param orderCode
     * @param shipperCode
     * @param logisticCode
     * @return
     */
//    ApiResult logisticsTracking(String orderCode, String shipperCode, String logisticCode);


    /***
     * 返回常用的快递公司编码
     * @return
     */
    ApiResult commonlyList();
}
