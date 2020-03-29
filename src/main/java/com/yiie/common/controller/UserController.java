package com.yiie.common.controller;

import com.yiie.aop.annotation.LogAnnotation;
import com.yiie.common.service.UserService;
import com.yiie.constant.Constant;
import com.yiie.entity.LoginLog;
import com.yiie.entity.User;
import com.yiie.enums.BaseResponseCode;
import com.yiie.utils.DataResult;
import com.yiie.utils.IPUtil;
import com.yiie.utils.JwtTokenUtil;
import com.yiie.vo.request.*;
import com.yiie.vo.response.HomeRespVO;
import com.yiie.vo.response.LoginRespVO;
import com.yiie.vo.response.PageVO;
import com.yiie.vo.response.UserOwnRoleRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Time：2020-1-1 11:22
 * Email： yiie315@163.com
 * Desc：用户模块控制器
 *
 * @author： yiie
 * @version：1.0.0
 */
@Api(tags = "组织模块-用户管理")
@RequestMapping("/sys")
@RestController
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/user/login")
    @ApiOperation(value = "用户登录接口")
    public DataResult<LoginRespVO> login(@RequestBody(required=false) String data){

        String[] splited = data.split("&");
        String type = splited[0].split("=")[1];
        String username = splited[1].split("=")[1];
        String password = splited[2].split("=")[1];
        LoginReqVO vo = new LoginReqVO();
        vo.setType(type);
        vo.setUsername(username);
        vo.setPassword(password);
        DataResult<LoginRespVO> result=DataResult.success();
        result.setData(userService.login(vo));
        return result;
    }

    @GetMapping("/user/unLogin")
    @ApiOperation(value = "引导客户端去登录")
    public DataResult unLogin(){
        DataResult result= DataResult.getResult(BaseResponseCode.TOKEN_ERROR);
        return result;
    }

    @GetMapping("/user/logout")
    @ApiOperation(value = "退出接口")
    @LogAnnotation(title = "用户管理",action = "退出")
    public DataResult logout(HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String refreshToken=request.getHeader(Constant.REFRESH_TOKEN);
        userService.logout(accessToken,refreshToken);
        return DataResult.success();
    }

    @PutMapping("/user/pwd")
    @ApiOperation(value = "修改密码接口")
    @LogAnnotation(title = "用户管理",action = "更新密码")
    public DataResult updatePwd(@RequestBody UpdatePasswordReqVO vo, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String refreshToken=request.getHeader(Constant.REFRESH_TOKEN);
        String userId=JwtTokenUtil.getUserId(accessToken);
        userService.updatePwd(vo,userId,accessToken,refreshToken);
        return DataResult.success();
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询用户详情接口")
    @LogAnnotation(title = "用户管理",action = "查询用户详情")
    @RequiresPermissions("sys:user:detail")
    public DataResult<User> detailInfo(@PathVariable("id") String id){
        DataResult<User> result=DataResult.success();
        result.setData(userService.detailInfo(id));
        return result;
    }

    @GetMapping("/username")
    @ApiOperation(value = "查询用户名")
    @LogAnnotation(title = "用户管理",action = "查询用户名")
    public String getUserName(HttpServletRequest request){
        String username=JwtTokenUtil.getUserName(request.getHeader(Constant.ACCESS_TOKEN));
        return username;
    }

    @GetMapping("/home")
    @ApiOperation(value ="获取首页数据接口")
    public DataResult<HomeRespVO> getHomeInfo(HttpServletRequest request){
        String accessToken=request.getHeader("authorization");
        String userId= JwtTokenUtil.getUserId(accessToken);
        DataResult<HomeRespVO> result=DataResult.success();
        result.setData(userService.getHomeInfo(userId));
        return result;
    }

    @GetMapping("/user/token")
    @ApiOperation(value = "用户刷新token接口")
    @LogAnnotation(title = "用户管理",action = "用户刷新token")
    public DataResult<String> refreshToken(HttpServletRequest request){
        String refreshToken=request.getHeader(Constant.REFRESH_TOKEN);
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        DataResult<String> result=DataResult.success();
        result.setData(userService.refreshToken(refreshToken,accessToken));
        return result;
    }

    @PostMapping("/users")
    @ApiOperation(value = "分页获取用户列表接口")
    @RequiresPermissions("sys:user:list")
    @LogAnnotation(title = "用户管理",action = "分页获取用户列表")
    public DataResult<PageVO<User>> pageInfo(@RequestBody UserPageReqVO vo){
        DataResult<PageVO<User>> result=DataResult.success();
        result.setData(userService.pageInfo(vo));
        return result;
    }

    @PostMapping("/user")
    @ApiOperation(value = "新增用户接口")
    @RequiresPermissions("sys:user:add")
    @LogAnnotation(title = "用户管理",action = "新增用户")
    public DataResult addUser(@RequestBody @Valid UserAddReqVO vo){
        userService.addUser(vo);
        return DataResult.success();
    }

    @GetMapping("/user")
    @ApiOperation(value = "查询用户详情接口")
    @LogAnnotation(title = "用户管理",action = "查询用户详情")
    public DataResult<User> youSelfInfo(HttpServletRequest request){
        String id=JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        DataResult<User> result=DataResult.success();
        result.setData(userService.detailInfo(id));
        return result;
    }

    @PutMapping("/user")
    @ApiOperation(value = "更新用户信息接口")
    @LogAnnotation(title = "用户管理",action = "更新用户信息")
    @RequiresPermissions("sys:user:update")
    public DataResult updateUserInfo(@RequestBody @Valid UserUpdateReqVO vo,HttpServletRequest request){
        String operationId= JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        userService.updateUserInfo(vo,operationId);
        return DataResult.success();
    }

    @PutMapping("/user/info")
    @ApiOperation(value = "更新用户信息接口")
    @LogAnnotation(title = "用户管理",action = "更新用户信息")
    public DataResult updateUserInfoById(@RequestBody @Valid UserUpdateReqVO vo, HttpServletRequest request){
        String operationId= JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        vo.setId(operationId);
        userService.updateUserInfo(vo,operationId);
        return DataResult.success();
    }

    @DeleteMapping("/user")
    @ApiOperation(value = "删除用户接口")
    @LogAnnotation(title = "用户管理",action = "删除用户")
    @RequiresPermissions("sys:user:deleted")
    public DataResult deletedUser(@RequestBody @ApiParam(value = "用户id集合") List<String> userIds, HttpServletRequest request){
        String operationId=JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        userService.deletedUsers(userIds,operationId);
        return DataResult.success();
    }

    @GetMapping("/user/roles/{userId}")
    @ApiOperation(value = "赋予角色-获取所有角色接口")
    @LogAnnotation(title = "用户管理",action = "赋予角色-获取所有角色接口")
    @RequiresPermissions("sys:user:role:detail")
    public DataResult<UserOwnRoleRespVO> getUserOwnRole(@PathVariable("userId")String userId,HttpServletRequest request){
        DataResult<UserOwnRoleRespVO> result=DataResult.success();
        result.setData(userService.getUserOwnRole(userId,request));
        return result;
    }
    @PutMapping("/user/roles/{userId}")
    @ApiOperation(value = "赋予角色-用户赋予角色接口")
    @LogAnnotation(title = "用户管理",action = "赋予角色-用户赋予角色接口")
    @RequiresPermissions("sys:user:update:role")
    public DataResult<UserOwnRoleRespVO> setUserOwnRole(@PathVariable("userId")String userId, @RequestBody List<String> roleIds,HttpServletRequest request){
        DataResult result=DataResult.success();
        userService.setUserOwnRole(userId,roleIds,request);
        return result;
    }
}
