package com.fx.shop.service.impl;

import com.fx.shop.entity.CustomerUser;
import com.fx.shop.mapper.UserMapper;
import com.fx.shop.service.CustomerUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Service
public class CustomerUserServiceImpl extends ServiceImpl<UserMapper, CustomerUser> implements CustomerUserService {

}
