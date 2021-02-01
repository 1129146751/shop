package com.fx.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fx.shop.entity.Ccc;
import com.fx.shop.mapper.CccMapper;
import com.fx.shop.service.CccService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fx
 * @since 2021-01-19
 */
@Service
public class CccServiceImpl  implements CccService {
    @Autowired
    private CccMapper mapper;
    @Override
    public List getList() {
        QueryWrapper<Ccc> queryWrapper=new QueryWrapper<>();

        List list=mapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public void save() {
        Ccc ccc=new Ccc();
        ccc.setPid(789L);
        mapper.insert(ccc);
    }
}
