package com.yiie.vo.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Time：2020-1-3 19:25
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@Data
public class UserAddReqVO {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "账号不能为空")
    private String username;
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
    @ApiModelProperty(value = "手机号码")
    private String phone;
    @ApiModelProperty(value = "创建来源(1.web 2.android 3.ios )")
    private Integer createWhere;
    @ApiModelProperty(value = "所属机构")
    @NotBlank(message = "所属机构不能为空")
    private String deptId;
    @ApiModelProperty(value = "性别(1.男 2.女)")
    private Integer sex;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "真实名称")
    private String realName;
    @ApiModelProperty(value = "所拥有的角色")
    private List<String> roleIds;
}
