package com.yiie.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.yiie.aop.annotation.LogAnnotation;
import com.yiie.common.mapper.LoginLogMapper;
import com.yiie.common.service.LoginLogService;
import com.yiie.entity.LoginLog;
import com.yiie.utils.DataResult;
import com.yiie.utils.PageUtils;
import com.yiie.vo.request.LogPageReqVO;
import com.yiie.vo.request.LoginLogPageReqVO;
import com.yiie.vo.response.PageVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Time：2020-1-4 21:33
 * Email：yiie315@163.com
 * Desc：TODO
 *
 * @Author：yiie
 * @Version：1.0.0
 */
@Service
@Slf4j
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;


    @Override
    public PageVO<LoginLog> pageInfo(LoginLogPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<LoginLog> loginLogs = loginLogMapper.selectAll(vo);
        return PageUtils.getPageVO(loginLogs);
    }

    @Override
    public void deleted(List<String> logIds) {
        loginLogMapper.batchDeletedLog(logIds);
    }
}
