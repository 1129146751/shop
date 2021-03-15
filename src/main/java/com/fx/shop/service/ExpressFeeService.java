package com.fx.shop.service;

import com.fx.shop.dto.PageResp;
import com.fx.shop.dto.exFee.req.AddReq;
import com.fx.shop.dto.exFee.req.EditReq;
import com.fx.shop.dto.exFee.req.QueryReq;
import com.fx.shop.dto.exFee.resp.Resp;
import com.fx.shop.entity.ExpressFee;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fx
 * @since 2021-01-22
 */
public interface ExpressFeeService extends IService<ExpressFee> {

    void add(AddReq req);

    void edit(EditReq req);

    PageResp<Resp> query(QueryReq req);
}
