package com.fx.shop.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fx.shop.util.common.RestCode;
import com.fx.shop.util.enums.LogisticsType;
import com.fx.shop.service.QueryService;
import com.fx.shop.util.MD.Md5Util;

import com.fx.shop.util.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class QueryServiceImpl implements QueryService {
    //电商ID
    @Value("${config.id}")
    private String EBusinessID;

    //电商加密私钥，快递鸟提供，注意保管，不要泄漏
    @Value(("${config.key}"))
    private String ApiKey;

    // 即时查询url
    @Value("${config.immediate-url}")
    private String immediateUrl;

    // 物流跟踪url
    @Value("${config.tracking-url}")
    private String trackingUrl;

    // 即时查询reqType
    @Value("${reqType.immediate-query}")
    private String immediateQuery;

    // 即时查询reqType
    @Value("${reqType.logistics-tracking}")
    private String logisticsTracking;


    @Override
    public ApiResult immediateQuery(String orderCode, String customerName, String shipperCode, String logisticCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("OrderCode", orderCode);
        map.put("CustomerName", customerName);
        map.put("ShipperCode", shipperCode);
        map.put("LogisticCode", logisticCode);
        log.info("请求参数：{}", map);
        String param = buildParam(JSONUtil.toJsonStr(map), immediateQuery);
        ApiResult restResult = doHttpRequest(param, immediateUrl);
        if (restResult.getCode() == RestCode.FAIL.code) {
            return restResult;
        }
        JSONObject data = JSONUtil.parseObj(restResult.getData().toString());
        JSONArray traces = JSONUtil.parseArray(data.get("Traces").toString());
        // 时间降序排序
        ListSortTraces(traces);
        List<Map> maps = traces.toList(Map.class);
        for (Map map1 : maps) {
            map1.put("remark",map1.get("Location"));
            map1.remove("Location");
        }
        data.set("Traces", maps);
        return ApiResult.success(data);
    }


    private void ListSortTraces(List list) {
        { //排序方法
            Collections.sort(list,new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        JSONObject t1 = JSONUtil.parseObj(o1.toString());
                        JSONObject t2 = JSONUtil.parseObj(o2.toString());
                        // format.format(o1.getTime()) 表示 date转string类型 如果是string类型就不要转换了
                        Date dt1 = format.parse(t1.get("AcceptTime").toString());
                        Date dt2 = format.parse(t2.get("AcceptTime").toString());
                        // 这是由大向小排序 如果要由小向大转换比较符号就可以
                        if (dt1.getTime() < dt2.getTime()) {
                            return 1;
                        } else if (dt1.getTime() > dt2.getTime()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
            });
        }
    }

    @Override
    public ApiResult logisticsTracking(String orderCode, String shipperCode, String logisticCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("OrderCode", orderCode);
        map.put("ShipperCode", shipperCode);
        map.put("LogisticCode", logisticCode);
        String param = buildParam(JSONUtil.toJsonStr(map), logisticsTracking);
        return doHttpRequest(param, trackingUrl);
    }


    @Override
    public ApiResult commonlyList() {
        LogisticsType[] values = LogisticsType.values();
        List<Map<String, Object>> list = new ArrayList<>();
        for (LogisticsType value : values) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", value.getCode());
            map.put("name", value.getName());
            list.add(map);
        }
        return ApiResult.success(list);
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
