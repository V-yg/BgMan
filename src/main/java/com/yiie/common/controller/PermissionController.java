package com.yiie.common.controller;

import com.yiie.aop.annotation.LogAnnotation;
import com.yiie.common.service.PermissionService;
import com.yiie.entity.Permission;
import com.yiie.utils.DataResult;
import com.yiie.vo.request.PermissionAddReqVO;
import com.yiie.vo.request.PermissionPageReqVO;
import com.yiie.vo.request.PermissionUpdateReqVO;
import com.yiie.vo.response.PageVO;
import com.yiie.vo.response.PermissionRespNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Time：2020-1-4 3:00
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "组织模块-菜单权限管理")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/permission")
    @ApiOperation(value = "新增菜单权限接口")
    @LogAnnotation(title = "菜单权限管理",action = "新增菜单权限")
    @RequiresPermissions("sys:permission:add")
    public DataResult<Permission> addPermission(@RequestBody @Valid PermissionAddReqVO vo){
        System.out.println(vo.toString());
        DataResult<Permission> result=DataResult.success();
        result.setData(permissionService.addPermission(vo));
        return result;
    }
    @DeleteMapping("/permission/{id}")
    @ApiOperation(value = "删除菜单权限接口")
    @LogAnnotation(title = "菜单权限管理",action = "删除菜单权限")
    @RequiresPermissions("sys:permission:deleted")
    public DataResult deleted(@PathVariable("id") String id){
        DataResult result=DataResult.success();
        permissionService.deleted(id);
        return result;
    }
    @PutMapping("/permission")
    @ApiOperation(value = "更新菜单权限接口")
    @LogAnnotation(title = "菜单权限管理",action = "更新菜单权限")
    @RequiresPermissions("sys:permission:update")

    public DataResult updatePermission(@RequestBody @Valid PermissionUpdateReqVO vo){
        System.out.println(vo.toString());
        DataResult result=DataResult.success();
        permissionService.updatePermission(vo);
        return result;
    }
    @GetMapping("/permission/{id}")
    @ApiOperation(value = "查询菜单权限接口")
    @LogAnnotation(title = "菜单权限管理",action = "查询菜单权限")
    @RequiresPermissions("sys:permission:detail")
    public DataResult<Permission> detailInfo(@PathVariable("id") String id){
        DataResult<Permission> result=DataResult.success();
        result.setData(permissionService.detailInfo(id));
        return result;
    }

    @PostMapping("/permissions")
    @ApiOperation(value = "分页查询菜单权限接口")
    @LogAnnotation(title = "菜单权限管理",action = "分页查询菜单权限")
    @RequiresPermissions("sys:permission:list")
    public DataResult<PageVO<Permission>> pageInfo(@RequestBody PermissionPageReqVO vo){
        DataResult<PageVO<Permission>> result=DataResult.success();
        result.setData(permissionService.pageInfo(vo));
        return result;
    }
    @GetMapping("/permissions")
    @ApiOperation(value = "获取所有菜单权限接口")
    @LogAnnotation(title = "菜单权限管理",action = "获取所有菜单权限")
    @RequiresPermissions("sys:permission:list")
    public DataResult<List<Permission>> getAllMenusPermission(){
        DataResult<List<Permission>> result=DataResult.success();
        result.setData(permissionService.selectAll());
        return result;
    }
    @GetMapping("/permission/tree")
    @ApiOperation(value = "获取所有目录菜单树接口")
    @LogAnnotation(title = "菜单权限管理",action = "获取所有目录菜单树")
    @RequiresPermissions(value = {"sys:permission:update","sys:permission:add"},logical = Logical.OR)
    public DataResult<List<PermissionRespNode>> getAllMenusPermissionTree(@RequestParam(required = false) String permissionId){
        DataResult<List<PermissionRespNode>> result= DataResult.success();
        result.setData(permissionService.selectAllMenuByTree(permissionId));
        return result;
    }

    @GetMapping("/permission/tree/all")
    @ApiOperation(value = "获取所有目录菜单树接口")
    @LogAnnotation(title = "菜单权限管理",action = "获取所有目录菜单树")
    @RequiresPermissions(value = {"sys:role:update","sys:role:add"},logical = Logical.OR)
    public DataResult<List<PermissionRespNode>> getAllPermissionTree(){
        DataResult<List<PermissionRespNode>> result=DataResult.success();
        result.setData(permissionService.selectAllByTree());
        return result;
    }
}
