//package com.fx.shop.util.jwt;
//
//import cn.hutool.json.JSONUtil;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.Claim;
//import com.auth0.jwt.interfaces.DecodedJWT;
//
//import com.fx.shop.util.web.WebUtil;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * jwt工具类
// *
// * @author 46105
// */
//@Slf4j
//public final class JwtUtil {
//
//    /**
//     * 密钥
//     */
////    private static final String SECRET = CommonConstant.JWT_SECRET;
//    private static final String SECRET = "3yicloud";
////    private static final String SECRET = "3yicloud-3yicloud3yicloud3yicloud3yicloud3yicloud3yicloud";
//    /**
//     * 默认字段key:exp
//     */
//    private static final String EXP = "exp";
//    /**
//     * 默认字段key:userId
//     */
//    private static final String USERID = "userId";
//    /**
//     * 默认字段key:payload
//     */
//    private static final String PAYLOAD = "payload";
//
//    /**
//     * 过期时间  1天
//     */
//    private static final Long EXPTIME = 24*60*60*1000L;
////    private static final Long EXPTIME = 60 * 1000L;
//
//    /**
//     * 创建token
//     *
//     * @param userId 用户id
//     * @param <T>
//     * @return
//     */
//    public static <T> String createToken(String userId) {
//        Map<String, Object> object = new HashMap<>();
//        return createToken(userId, object, EXPTIME);
//    }
//
//    /**
//     * 创建token
//     *
//     * @param <T>
//     * @param userId 用户id
//     * @param object 加密数据
//     * @return
//     */
//    public static <T> String createToken(String userId, T object) {
//        return createToken(userId, object, EXPTIME);
//    }
//
//    /**
//     * 创建token
//     *
//     * @param userId  用户id
//     * @param object  加密数据
//     * @param maxTime 有效期（毫秒数）
//     * @param <T>
//     * @return
//     */
//    public static <T> String createToken(String userId, T object, long maxTime) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(SECRET);
//            Date exp = new Date(System.currentTimeMillis() + maxTime);
//            String payload = JSONUtil.toJsonStr(object);
//
//            String token = JWT.create()
//                    .withClaim(USERID, userId)
//                    .withClaim(PAYLOAD, payload)
//                    .withExpiresAt(exp)
//                    .sign(algorithm);
//
//            return token;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    /**
//     * 解析校验token
//     *
//     * @param token
//     * @return
//     */
//    public static String getUserId(String token) {
//        Map<String, Claim> claims = getClaims(token);
//        return claims.get(USERID).asString();
//    }
//
//    /**
//     * 解析校验token
//     *
//     * @param token  解密数据
//     * @param tClass 解密类型
//     * @param <T>
//     * @return
//     */
//    public static <T> T getPayload(String token, Class<T> tClass) {
//        Map<String, Claim> claims = getClaims(token);
//        String json = claims.get(PAYLOAD).asString();
//        return JSONUtil.toBean(json, tClass);
//    }
//
//
//    /**
//     * 解析校验token
//     *
//     * @param token
//     * @return
//     */
//    public static Boolean verify(String token) {
//        Map<String, Claim> claims = getClaims(token);
//        return true;
//    }
//
//    /**
//     * 获取载荷
//     *
//     * @param token
//     * @return
//     */
//    public static Map<String, Claim> getClaims(String token) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(SECRET);
//
//            JWTVerifier verifier = JWT.require(algorithm).build();
//
//            DecodedJWT jwt = verifier.verify(token);
//
//
//            Map<String, Claim> claims = jwt.getClaims();
//            long exp = claims.get(EXP).asLong();
//            long currentTimeMillis = System.currentTimeMillis() / 1000L;
//            if (exp < currentTimeMillis) {
//                throw new    RuntimeException( "会话已经过期,请重新登录!");
//            }
//            return claims;
//        } catch (Exception exception) {
//            log.error("非法参数异常,token={}", token);
//            throw new   RuntimeException("非法参数异常,请重新登录!");
//        }
//    }
//
//    public static void main(String[] args) {
////        Map<String,Object> payload = new HashMap<>();
////        payload.put("user","doctor");
////        String token = JwtUtil.createToken("1651561651651651351654",payload);
////        System.out.println(token);
////        String userId = JwtUtil.getUserId(token );
////        HashMap map = JwtUtil.getPayload(token ,HashMap.class);
////        System.out.println(userId);
////        System.out.println(map);
//
////        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXlsb2FkIjoie1wicGhhcm1hY3lJZFwiOjEyODc5MTc2MjMwMzc2NjUyODIsXCJwaGFybWFjeU5hbWVcIjpcIuWPgeS8iuiNr-aIv1wifSIsImV4cCI6MTU5NTkxNDgwMSwidXNlcklkIjoiMTI4NzkxNzYyMzE3MTg4MzAwOSJ9.oqCqqPmX4cfGRZD-vJOx3VllwuwFH4L_pQB3uHz7d4Q";
//        //String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXlsb2FkIjoie1wicGhhcm1hY3lJZFwiOjEyODc5MTc2MjMwMzc2NjUyODIsXCJwaGFybWFjeU5hbWVcIjpcIuWPgeS8iuiNr-aIv1wifSIsImV4cCI6MTU5NTkxNDgwMSwidXNlcklkIjoiMTI4NzkxNzYyMzE3MTg4MzAwOSJ9.oqCqqPmX4cfGRZD-vJOx3VllwuwFH4L_pQB3uHz7d4Q";
//        //String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXlsb2FkIjoie1wicGhhcm1hY3lJZFwiOjEyODY1ODMwNTk5ODE4NjA4NjUsXCJwaGFybWFjeU5hbWVcIjpcIuWPgeS8iuiNr-aIv1wifSIsImV4cCI6MTU5NjAwNTE5MSwidXNlcklkIjoiMTI4NjU4MzA2MDAzMjE5MjUxMyJ9.Yi46YuzO6u9NlbqJsNBUj99TbILjasiZEK_92FGxv80";
//        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXlsb2FkIjoie1wicGhhcm1hY3lJZFwiOjEyODY1ODMwNTk5ODE4NjA4NjUsXCJwaGFybWFjeU5hbWVcIjpcIuWPgeS8iuiNr-aIv1wifSIsImV4cCI6MTU5NjAxMzYyMywidXNlcklkIjoiMTI4NjU4MzA2MDAzMjE5MjUxMyJ9.EwoRw7auRAPk7MUNHu9Wf_9bIGx2qZf72hLhO4bkVrU";
//        Map<String,Object> map = getPayload(token,Map.class);
//        System.out.println(map.toString());
//        System.out.println(JwtUtil.getUserId(token));
//
//    }
//    public static Long getPharmacyId(){
//        String token = WebUtil.getCurrentRequest().getHeader("token");
//        Map<String,Object> payload = getPayload(token, Map.class);
//        return Long.parseLong(payload.get("pharmacyId").toString());
//    }
//    public static String getPharmacyName(){
//        String token = WebUtil.getCurrentRequest().getHeader("token");
//        Map<String,Object> payload = getPayload(token, Map.class);
//        return payload.get("pharmacyName").toString();
////    }
//
//    public static Long getUserId(){
//        String token = WebUtil.getCurrentRequest().getHeader("token");
//        String userId = JwtUtil.getUserId(token);
//        return Long.parseLong(userId);
//    }
//
//}
