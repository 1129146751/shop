package com.fx.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fx.shop.dto.sysuser.req.SysUserLonginReq;
import com.fx.shop.entity.SysUser;
import com.fx.shop.mapper.SysUserMapper;
import com.fx.shop.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fx.shop.util.exception.MyException;
import com.fx.shop.util.jwt.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fx
 * @since 2021-03-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    /**
     * 系统用户登录
     * @param req
     */
    @Override
    public Map login(SysUserLonginReq req) {
        String name=req.getSysUserName();
        String pwd=req.getPwd();
        QueryWrapper<SysUser> queryWrapperName=new QueryWrapper<>();
        queryWrapperName.eq("sys_user_name",name);
        int countName=this.count(queryWrapperName);
        if(countName==0){
            throw  new MyException(MyException.ERROR_400,"系统用户不存在，请联系管理员");
        }
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("sys_user_name",name);
        queryWrapper.eq("pwd",pwd);
        List<SysUser> sysUserList=this.list(queryWrapper);
        if(null==sysUserList||sysUserList.size()==0){
            throw  new MyException(MyException.ERROR_400,"系统用户"+name+"密码有误，请联系管理员");
        }
        Map map=new HashMap<>();
        Long sysUserId=sysUserList.get(0).getId();
        //TODO 一天
        long maxAccessExpiration = 24*60*60*1000;
        Map<String, Object> payload = new HashMap<>();
        payload.put("sysUserId",sysUserId.toString());
        String accessToken = JwtUtil.createToken(sysUserId.toString(), payload, maxAccessExpiration);
       /* long maxRefreshExpiration = 6*1000;//五天
        String refreshToken = JwtUtil.createToken(sysUserId.toString(), new HashMap<String, Object>(), maxRefreshExpiration);
        map.put("refreshToken",refreshToken);*/
        map.put("accessToken",accessToken);
        return map;
    }
}
