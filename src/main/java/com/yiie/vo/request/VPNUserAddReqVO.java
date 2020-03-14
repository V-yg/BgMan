package com.yiie.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Time： 2020-3-10 15:50
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
@Data
public class VPNUserAddReqVO {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "账号不能为空")
    private String username;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "会员等级")
    private String level;

    @ApiModelProperty(value = "用户状态")
    private Integer status;
}
