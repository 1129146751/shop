package com.fx.shop.service.impl;

import com.fx.shop.dto.shopInfo.req.InfoEditReq;
import com.fx.shop.dto.shopInfo.resp.InfoResp;
import com.fx.shop.entity.Info;
import com.fx.shop.mapper.InfoMapper;
import com.fx.shop.service.InfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fx.shop.util.common.ObjectUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fx
 * @since 2021-03-11
 */
@Service
public class InfoServiceImpl extends ServiceImpl<InfoMapper, Info> implements InfoService {

    @Override
    public InfoResp queryDetail() {
        List<Info> list=this.list();
        if(null!=list&&list.size()>0){
            InfoResp infoResp=new InfoResp();
            Info info= list.get(0);
            ObjectUtil.copyProperties(info,infoResp);
            return infoResp;
        }
        return null;
    }

    @Override
    public void edit(InfoEditReq req) {
        Info info=new Info();
        ObjectUtil.copyProperties(req,info);
        this.updateById(info);
    }

    @Override
    public void add(InfoEditReq req) {
        Info info=new Info();
        ObjectUtil.copyProperties(req,info);
        this.save(info);
    }
}
