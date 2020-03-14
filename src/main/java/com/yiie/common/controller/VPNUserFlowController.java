package com.yiie.common.controller;

import com.yiie.aop.annotation.LogAnnotation;
import com.yiie.common.service.VPNUserFlowService;
import com.yiie.entity.VPNUser;
import com.yiie.entity.VPNUserFlow;
import com.yiie.utils.DataResult;
import com.yiie.vo.request.VPNUserFlowPageReqVO;
import com.yiie.vo.request.VPNUserFlowUpdateReqVO;
import com.yiie.vo.request.VPNUserPageReqVO;
import com.yiie.vo.request.VPNUserUpdateReqVO;
import com.yiie.vo.response.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Time： 2020-3-11 11:10
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
@Api(tags = "VPN管理模块-流量管理")
@RequestMapping("/sys")
@RestController
public class VPNUserFlowController {

    @Autowired
    private VPNUserFlowService vpnUserFlowService;

    @PostMapping("/vpnuserflows")
    @ApiOperation(value = "分页获取VPN用户流量信息接口")
    @LogAnnotation(title = "VPN用户流量管理",action = "分页获取VPN用户流量信息")
    @RequiresPermissions("sys:vpnuserflow:list")
    public DataResult<PageVO<VPNUserFlow>> pageInfo(@RequestBody VPNUserFlowPageReqVO vo){
        DataResult<PageVO<VPNUserFlow>> result=DataResult.success();
        result.setData(vpnUserFlowService.pageInfo(vo));
        return result;
    }

    @PutMapping("/vpnuserflow")
    @ApiOperation(value = "更新VPN用户流量信息接口")
    @LogAnnotation(title = "VPN管理",action = "更新VPN用户流量信息")
    @RequiresPermissions("sys:vpnuserflow:update")
    public DataResult updateUserInfo(@RequestBody @Valid VPNUserFlowUpdateReqVO vo){
        System.out.println(vo.toString());
        vpnUserFlowService.updateUserInfo(vo);
        return DataResult.success();
    }
}
