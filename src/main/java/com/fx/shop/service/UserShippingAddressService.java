package com.fx.shop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fx.shop.dto.useraddress.req.AddressAddReq;
import com.fx.shop.dto.useraddress.req.AddressQueryReq;
import com.fx.shop.dto.useraddress.req.AddresseEditReq;
import com.fx.shop.dto.useraddress.resp.AddressResp;
import com.fx.shop.entity.UserShippingAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户收货地址 服务类
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
public interface UserShippingAddressService extends IService<UserShippingAddress> {

    void add(AddressAddReq req);

    void edit(AddresseEditReq req);

    Page<AddressResp> query(AddressQueryReq req);
}
