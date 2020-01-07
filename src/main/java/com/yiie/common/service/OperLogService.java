package com.yiie.common.service;

import com.yiie.entity.Log;
import com.yiie.vo.request.LogPageReqVO;
import com.yiie.vo.response.PageVO;

import java.util.List;

/**
 * Time：2020-1-4 14:21
 * Email：yiie315@163.com
 * Desc：TODO
 *
 * @Author：yiie
 * @Version：1.0.0
 */
public interface OperLogService {

    PageVO<Log> pageInfo(LogPageReqVO vo);

    void deleted(List<String> logIds);
}
