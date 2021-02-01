package com.fx.shop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fx.shop.dto.sort.req.AddReq;
import com.fx.shop.dto.sort.req.EditReq;
import com.fx.shop.dto.sort.req.QueryReq;
import com.fx.shop.dto.sort.resp.Resp;
import com.fx.shop.entity.ProductSort;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sineyun.commons.base.dto.response.PageResp;

/**
 * <p>
 * 商品分类 服务类
 * </p>
 *
 * @author fx
 * @since 2021-01-22
 */
public interface ProductSortService extends IService<ProductSort> {

    void add(AddReq req);

    void edit(EditReq req);

    PageResp<Resp> query(QueryReq req);
}
