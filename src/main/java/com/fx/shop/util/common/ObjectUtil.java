package com.fx.shop.util.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 对象数据复制工具类
 * 
 * @author hjzeng-2015
 *
 */
@Slf4j
public final class ObjectUtil {
    /**
     * 将表实体对象转为自定义对象
     * 
     * @param page
     * @return
     */
    @SuppressWarnings("all")
    public static <T> List<T> convertEntityToDto(List list, Class<T> clazz) {
        List<T> datas = Lists.newArrayList();
        if (list != null && !list.isEmpty()) {
            for (Object temp : list) {
                T target = null;
                try {
                    target = clazz.newInstance();
                    copyProperties(temp, target);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                datas.add(target);
            }
        }

        return datas;
    }

    /**
     * 将表实体对象转为自定义对象
     * 
     * @param page
     * @param clazz
     * @return
     */
    @SuppressWarnings("all")
    public static <T> Page<T> convertEntityToDto(IPage page, Class<T> clazz) {
        Page<T> datas = new Page<T>();
        datas.setRecords(Lists.newArrayList());
        datas.setCurrent(page.getCurrent());
        datas.setSize(page.getSize());
        datas.setTotal(page.getTotal());
        if (page.getRecords() != null) {
            for (Object temp : page.getRecords()) {
                T target = null;
                try {
                    target = clazz.newInstance();
                    copyProperties(temp, target);
                } catch (Exception e) {
                    log.error("", e);
                }
                datas.getRecords().add(target);
            }
        }

        return datas;
    }

    /**
     * 对象复制属性
     * 
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
