package com.yiie.common.controller;

import com.yiie.aop.annotation.LogAnnotation;
import com.yiie.common.service.LoginLogService;
import com.yiie.entity.ChartBean;
import com.yiie.entity.VPNUser;
import com.yiie.utils.DataResult;
import com.yiie.vo.request.VPNUserPageReqVO;
import com.yiie.vo.response.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Time： 2020-3-12 18:49
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "首页")
public class MainController {

    @Autowired
    private LoginLogService loginLogService;

    @PostMapping("/main")
    @ApiOperation(value = "首页获取图表信息接口")
    @LogAnnotation(title = "V首页",action = "首页获取图表信息接口")
    public DataResult<ChartBean> getChart(){
        DataResult<ChartBean> result=DataResult.success();
        result.setData(loginLogService.getCharData());
        System.out.println(result.toString());
        return result;
    }
}
