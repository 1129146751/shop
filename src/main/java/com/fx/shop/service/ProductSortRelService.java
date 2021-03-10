package com.fx.shop.service;

import com.fx.shop.dto.productSortRel.PsRelReq;
import com.fx.shop.entity.ProductSortRel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品-分类关系表 服务类
 * </p>
 *
 * @author fx
 * @since 2021-02-04
 */
public interface ProductSortRelService extends IService<ProductSortRel> {

    void add(PsRelReq req);

}
