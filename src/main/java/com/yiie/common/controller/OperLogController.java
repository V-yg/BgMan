package com.yiie.common.controller;

import com.yiie.aop.annotation.LogAnnotation;
import com.yiie.common.service.OperLogService;
import com.yiie.entity.Log;
import com.yiie.utils.DataResult;
import com.yiie.vo.request.LogPageReqVO;
import com.yiie.vo.response.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Time：2020-1-4 14:19
 * Email：yiie315@163.com
 * Desc：TODO
 *
 * @Author：yiie
 * @Version：1.0.0
 */
@RequestMapping("/sys")
@Api(tags = "系统模块-系统操作日志管理")
@RestController
public class OperLogController {

    @Autowired
    private OperLogService logService;

    @PostMapping("/logs")
    @ApiOperation(value = "分页查询系统操作日志接口")
    @LogAnnotation(title = "系统操作日志管理",action = "分页查询系统操作日志")
    @RequiresPermissions("sys:log:list")
    public DataResult<PageVO<Log>> pageInfo(@RequestBody LogPageReqVO vo){
        PageVO<Log> sysLogPageVO = logService.pageInfo(vo);
        DataResult<PageVO<Log>> result= DataResult.success();
        result.setData(sysLogPageVO);
        return result;
    }

    @DeleteMapping("/logs")
    @ApiOperation(value = "删除日志接口")
    @LogAnnotation(title = "系统操作日志管理",action = "删除系统操作日志")
    @RequiresPermissions("sys:log:deleted")
    public DataResult deleted(@RequestBody List<String> logIds){
        logService.deleted(logIds);
        return DataResult.success();
    }

}
