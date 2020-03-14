package com.yiie.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Time： 2020-3-11 12:21
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
@Data
public class VPNUserFlowUpdateReqVO {

    @ApiModelProperty(value = "用户id")
    @NotBlank(message = "用户id不能为空")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "最大上传速度")
    private Integer maxUp;

    @ApiModelProperty(value = "最大下载速度")
    private Integer maxDown;

    @ApiModelProperty(value = "信用")
    private Integer credit;

    @ApiModelProperty(value = "流量")
    private Integer flow;

    @ApiModelProperty(value = "余额")
    private Integer money;

}
