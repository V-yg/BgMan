package com.yiie.common.controller;

import com.yiie.aop.annotation.LogAnnotation;
import com.yiie.common.service.VPNUserService;
import com.yiie.constant.Constant;
import com.yiie.entity.Role;
import com.yiie.entity.VPNUser;
import com.yiie.utils.DataResult;
import com.yiie.utils.JwtTokenUtil;
import com.yiie.vo.request.*;
import com.yiie.vo.response.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Time： 2020-3-10 10:22
 * Email： yiie315@163.com
 * Desc：VPN服务控制器
 *
 * @Author： yiie
 * @Version： 1.0
 */
@Api(tags = "VPN管理模块-用户管理")
@RequestMapping("/sys")
@RestController
public class VPNUserConatroller {

    @Autowired
    private VPNUserService vpnUserService;

    @PostMapping("/vpnusers")
    @ApiOperation(value = "分页获取VPN角色信息接口")
    @LogAnnotation(title = "VPN角色管理",action = "分页获取VPN角色信息")
    @RequiresPermissions("sys:vpnuser:list")
    public DataResult<PageVO<VPNUser>> pageInfo(@RequestBody VPNUserPageReqVO vo){
        DataResult<PageVO<VPNUser>> result=DataResult.success();
        result.setData(vpnUserService.pageInfo(vo));
        return result;
    }

    @DeleteMapping("/vpnuser")
    @ApiOperation(value = "删除VPN用户接口")
    @LogAnnotation(title = "VPN管理",action = "删除VPN用户")
    @RequiresPermissions("sys:vpnuser:deleted")
    public DataResult deletedVPNUser(@RequestBody @ApiParam(value = "用户id集合") List<String> userIds){
        vpnUserService.deletedUsers(userIds);
        return DataResult.success();
    }

    @PostMapping("/vpnuser")
    @ApiOperation(value = "新增VPN用户接口")
    @RequiresPermissions("sys:vpnuser:add")
    @LogAnnotation(title = "VPN管理",action = "新增VPN用户")
    public DataResult addUser(@RequestBody @Valid VPNUserAddReqVO vo){
        vpnUserService.addUser(vo);
        return DataResult.success();
    }

    @PutMapping("/vpnuser")
    @ApiOperation(value = "更新VPN用户信息接口")
    @LogAnnotation(title = "VPN管理",action = "更新VPN用户信息")
    @RequiresPermissions("sys:vpnuser:update")
    public DataResult updateUserInfo(@RequestBody @Valid VPNUserUpdateReqVO vo){
        System.out.println(vo.toString());
        vpnUserService.updateUserInfo(vo);
        return DataResult.success();
    }
}
