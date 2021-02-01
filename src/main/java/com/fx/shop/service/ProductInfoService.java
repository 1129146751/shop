package com.fx.shop.service;

import com.fx.shop.dto.product.req.AddReq;
import com.fx.shop.dto.product.req.EditReq;
import com.fx.shop.dto.product.req.QueryReq;
import com.fx.shop.dto.product.resp.Resp;
import com.fx.shop.entity.ProductInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sineyun.commons.base.dto.response.PageResp;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author fx
 * @since 2021-01-22
 */
public interface ProductInfoService extends IService<ProductInfo> {

    void add(AddReq req);

    void edit(EditReq req);

    PageResp<Resp> query(QueryReq req);
}
