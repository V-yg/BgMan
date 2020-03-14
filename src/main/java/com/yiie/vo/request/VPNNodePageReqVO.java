package com.yiie.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Time： 2020-3-10 17:27
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
@Data
public class VPNNodePageReqVO {

    @ApiModelProperty(value = "第几页")
    private int pageNum=1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize=10;

    @ApiModelProperty(value = "节点id")
    private Integer nodeId;

    @ApiModelProperty(value = "节点状态(1.正常 2.失效 ")
    private Integer status;

    @ApiModelProperty(value = "节点名称 ")
    private String node;

    @ApiModelProperty(value = "创建开始时间")
    private String startTime;

    @ApiModelProperty(value = "创建结束时间")
    private String endTime;

}
