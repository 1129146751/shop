package com.fx.shop.service;

import com.fx.shop.dto.shopInfo.req.InfoEditReq;
import com.fx.shop.dto.shopInfo.resp.InfoResp;
import com.fx.shop.entity.Info;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fx
 * @since 2021-03-11
 */
public interface InfoService extends IService<Info> {

    InfoResp queryDetail();

    void edit(InfoEditReq req);

    void add(InfoEditReq req);
}
