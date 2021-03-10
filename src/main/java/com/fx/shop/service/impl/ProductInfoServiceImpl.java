package com.fx.shop.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fx.shop.dto.product.req.AddReq;
import com.fx.shop.dto.product.req.EditReq;
import com.fx.shop.dto.product.req.QueryReq;
import com.fx.shop.dto.product.resp.Resp;
import com.fx.shop.entity.*;
import com.fx.shop.mapper.ProductInfoMapper;
import com.fx.shop.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sineyun.commons.base.dto.response.PageResp;
import com.sineyun.commons.core.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author fx
 * @since 2021-01-22
 */
@Slf4j
@Service
@Transactional
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {
    @Autowired
    private ProductSortService sortService;
    @Autowired
    private ProductSortRelService productSortRelService;
    @Autowired
    private ProductCouponRelService productCouponRelService;
    @Autowired
    private CouponService couponService;
    /**
     * 增加
     * @param req
     */
    @Override
    public void add(AddReq req) {
        ProductInfo productInfo=new ProductInfo();
        BeanUtil.copyProperties(req,productInfo);
        productInfo.setCreateTime(new Date());
        this.save(productInfo);
    }

    /**
     * 修改
     * @param req
     */
    @Override
    public void edit(EditReq req) {
        Long productId=req.getId();
        if(null==productId){
            throw new CustomException("主键不能为空!");
        }
        ProductInfo productInfo=new ProductInfo();
        BeanUtil.copyProperties(req,productInfo);
        productInfo.setUpdateTime(new Date());
        this.updateById(productInfo);
    }

    /**
     * 查询
     * @param req
     * @return
     */
    @Override
    public PageResp<Resp> query(QueryReq req) {
        IPage<ProductInfo> page = new Page<>(req.getCurrent(), req.getSize());
        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
        //TODO 商品名称/副标题
        String name=req.getName();
        if(StringUtils.isNotBlank(name)) {
            queryWrapper.and(wrapper -> wrapper.like("name", name).or().like("sub_title", name));
        }
        //TODO 商品属性分类编码
        Set<Long> sortIds=req.getSortIds();
        if(null!=sortIds){
            QueryWrapper<ProductSortRel> psRel=new QueryWrapper<>();
            //TODO 获取商品id
            psRel.in("sort_id",sortIds);
            List<ProductSortRel> relList=productSortRelService.list(psRel);
            Set<Long> pids=new HashSet<>();
            for(ProductSortRel productSortRel:relList){
                pids.add(productSortRel.getProductId());
            }
            if(null==pids||pids.size()==0){
                return null;
            }
            queryWrapper.eq("id",pids);
        }
        //TODO 商品属性分类编码
        Integer publishStatus=req.getPublishStatus();
        if(null!=publishStatus){
            queryWrapper.eq("publish_status",publishStatus);
        }
        queryWrapper.orderByDesc("create_time,sort_no");
        page=this.page(page,queryWrapper);
        PageResp<Resp> pageResp = PageResp.convertEntityToDto(page, Resp.class);
        List<Resp> respList= pageResp.getRecords();
        if (null == respList || respList.size() == 0) {
            return pageResp;
        }
        for(Resp resp:respList){
            QueryWrapper<ProductSortRel> psRel=new QueryWrapper<>();
            psRel.eq("product_id",resp.getId());
            List<ProductSortRel> relList=productSortRelService.list(psRel);
            //TODO 获取分类id
            Set<Long> sortIdss=new HashSet<>();
            for(ProductSortRel productSortRel:relList){
                sortIdss.add(productSortRel.getSortId());
            }

            //TODO 属性名称
            List<ProductSort> productSortList=sortService.listByIds(sortIdss);
            if(null!=productSortList){
                String sortName="";
                for(ProductSort productSort:productSortList){
                    sortName=sortName+","+productSort.getSortName();
                }
                resp.setSortName(StringUtils.isNotBlank(sortName)?sortName.substring(1):sortName);
            }
            QueryWrapper<ProductCouponRel> pcRel=new QueryWrapper<>();
            pcRel.eq("product_id",resp.getId());
            List<ProductCouponRel> pcList=productCouponRelService.list(pcRel);
            //TODO 获取优惠券id
            Set<Long> couponIds=new HashSet<>();
            for(ProductCouponRel productCouponRel:pcList){
                couponIds.add(productCouponRel.getCouponId());
            }
            //TODO 优惠券信息
            List<Coupon>  pcRelList=couponService.listByIds(couponIds);
            if(null!=pcRelList) {
                String coupon="";
                for (Coupon c : pcRelList) {
                    coupon=coupon+","+c.getName();
                }
            }
        }
        return pageResp;
    }

    /**
     * 修改商品对应的分类和优惠券
     */
    public void updateSortCon( Set<Long> sortIds,Set<Long> couponIds,Long productId){
        //TODO 增加商品分类
        if(null!=sortIds&&sortIds.size()>0) {
            //TODO 删除以前的分类
            QueryWrapper<ProductSortRel> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("product_id",productId);
            productSortRelService.remove(queryWrapper);
            for (Long sortId : sortIds) {
                ProductSortRel productSortRel = new ProductSortRel();
                productSortRel.setProductId(productId);
                productSortRel.setSortId(sortId);
                productSortRelService.save(productSortRel);
            }
        }
        //TODO 如果支持优惠券  增加优惠券
        if(null!=couponIds||couponIds.size()>0){
            //TODO 删除以前的优惠券
            QueryWrapper<ProductCouponRel> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("product_id",productId);
            productCouponRelService.remove(queryWrapper);
            for(Long couponId:couponIds){
                ProductCouponRel productCouponRel=new ProductCouponRel();
                productCouponRel.setProductId(productId);
                productCouponRel.setCouponId(couponId);
                productCouponRelService.save(productCouponRel);
            }
        }
    }
}
