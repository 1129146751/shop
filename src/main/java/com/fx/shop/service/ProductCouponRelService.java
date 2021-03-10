package com.fx.shop.service;

import com.fx.shop.dto.productCouponRel.PcRelReq;
import com.fx.shop.entity.ProductCouponRel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品可用优惠券 服务类
 * </p>
 *
 * @author fx
 * @since 2021-02-04
 */
public interface ProductCouponRelService extends IService<ProductCouponRel> {

    void add(PcRelReq req);
}
