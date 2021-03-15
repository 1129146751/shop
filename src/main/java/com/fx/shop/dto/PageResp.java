package com.fx.shop.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fx.shop.util.result.MyListUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 分页数据对象返回类
 *
 * @author jx
 * @date 2020/8/7 17:31
 */
@Slf4j
@Data
@ApiModel(value = "分页数据对象返回类", description = "分页数据对象返回类")
public class PageResp<T> extends BaseDTO {

    @ApiModelProperty(value = "当前的页数", position = 1)
    private Integer current;

    @ApiModelProperty(value = "每页记录数", position = 2)
    private Integer size;

    @ApiModelProperty(value = "总记录", position = 3)
    private Integer total;

    @ApiModelProperty(value = "总页数", position = 4)
    private Integer totalPage;

    @ApiModelProperty(value = "对象列表", position = 5)
    private List<T> records;


    /**
     * 将MyBatis Plus 分页结果转化为通用结果
     */
    public static <T> PageResp<T> convertPage(IPage<T> page) {
        PageResp<T> resp = new PageResp<>();
        if (CollUtil.isNotEmpty(page.getRecords())) {
            BeanUtil.copyProperties(page, resp);
        }

        resp.setCurrent(Convert.toInt(page.getCurrent()));
        resp.setSize(Convert.toInt(page.getSize()));
        resp.setTotal(Convert.toInt(page.getTotal()));
        resp.setTotalPage(Convert.toInt(page.getPages()));

        return resp;
    }

    /**
     * 将MyBatis Plus 分页结果转化为通用结果，指定返回类型
     */
    public static <T, V> PageResp<V> convertEntityToDto(IPage<T> page, Class<V> clazz) {
        PageResp<V> resp = new PageResp<>();
        if (CollUtil.isNotEmpty(page.getRecords())) {
            BeanUtil.copyProperties(page, resp);
        }

        resp.setCurrent(Convert.toInt(page.getCurrent()));
        resp.setSize(Convert.toInt(page.getSize()));
        resp.setTotal(Convert.toInt(page.getTotal()));
        resp.setTotalPage(Convert.toInt(page.getPages()));
        if (CollUtil.isNotEmpty(page.getRecords())) {
            resp.setRecords(MyListUtil.convertEntityToDto(page.getRecords(), clazz));
        }

        return resp;
    }

    /**
     * 从分页结果中获取数组，指定返回类型
     */
    public static <T> List<T> getRecords(String jsonResult, Class<T> clazz) {
        if (StrUtil.isEmpty(jsonResult)) {
            return null;
        }
        PageResp resp = JSONUtil.toBean(jsonResult, PageResp.class);
        List<T> records = MyListUtil.convertEntityToDto(resp.getRecords(), clazz);

        return records;
    }

    /**
     * 从分页结果中获取数组json
     */
    public static String getRecords(String jsonResult) {
        if (StrUtil.isEmpty(jsonResult)) {
            return null;
        }
        PageResp resp = JSONUtil.toBean(jsonResult, PageResp.class);

        //判断结果是否为空
        if (JSONUtil.isNull(resp.getRecords())) {
            return null;
        }

        return resp.getRecords().toString();
    }


}
