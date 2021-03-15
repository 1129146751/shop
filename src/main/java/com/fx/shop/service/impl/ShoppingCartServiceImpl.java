package com.fx.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fx.shop.dto.cart.req.AddReq;
import com.fx.shop.dto.cart.req.DelReq;
import com.fx.shop.dto.cart.resp.Resp;
import com.fx.shop.entity.ProductInfo;
import com.fx.shop.entity.ShoppingCart;
import com.fx.shop.mapper.ShoppingCartMapper;
import com.fx.shop.service.ProductInfoService;
import com.fx.shop.service.ShoppingCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fx.shop.util.common.ObjectUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    @Autowired
    private ProductInfoService productService;
    /**
     * 查询
     * @return
     */
    @Override
    public List<Resp> queryCart(Long userId) {
        QueryWrapper<ShoppingCart> cartWrapper=new QueryWrapper<>();
        cartWrapper.select("product_id");
        cartWrapper.eq("user_id",userId);
        List<ShoppingCart> cartList= this.list(cartWrapper);
        Map<Long,ShoppingCart> cartMap=new HashMap<>();
        Set<Long> ids=new HashSet<>();
        for(ShoppingCart cart:cartList){
            Long pid=cart.getProductId();
            ids.add(pid);
            cartMap.put(pid,cart);
        }
        List<ProductInfo> productInfos=productService.listByIds(ids);
        List<Resp> respList=ObjectUtil.convertEntityToDto(productInfos, Resp.class);
        for(Resp resp:respList){
            Long id=resp.getId();
            ShoppingCart cart=cartMap.get(id);
            resp.setNum(cart.getNum());
        }

        return respList;
    }

    /**
     * 增加
     * @param req
     */
    @Override
    public void add(AddReq req) {
        Long userId=req.getUserId();
        Long pid=req.getPid();
        Integer addNum=req.getNum();
        QueryWrapper<ShoppingCart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("product_id",pid);
        ShoppingCart shoppingCart=this.getOne(queryWrapper);
        //TODO 购物车存在该商品
        if(null!=shoppingCart){
            Integer newNum=shoppingCart.getNum();
            shoppingCart.setNum(addNum+newNum);
            this.updateById(shoppingCart);
        }else {
            ShoppingCart addCart=new ShoppingCart();
            addCart.setUserId(userId);
            addCart.setProductId(pid);
            addCart.setNum(addNum);
            this.save(addCart);
        }

    }

    /**
     *
     * @param req
     */
    @Override
    public void del(DelReq req) {
        Long id=req.getId();
        Integer delType=req.getDelType();
        ShoppingCart cart=this.getById(id);
        if(null==cart){
            throw  new    RuntimeException("该商品不存在,请联系管理员!");
        }
       if(2==delType){
           this.removeById(id);
       }else {
           Integer newNum=cart.getNum();
           if(1==newNum){
               this.removeById(id);
           }else{
               cart.setNum(newNum-1);
               this.updateById(cart);
           }
       }
    }
}
