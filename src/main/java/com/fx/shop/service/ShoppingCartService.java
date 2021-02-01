package com.fx.shop.service;

import com.fx.shop.dto.cart.req.AddReq;
import com.fx.shop.dto.cart.req.DelReq;
import com.fx.shop.dto.cart.resp.Resp;
import com.fx.shop.entity.ShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 购物车 服务类
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
public interface ShoppingCartService extends IService<ShoppingCart> {
    List<Resp> queryCart();

    void add(AddReq req);

    void del(DelReq req);
}
