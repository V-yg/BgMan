package com.yiie.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Time：2020-1-4 1:19
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@Data
public class DeptPageReqVO {
    @ApiModelProperty(value = "第几页")
    private int pageNum=1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize=10;
}
