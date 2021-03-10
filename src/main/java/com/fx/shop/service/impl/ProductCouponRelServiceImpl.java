package com.fx.shop.service.impl;

import com.fx.shop.dto.productCouponRel.PcRelReq;
import com.fx.shop.entity.Coupon;
import com.fx.shop.entity.ProductCouponRel;
import com.fx.shop.entity.ProductInfo;
import com.fx.shop.mapper.ProductCouponRelMapper;
import com.fx.shop.service.CouponService;
import com.fx.shop.service.ProductCouponRelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fx.shop.service.ProductInfoService;
import com.sineyun.commons.core.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 * 商品可用优惠券 服务实现类
 * </p>
 *
 * @author fx
 * @since 2021-02-04
 */
@Service
public class ProductCouponRelServiceImpl extends ServiceImpl<ProductCouponRelMapper, ProductCouponRel> implements ProductCouponRelService {
    @Autowired
    private ProductInfoService infoService;
    @Autowired
    private CouponService couponService;
    /**
     * 增加
     * @param req
     */
    @Override
    public void add(PcRelReq req) {
        Long productId=req.getProductId();
        ProductInfo info=infoService.getById(productId);
        if(null==info){
            throw new CustomException("该商品信息不存在,请联系管理员");
        }
        //TODO 删除以前 商品对应的优惠券
        Set<Long> couponIds=req.getCouponId();
        this.removeByIds(couponIds);
        for(Long couponId:couponIds){
            Coupon coupon=couponService.getById(couponId);
            if(null==coupon){
                throw new CustomException("错误的优惠券信息,请联系管理员");
            }
            ProductCouponRel productCouponRel=new ProductCouponRel();
            productCouponRel.setProductId(productId);
            productCouponRel.setCouponId(couponId);
            this.save(productCouponRel);
        }
    }
}
