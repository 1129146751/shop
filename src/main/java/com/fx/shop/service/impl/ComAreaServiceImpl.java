package com.fx.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fx.shop.dto.PageResp;
import com.fx.shop.dto.area.req.AreaQueryReq;
import com.fx.shop.dto.area.resp.AreaResp;
import com.fx.shop.entity.ComArea;
import com.fx.shop.mapper.ComAreaMapper;
import com.fx.shop.service.ComAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 区域信息表 服务实现类
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Service
public class ComAreaServiceImpl extends ServiceImpl<ComAreaMapper, ComArea> implements ComAreaService {
    /**
     * 查询区域信息
     * @param req
     * @return
     */
    @Override
    public PageResp<AreaResp> query(AreaQueryReq req) {
        IPage<ComArea> page=new Page<>(req.getCurrent(),req.getSize());
        QueryWrapper<ComArea> queryWrapper=new QueryWrapper<>();
        Long id=req.getId();
        if(null!=id){
            queryWrapper.eq("id",id);
        }
        Integer grade=req.getGrade();
        if(null!=grade){
            queryWrapper.eq("grade",grade);
        }
        String parentCode=req.getParentCode();
        if(StringUtils.isNotBlank(parentCode)){
            queryWrapper.eq("parent_code",parentCode);
        }
        page=this.page(page,queryWrapper);
        PageResp<AreaResp> pageResp=PageResp.convertEntityToDto(page,AreaResp.class);
        return pageResp;
    }
}
