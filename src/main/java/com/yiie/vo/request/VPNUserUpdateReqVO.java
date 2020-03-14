package com.yiie.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Time： 2020-3-10 16:35
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
@Data
public class VPNUserUpdateReqVO {

    @ApiModelProperty(value = "用户id")
    @NotBlank(message = "用户id不能为空")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "等级")
    private String level;

    @ApiModelProperty(value = "账户状态(1.正常 2.锁定 )")
    private Integer status;
}
