package com.yiie.common.controller;

import com.yiie.aop.annotation.LogAnnotation;
import com.yiie.common.service.DeptService;
import com.yiie.entity.Dept;
import com.yiie.entity.User;
import com.yiie.utils.DataResult;
import com.yiie.vo.request.DeptAddReqVO;
import com.yiie.vo.request.DeptPageReqVO;
import com.yiie.vo.request.DeptUpdateReqVO;
import com.yiie.vo.request.UserPageUserByDeptReqVO;
import com.yiie.vo.response.DeptRespNodeVO;
import com.yiie.vo.response.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Time：2020-1-3 23:05
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "组织模块-机构管理")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/tree")
    @ApiOperation(value = "树型组织列表接口")
    @LogAnnotation(title = "机构管理",action = "树型组织列表")
    @RequiresPermissions(value = {"sys:user:update","sys:user:add","sys:dept:add","sys:dept:update"},logical = Logical.OR)
    public DataResult<List<DeptRespNodeVO>> getTree(@RequestParam(required = false) String deptId){
        DataResult<List<DeptRespNodeVO>> result= DataResult.success();
        result.setData(deptService.deptTreeList(deptId));
        return result;
    }
    @PostMapping("/dept")
    @ApiOperation(value = "新增组织接口")
    @LogAnnotation(title = "机构管理",action = "新增组织")
    @RequiresPermissions("sys:dept:add")
    public DataResult<Dept> addDept(@RequestBody @Valid DeptAddReqVO vo){
        DataResult<Dept> result=DataResult.success();
        result.setData(deptService.addDept(vo));
        return result;
    }
    @DeleteMapping("/dept/{id}")
    @ApiOperation(value = "删除组织接口")
    @LogAnnotation(title = "机构管理",action = "删除组织")
    @RequiresPermissions("sys:dept:deleted")
    public DataResult deleted(@PathVariable("id") String id){
        deptService.deleted(id);
        return DataResult.success();
    }
    @PutMapping("/dept")
    @ApiOperation(value = "更新组织信息接口")
    @LogAnnotation(title = "机构管理",action = "更新组织信息")
    @RequiresPermissions("sys:dept:update")
    public DataResult updateDept(@RequestBody @Valid DeptUpdateReqVO vo){
        deptService.updateDept(vo);
        return DataResult.success();
    }
    @GetMapping("/dept/{id}")
    @ApiOperation(value = "查询组织详情接口")
    @LogAnnotation(title = "机构管理",action = "查询组织详情")
    @RequiresPermissions("sys:dept:detail")
    public DataResult<Dept> detailInfo(@PathVariable("id") String id){
        DataResult<Dept> result=DataResult.success();
        result.setData(deptService.detailInfo(id));
        return result;
    }
    @PostMapping("/depts")
    @ApiOperation(value = "分页获取组织信息接口")
    @LogAnnotation(title = "机构管理",action = "分页获取组织信息")
    @RequiresPermissions("sys:dept:list")
    public DataResult<PageVO<Dept>> pageInfo(@RequestBody DeptPageReqVO vo){
        DataResult<PageVO<Dept>> result=DataResult.success();
        result.setData(deptService.pageInfo(vo));
        return result;
    }

    @PostMapping("/dept/users")
    @ApiOperation(value = "分页获取组织下所有用户接口")
    @LogAnnotation(title = "机构管理",action = "分页获取组织下所有用户")
    @RequiresPermissions("sys:dept:user:list")
    public DataResult<PageVO<User>> pageDeptUserInfos(@RequestBody @Valid UserPageUserByDeptReqVO vo){
        DataResult<PageVO<User>> result=DataResult.success();
        result.setData(deptService.pageDeptUserInfo(vo));
        System.out.println("fsdfds");
        return result;
    }

    @GetMapping("/depts")
    @ApiOperation(value = "获取机构列表接口")
    @LogAnnotation(title = "机构管理",action = "获取所有组织机构")
    @RequiresPermissions("sys:dept:list")
    public DataResult<List<Dept>> getDeptAll(){
        DataResult<List<Dept>> result=DataResult.success();
        result.setData(deptService.selectAll());
        return result;
    }
}
