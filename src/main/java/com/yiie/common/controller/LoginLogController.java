package com.yiie.common.controller;

import com.yiie.aop.annotation.LogAnnotation;
import com.yiie.common.service.LoginLogService;
import com.yiie.entity.LoginLog;
import com.yiie.utils.DataResult;
import com.yiie.vo.request.LogPageReqVO;
import com.yiie.vo.request.LoginLogPageReqVO;
import com.yiie.vo.response.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Time：2020-1-4 21:34
 * Email：yiie315@163.com
 * Desc：TODO
 *
 * @Author：yiie
 * @Version：1.0.0
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "系统模块-登录日志")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @PostMapping("/loginlogs")
    @ApiOperation(value = "分页查询系统登录日志接口")
    @LogAnnotation(title = "系统操作登录日志管理",action = "分页查询系统登录日志")
    @RequiresPermissions("sys:loginlog:list")
    public DataResult<PageVO<LoginLog>> pageInfo(@RequestBody LoginLogPageReqVO vo) {
        PageVO<LoginLog> sysLogPageVO = loginLogService.pageInfo(vo);
        DataResult<PageVO<LoginLog>> result = DataResult.success();
        result.setData(sysLogPageVO);
        return result;
    }

    @DeleteMapping("/loginlogs")
    @ApiOperation(value = "删除登录日志接口")
    @LogAnnotation(title = "系统登录日志管理",action = "删除系统登录日志")
    @RequiresPermissions("sys:loginlog:deleted")
    public DataResult deleted(@RequestBody List<String> logIds){
        loginLogService.deleted(logIds);
        return DataResult.success();
    }
}
