package com.fx.shop.service.impl;

import com.fx.shop.entity.OrderInfo;
import com.fx.shop.mapper.OrderInfoMapper;
import com.fx.shop.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

}
