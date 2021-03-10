package com.fx.shop.service.impl;

import com.fx.shop.dto.productSortRel.PsRelReq;
import com.fx.shop.entity.ProductInfo;
import com.fx.shop.entity.ProductSort;
import com.fx.shop.entity.ProductSortRel;
import com.fx.shop.mapper.ProductSortRelMapper;
import com.fx.shop.service.ProductInfoService;
import com.fx.shop.service.ProductSortRelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fx.shop.service.ProductSortService;
import com.sineyun.commons.core.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * <p>
 * 商品-分类关系表 服务实现类
 * </p>
 *
 * @author fx
 * @since 2021-02-04
 */
@Service
@Transactional
public class ProductSortRelServiceImpl extends ServiceImpl<ProductSortRelMapper, ProductSortRel> implements ProductSortRelService {
    @Autowired
    private ProductInfoService infoService;
    @Autowired
    private ProductSortService sortService;
    /**
     * 增加
     * @param req
     */
    @Override
    public void add(PsRelReq req) {
        //TODO 商品校验
        Long productId=req.getProductId();
        ProductInfo productInfo=infoService.getById(productId);
        if(null==productInfo){
            throw new CustomException("该商品不存在，请联系管理员!");
        }
        //TODO 先删除商品对应的属性
        Set<Long> sortIds=req.getSortIds();
        sortService.removeByIds(sortIds);
        for(Long sortId:sortIds){
            //TODO 商品属性校验
            ProductSort productSort=sortService.getById(sortId);
            if(null==productSort){
                throw new CustomException("该商品属性信息不存在，请联系管理员!");
            }
            //TODO 商品和属性信息保存
            ProductSortRel productSortRel=new ProductSortRel();
            productSortRel.setProductId(productId);
            productSortRel.setSortId(sortId);
            this.save(productSortRel);
        }
    }

}
