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
import com.fx.shop.entity.ProductInfo;
import com.fx.shop.entity.ProductSort;
import com.fx.shop.mapper.ProductInfoMapper;
import com.fx.shop.service.ProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fx.shop.service.ProductSortService;
import com.sineyun.commons.base.dto.response.PageResp;
import com.sineyun.commons.core.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
        if(null==req.getId()){
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
        String sortCode=req.getSortCode();
        if(StringUtils.isNotBlank(sortCode)){
            queryWrapper.like("sort_code",sortCode);
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
            QueryWrapper<ProductSort> sortWrapper=new QueryWrapper<>();
            sortWrapper.select("sort_name");
            sortWrapper.eq("sort_code",resp.getSortCode());
            ProductSort productSort=sortService.getOne(sortWrapper);
            if(null!=productSort){
                resp.setSortName(productSort.getSortName());
            }
        }
        return pageResp;
    }

}
