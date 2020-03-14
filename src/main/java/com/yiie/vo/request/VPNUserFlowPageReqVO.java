package com.yiie.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Time： 2020-3-11 10:55
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
@Data
public class VPNUserFlowPageReqVO {

    @ApiModelProperty(value = "第几页")
    private int pageNum=1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize=10;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "信用等级")
    private String credit;
}
