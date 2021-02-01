package com.fx.shop.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fx.shop.dto.exFee.req.AddReq;
import com.fx.shop.dto.exFee.req.EditReq;
import com.fx.shop.dto.exFee.req.QueryReq;
import com.fx.shop.dto.exFee.resp.Resp;
import com.fx.shop.entity.ExpressFee;
import com.fx.shop.mapper.ExpressFeeMapper;
import com.fx.shop.service.ExpressFeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sineyun.commons.base.dto.response.PageResp;
import com.sineyun.commons.core.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fx
 * @since 2021-01-22
 */
@Service
public class ExpressFeeServiceImpl extends ServiceImpl<ExpressFeeMapper, ExpressFee> implements ExpressFeeService {
    /**
     * 增加
     * @param req
     */
    @Override
    public void add(AddReq req) {
        String provinceCode=req.getProvinceCode();
        Integer expressType=req.getExpressType();
        QueryWrapper<ExpressFee> checkWrapper=new QueryWrapper<>();
        checkWrapper.eq("province_code",provinceCode);
        checkWrapper.eq("express_type",expressType);
        int count=this.count(checkWrapper);
        if(count>0){
            throw new CustomException("该区域快递方式已经存在,不能重复添加!");
        }
        if(3==expressType){
            req.setExpress(new BigDecimal("0"));
        }
        ExpressFee expressFee=new ExpressFee();
        BeanUtil.copyProperties(req,expressFee);
        expressFee.setCreateTime(new Date());
        this.save(expressFee);
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
        ExpressFee expressFee=new ExpressFee();
        BeanUtil.copyProperties(req,expressFee);
        if(3==expressFee.getExpressType()){
            expressFee.setExpress(new BigDecimal("0"));
        }
        expressFee.setUpdateTime(new Date());
        this.updateById(expressFee);
    }

    /**
     * 查询
     * @param req
     * @return
     */
    @Override
    public PageResp<Resp> query(QueryReq req) {
        IPage<ExpressFee> page=new Page<>(req.getCurrent(),req.getSize());
        QueryWrapper<ExpressFee> queryWrapper=new QueryWrapper<>();
        //TODO 快递类型（1空运，2 陆运,顺丰到付）
        Integer expressType=req.getExpressType();
        if(null!=expressType){
            queryWrapper.eq("express_type",expressType);
        }
        //TODO 省名称/省编码
        String name=req.getName();
        if(StringUtils.isNotBlank(name)){
            queryWrapper.and(wrapper->wrapper.eq("province_name",name).or().eq("province_code",name));
        }
        //TODO 状态1有效 0无效
        Integer status=req.getStatus();
        if(null!=status){
            queryWrapper.eq("status",status);
        }
        page=this.page(page,queryWrapper);
        PageResp<Resp> pageResp=PageResp.convertEntityToDto(page,Resp.class);
        return pageResp;
    }
}
