package com.fx.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fx.shop.dto.PageResp;
import com.fx.shop.dto.order.req.OrderCreateReq;
import com.fx.shop.dto.order.req.OrderEditReq;
import com.fx.shop.dto.order.req.OrderQueryReq;
import com.fx.shop.dto.order.resp.OrderResp;
import com.fx.shop.entity.OrderInfo;


/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
public interface OrderInfoService extends IService<OrderInfo> {

    String create(OrderCreateReq req);

    PageResp<OrderResp> query(OrderQueryReq req);

    void edit(OrderEditReq req);
}
