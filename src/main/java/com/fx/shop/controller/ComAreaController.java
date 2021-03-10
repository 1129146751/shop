package com.fx.shop.controller;


import com.fx.shop.dto.area.req.AreaQueryReq;
import com.fx.shop.dto.area.resp.AreaResp;
import com.fx.shop.service.ComAreaService;
import com.sineyun.commons.base.dto.response.PageResp;
import com.sineyun.commons.core.result.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 区域信息表 前端控制器
 * </p>
 *
 * @author fx
 * @since 2021-01-20
 */
@Api(tags = "区域信息")
@RestController
@RequestMapping("/area")
public class ComAreaController {
    @Autowired
    private ComAreaService areaService;
    /**
     *查询区域信息
     * @param req
     * @return
     */
    @PostMapping("/query")
    @ApiOperation(value = "查询区域信息")
    public ApiResult<PageResp<AreaResp>> upload(@RequestBody  AreaQueryReq req) {
        PageResp<AreaResp> pageResp=areaService.query(req);
        return ApiResult.success(pageResp);
    }
}
