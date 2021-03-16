//package com.fx.shop.util.jwt;
//
//import cn.hutool.core.util.StrUtil;
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.net.*;
//
///**
// * web相关工具类型
// *
// * @author hjzeng-2015
// */
//@Slf4j
//public class WebUtil {
//    /**
//     * 获取请求request
//     *
//     * @return
//     */
//    public static HttpServletRequest getCurrentRequest() {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes();
//        if (null == requestAttributes) {
//            return null;
//        }
//        HttpServletRequest request = requestAttributes.getRequest();
//
//        return request;
//    }
//
//    /**
//     * 获取响应response
//     *
//     * @return
//     */
//    public static HttpServletResponse getCurrentResponse() {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes();
//        HttpServletResponse response = requestAttributes.getResponse();
//
//        return response;
//    }
//
//
//    /**
//     * 获取当前请求ip地址
//     *
//     * @return
//     */
//    public static String getCurrentRequestIpAddr() {
//        return WebUtil.getClientIpAddr(getCurrentRequest());
//    }
//
//    /**
//     * 获取客户端ip地址
//     *
//     * @param request
//     * @return
//     */
//    public static String getClientIpAddr(HttpServletRequest request) {
//        String ipAddress = request.getHeader("x-forwarded-for");
//        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getHeader("Proxy-Client-IP");
//        }
//        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getRemoteAddr();
//            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
//                //根据网卡取本机配置的IP
//                InetAddress inet = null;
//                try {
//                    inet = InetAddress.getLocalHost();
//                } catch (UnknownHostException e) {
//                    e.printStackTrace();
//                }
//                ipAddress = inet.getHostAddress();
//            }
//        }
//        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
//        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
//            if (ipAddress.indexOf(",") > 0) {
//                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
//            }
//        }
//        return ipAddress;
//    }
//
//    /**
//     * 通过IP获取地址(需要联网，调用淘宝的IP库)
//     *
//     * @param ip
//     * @return
//     */
//    public static String getIpInfo(String ip) {
//        if ("127.0.0.1".equals(ip)) {
//            ip = "127.0.0.1";
//        }
//        String info = "";
//        try {
//            URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
//            HttpURLConnection htpcon = (HttpURLConnection) url.openConnection();
//            htpcon.setRequestMethod("GET");
//            htpcon.setDoOutput(true);
//            htpcon.setDoInput(true);
//            htpcon.setUseCaches(false);
//
//            InputStream in = htpcon.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
//            StringBuffer temp = new StringBuffer();
//            String line = bufferedReader.readLine();
//            while (line != null) {
//                temp.append(line).append("\r\n");
//                line = bufferedReader.readLine();
//            }
//            bufferedReader.close();
//            JSONObject obj = JSONUtil.parseObj(temp.toString());
//            if (obj.getInt("code") == 0) {
//                JSONObject data = obj.getJSONObject("data");
//                info += data.getStr("country") + " ";
//                info += data.getStr("region") + " ";
//                info += data.getStr("city") + " ";
//                info += data.getStr("isp");
//            }
//        } catch (MalformedURLException e) {
//            log.error("获取{}地址信息失败", ip);
//        } catch (ProtocolException e) {
//            log.error("获取{}地址信息失败", ip);
//        } catch (IOException e) {
//            log.error("获取{}地址信息失败", ip);
//        }
//        return info;
//    }
//
//    /**
//     * 获得MAC地址
//     *
//     * @param ip
//     * @return
//     */
//    public static String getMACAddress(String ip) {
//        String str = "";
//        String macAddress = "";
//        try {
//            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
//            InputStreamReader ir = new InputStreamReader(p.getInputStream());
//            LineNumberReader input = new LineNumberReader(ir);
//            for (int i = 1; i < 100; i++) {
//                str = input.readLine();
//                if (str != null) {
//                    if (str.indexOf("MAC Address") > 1) {
//                        macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
//                        break;
//                    }
//                }
//            }
//        } catch (IOException e) {
//            log.error("获取{}MAC地址信息失败", ip);
//        }
//        return macAddress;
//    }
//
//
//
//    public static String getToken(HttpServletRequest request) {
//        String token = null;
//
//        try {
//            if (request == null) {
//                request = getCurrentRequest();
//            }
//
//            token = request.getHeader("token");
//            if (StrUtil.isEmpty(token)) {
//                token = getValue(request.getCookies(), "Authorization");
//            }
//        } catch (Exception var3) {
//            log.error("获取token失败", var3);
//        }
//
//        return token;
//    }
//
//    /**
//     * 从Cookie中获取频道编码channelCode
//     *
//     * @param cookies
//     * @return
//     */
//    public static String getValue(Cookie[] cookies, String key) {
//        String value = null;
//        if (null != cookies && cookies.length > 0) {
//            for (Cookie c : cookies) {
//                if (key.equals(c.getName())) {
//                    value = c.getValue();
//                    break;
//                }
//            }
//        }
//
//        return value;
//    }
//
//}
