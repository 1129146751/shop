package com.fx.shop.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fx.shop.dto.PageResp;
import com.fx.shop.dto.sort.req.AddReq;
import com.fx.shop.dto.sort.req.EditReq;
import com.fx.shop.dto.sort.req.QueryReq;
import com.fx.shop.dto.sort.resp.Resp;
import com.fx.shop.entity.ProductSort;
import com.fx.shop.mapper.ProductSortMapper;
import com.fx.shop.service.ProductSortService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品分类 服务实现类
 * </p>
 *
 * @author fx
 * @since 2021-01-22
 */
@Service
public class ProductSortServiceImpl extends ServiceImpl<ProductSortMapper, ProductSort> implements ProductSortService {
    /**
     * 增加
     * @param req
     */
    @Override
    public void add(AddReq req) {
        ProductSort sort=new ProductSort();
        BeanUtil.copyProperties(req,sort);
        sort.setCreateTime(new Date());
        this.save(sort);
    }

    /**
     * 修改
     * @param req
     */
    @Override
    public void edit(EditReq req) {
        if(null==req.getId()){
            throw new    RuntimeException("主键不能为空");
        }
        ProductSort sort=new ProductSort();
        BeanUtil.copyProperties(req,sort);
        sort.setUpdateTime(new Date());
        this.updateById(sort);
    }

    /**
     * 查询
     * @param req
     * @return
     */
    @Override
    public PageResp<Resp> query(QueryReq req) {
        IPage<ProductSort> sortIPage=new Page<>(req.getCurrent(),req.getSize());
        QueryWrapper<ProductSort> queryWrapper=new QueryWrapper<>();
        //TODO 商品分类名称
        String sortName=req.getSortName();
        if(StringUtils.isNotBlank(sortName)){
            queryWrapper.like("sort_name",sortName);
        }
        //TODO 状态（1可用，0不可用）
        Integer state=req.getState();
        if(null!=state){
            queryWrapper.eq("state",state);
        }
        sortIPage=this.page(sortIPage,queryWrapper);
        PageResp<Resp> pageResp =PageResp.convertEntityToDto(sortIPage,Resp.class);
        return pageResp;
    }
}
