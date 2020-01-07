package com.yiie.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Time：2020-1-1 17:39
 * Email： yiie315@163.com
 * Desc：用户登录响应VO
 *
 * @author： yiie
 * @version：1.0.0
 */
@Data
public class LoginRespVO {

    @ApiModelProperty(value = "token")
    private String accessToken;
    @ApiModelProperty(value = "刷新token")
    private String refreshToken;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户id")
    private String id;
    @ApiModelProperty(value = "电话")
    private String phone;
    @ApiModelProperty(value = "用户所拥有的菜单权限(前后端分离返回给前端控制菜单和按钮的显示和隐藏)")
    private List<PermissionRespNode> list;

}
