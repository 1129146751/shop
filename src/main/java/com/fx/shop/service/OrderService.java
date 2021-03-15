package com.fx.shop.service;



import com.fx.shop.dto.appoint.AppointReq;
import com.fx.shop.dto.sheet.ElectronicCancelReq;
import com.fx.shop.dto.sheet.ElectronicSheetReq;
import com.fx.shop.dto.visit.VisitReq;
import com.fx.shop.util.result.ApiResult;


/**
 * @author zhangyi
 * @date 2020-8-24
 */
public interface OrderService {

    /**
     * 预约取件 API
     * @return
     */
    ApiResult appointPickUp(AppointReq req);

    /**
     * 上门取件 API
     * @return
     */
    ApiResult visitPickUp(VisitReq req);

    /**
     * 电子面单 API
     * @return
     */
    ApiResult electronicSheet(ElectronicSheetReq req);

    /**
     * 电子面单订单取消 API
     * @return
     */
    ApiResult electronicCancel(ElectronicCancelReq req);
}
