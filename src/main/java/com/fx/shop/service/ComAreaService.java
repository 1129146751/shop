package com.fx.shop.service;

import com.fx.shop.dto.area.req.AreaQueryReq;
import com.fx.shop.dto.area.resp.AreaResp;
import com.fx.shop.entity.ComArea;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sineyun.commons.base.dto.response.PageResp;

/**
 * <p>
 * 区域信息表 服务类
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
public interface ComAreaService extends IService<ComArea> {

    PageResp<AreaResp> query(AreaQueryReq req);
}
