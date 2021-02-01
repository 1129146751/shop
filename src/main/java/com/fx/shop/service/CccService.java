package com.fx.shop.service;

import com.fx.shop.entity.Ccc;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fx
 * @since 2021-01-19
 */
public interface CccService  {

    List getList();

    void save();
}
