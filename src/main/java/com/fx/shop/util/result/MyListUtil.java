package com.fx.shop.util.result;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.json.JSONUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 数组工具
 *
 * @Author jx
 * @Date 2020/11/6 15:37
 */
public class MyListUtil extends ListUtil {

    /**
     * 将数组对象转化为指定类型
     */
    public static <T, V> List<V> convertEntityToDto(List<T> source, Class<V> clazz) {
        if (CollUtil.isNotEmpty(source)) {
            List<V> target = source.stream().map(
                    e -> JSONUtil.toBean(JSONUtil.toJsonStr(e), clazz)
            ).collect(Collectors.toList());

            return target;
        }

        return null;
    }


}
