package com.yiie.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Time：2020-1-5 14:21
 * Email：yiie315@163.com
 * Desc：TODO
 *
 * @Author：yiie
 * @Version：1.0.0
 */
@Data
public class LoginLogPageReqVO {
    @ApiModelProperty(value = "第几页")
    private int pageNum=1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize=10;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "登录状态 1：成功 0：失败")
    private String status;


}
