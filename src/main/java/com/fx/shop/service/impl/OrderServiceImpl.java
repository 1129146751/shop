package com.fx.shop.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fx.shop.util.common.RestCode;
import com.fx.shop.dto.appoint.AppointReq;
import com.fx.shop.dto.sheet.ElectronicCancelReq;
import com.fx.shop.dto.sheet.ElectronicSheetReq;
import com.fx.shop.dto.visit.VisitReq;
import com.fx.shop.service.OrderService;
import com.fx.shop.util.MD.Md5Util;

import com.fx.shop.util.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    //电商ID
    @Value("${config.id}")
    private String EBusinessID;

    //电商加密私钥，快递鸟提供，注意保管，不要泄漏
    @Value(("${config.key}"))
    private String ApiKey;

    // 预约取件url
    @Value("${config.appoint-url}")
    private String appointUrl;

    // 上门取件url
    @Value("${config.visit-url}")
    private String visitUrl;

    // 电子面单url
    @Value("${config.electronic-url}")
    private String electronicUrl;

    // 电子面单取消url
    @Value("${config.electronicCancel-url}")
    private String electronicCancelUrl;

    // 预约取件reqType
    @Value("${reqType.appoint-pick-up}")
    private String appointPickUp;

    // 上门取件reqType
    @Value("${reqType.visit-pick-up}")
    private String visitPickUp;

    //电子面单reqType
    @Value("${reqType.electronic-sheet}")
    private String electronicSheet;

    //电子面单取消reqType
    @Value("${reqType.electronic-cancel}")
    private String electronicCancel;

    @Override
    public ApiResult appointPickUp(AppointReq req) {
        String requestData = JSONUtil.toJsonStr(req);
        log.info("请求参数：{}", requestData);
        String param = buildParam(JSONUtil.toJsonStr(requestData), appointPickUp);
        ApiResult restResult = doHttpRequest(param, appointUrl);
        if (restResult.getCode() == RestCode.FAIL.code) {
            return restResult;
        }
        JSONObject data = JSONUtil.parseObj(restResult.getData().toString());
        return ApiResult.success(data);
    }


    @Override
    public ApiResult visitPickUp(VisitReq req) {
        String requestData = JSONUtil.toJsonStr(req);
        log.info("请求参数：{}", requestData);
        String param = buildParam(JSONUtil.toJsonStr(requestData), visitPickUp);
        ApiResult restResult = doHttpRequest(param, visitUrl);
        if (restResult.getCode() == RestCode.FAIL.code) {
            return restResult;
        }
        JSONObject data = JSONUtil.parseObj(restResult.getData().toString());
        return ApiResult.success(data);
    }

    @Override
    public ApiResult electronicSheet(ElectronicSheetReq req) {
        String requestData = JSONUtil.toJsonStr(req);
        log.info("请求参数：{}", requestData);
        String param = buildParam(JSONUtil.toJsonStr(requestData), electronicSheet);
        ApiResult restResult = doHttpRequest(param, electronicUrl);

        System.out.println(param);
        System.out.println(electronicUrl);
        if (restResult.getCode() == RestCode.FAIL.code) {
            return restResult;
        }
        JSONObject data = JSONUtil.parseObj(restResult.getData().toString());
        return ApiResult.success(data);
    }

    @Override
    public ApiResult electronicCancel(ElectronicCancelReq req) {
        String requestData = JSONUtil.toJsonStr(req);
        log.info("请求参数：{}", requestData);
        String param = buildParam(JSONUtil.toJsonStr(requestData), electronicCancel);
        ApiResult restResult = doHttpRequest(param, electronicCancelUrl);
        if (restResult.getCode() == RestCode.FAIL.code) {
            return restResult;
        }
        JSONObject data = JSONUtil.parseObj(restResult.getData().toString());
        return ApiResult.success(data);
    }

    /**
     * 构建请求参数
     *
     * @param requestData
     * @param apiType
     * @return
     */
    private String buildParam(String requestData, String apiType) {
        Map<String, String> params = new HashMap<>();
        // 请求内容需进行URL(utf-8)编码。请求内容JSON格式，须和DataType一致
        params.put("RequestData", Md5Util.urlEncoder(requestData, "UTF-8"));
        // 商户ID，请在我的服务页面查看。
        params.put("EBusinessID", EBusinessID);
        // 请求指令类型
        params.put("RequestType", apiType);
        String dataSign = Md5Util.encrypt(requestData, ApiKey, "UTF-8");
        // 数据内容签名：把(请求内容(未编码)+AppKey)进行MD5加密，然后Base64编码，最后 进行URL(utf-8)编码。详细过程请查看Demo
        params.put("DataSign", Md5Util.urlEncoder(dataSign, "UTF-8"));
        // 请求、返回数据类型：2-json
        params.put("DataType", "2");
        StringBuilder param = new StringBuilder();
        // 发送请求参数
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (param.length() > 0) {
                    param.append("&");
                }
                param.append(entry.getKey());
                param.append("=");
                param.append(entry.getValue());
            }
        }
        return param.toString();
    }

    /**
     * 发送请求
     *
     * @param param
     */
    private ApiResult doHttpRequest(String param, String url) {
        HttpRequest post = HttpUtil.createPost(url);
        post.header("accept", "*/*");
        post.header("connection", "Keep-Alive");
        post.header("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        post.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        post.body(param);
        log.info("请求的json参数:" + param);
        HttpResponse response = post.execute();
        String body = response.body();
        // 将null替换成空串
        String replace = body.replaceAll("null", "\"\"");
        JSONObject res = JSONUtil.parseObj(replace);
        if (res.get("Success").toString().equals("false")) {
            log.error("请求失败，失败原因：" + res.get("Reason").toString());
            return ApiResult.error(res.get("Reason").toString());
        }
        return ApiResult.success(res);
    }

}
