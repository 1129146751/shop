package com.fx.shop.controller;


import com.fx.shop.dto.PageResp;
import com.fx.shop.dto.exFee.req.AddReq;
import com.fx.shop.dto.exFee.req.EditReq;
import com.fx.shop.dto.exFee.req.QueryReq;
import com.fx.shop.dto.exFee.resp.Resp;
import com.fx.shop.service.ExpressFeeService;


import com.fx.shop.util.result.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fx
 * @since 2021-01-22
 */
@Api(tags = "快递费")
@RestController
@RequestMapping("/exFee")
public class ExpressFeeController {
    @Autowired
    private ExpressFeeService feeService;

    /**
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "增加",notes = "增加")
    @PostMapping("/add")
    public ApiResult<String> add(@RequestBody @Valid AddReq req){
        feeService.add(req);
        return ApiResult.success("增加成功!");
    }
    /**
     *修改
     * @param req
     * @return
     */
    @ApiOperation(value = "修改",notes = "修改")
    @PostMapping("/edit")
    public ApiResult<String> edit(@RequestBody EditReq req){
        feeService.edit(req);
        return ApiResult.success("修改成功!");
    }
    /**
     *查询
     * @param req
     * @return
     */
    @ApiOperation(value = "查询",notes = "查询")
    @PostMapping("/query")
    public ApiResult<PageResp<Resp>> query(@RequestBody QueryReq req){
        PageResp<Resp>  pageResp=feeService.query(req);
        return ApiResult.success(pageResp);
    }
}
