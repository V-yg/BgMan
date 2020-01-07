package com.yiie.common.service;

import com.yiie.entity.LoginLog;
import com.yiie.vo.request.LogPageReqVO;
import com.yiie.vo.request.LoginLogPageReqVO;
import com.yiie.vo.response.PageVO;

import java.util.List;

/**
 * Time：2020-1-4 21:33
 * Email：yiie315@163.com
 * Desc：TODO
 *
 * @Author：yiie
 * @Version：1.0.0
 */
public interface LoginLogService {
    PageVO<LoginLog> pageInfo(LoginLogPageReqVO vo);

    void deleted(List<String> logIds);
}
