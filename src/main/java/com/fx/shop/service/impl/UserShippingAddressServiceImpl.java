package com.fx.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fx.shop.dto.useraddress.req.AddressAddReq;
import com.fx.shop.dto.useraddress.req.AddressQueryReq;
import com.fx.shop.dto.useraddress.req.AddresseEditReq;
import com.fx.shop.dto.useraddress.resp.AddressResp;
import com.fx.shop.entity.OrderInfo;
import com.fx.shop.entity.UserShippingAddress;
import com.fx.shop.mapper.UserShippingAddressMapper;
import com.fx.shop.service.UserShippingAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fx.shop.util.common.ObjectUtil;
import com.fx.shop.util.exception.MyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 常用地址 服务实现类
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Service
public class UserShippingAddressServiceImpl extends ServiceImpl<UserShippingAddressMapper, UserShippingAddress> implements UserShippingAddressService {
    /**
     * 增加常用地址
     * @param req
     */
    @Override
    public void add(AddressAddReq req) {
        UserShippingAddress address=new UserShippingAddress();
        ObjectUtil.copyProperties(req,address);
        address.setCreateTime(new Date());
        this.save(address);
    }

    /**
     * 修改常用地址
     * @param req
     */
    @Override
    public void edit(AddresseEditReq req) {
        Long id=req.getId();
        UserShippingAddress address=this.getById(id);
        if(null==address){
            throw new MyException(MyException.ERROR_400,"地址信息有误，请联系管理员");
        }
        address=new UserShippingAddress();
        ObjectUtil.copyProperties(req,address);
        this.updateById(address);
    }

    /**
     * 查询常用地址
     * @param req
     * @return
     */
    @Override
    public  Page<AddressResp> query(AddressQueryReq req) {
        Long id=req.getId();
        Long userId=req.getUserId();
        String consignee=req.getConsignee();
        String consigneePhone=req.getConsigneePhone();
        IPage<UserShippingAddress> page = new Page<>(req.getCurrent(), req.getSize());
        QueryWrapper<UserShippingAddress> queryWrapper=new QueryWrapper<>();
        if(null!=id){
            queryWrapper.eq("id",id);
        }
        if(null!=userId){
            queryWrapper.eq("user_id",userId);
        }
        if(StringUtils.isNotBlank(consignee)){
            queryWrapper.like("consignee",consignee);
        }
        if(StringUtils.isNotBlank(consigneePhone)){
            queryWrapper.like("consignee_phone",consigneePhone);
        }
        page=this.page(page,queryWrapper);
        Page<AddressResp> resPage=ObjectUtil.convertEntityToDto(page,AddressResp.class);
        return resPage;
    }
}
