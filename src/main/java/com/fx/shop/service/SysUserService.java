package com.fx.shop.service;

import com.fx.shop.dto.sysuser.req.SysUserLonginReq;
import com.fx.shop.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fx
 * @since 2021-03-15
 */
public interface SysUserService extends IService<SysUser> {

    Map login(SysUserLonginReq req);
}
