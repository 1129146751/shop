package com.fx.shop.util.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * web相关工具类型
 * 
 * @author hjzeng-2015
 *
 */
public final class WebUtil {
    public static String USER_ID_KEY = "USER_ID";

    /**
     * 获取当前登录用户ID
     * 
     * @return
     */
    public static Long getUserId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Object tempUser = request.getAttribute(USER_ID_KEY);
        if (tempUser == null) {
            return null;
        }

        return Long.parseLong(tempUser.toString());
    }

    /**
     * 获取当前客服端ip
     * 
     * @return
     */
    public static String getCurrenClientIp() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        return getClientIpAddr(request);
    }

    /**
     * 获取登录用户IP地址
     * 
     * @param request
     * @return
     */
    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-real-ip");
        if (StringUtils.isBlank(ip)) {
            ip = request.getHeader("X-Forward-For");
        }
        if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase("unknown", ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || StringUtils.equalsIgnoreCase("unknown", ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtils.indexOf(ip, "0:0") != -1) {
            ip = "127.0.0.1";
        }
        return ip;
    }
    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        return request;
    }
}
