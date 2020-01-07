package com.yiie.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.yiie.common.mapper.OperLogMapper;
import com.yiie.common.service.OperLogService;
import com.yiie.entity.Log;
import com.yiie.utils.PageUtils;
import com.yiie.vo.request.LogPageReqVO;
import com.yiie.vo.response.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Time：2020-1-4 14:24
 * Email：yiie315@163.com
 * Desc：TODO
 *
 * @Author：yiie
 * @Version：1.0.0
 */
@Service
public class OperLogServiceImpl implements OperLogService {

    @Autowired
    private OperLogMapper sysLogMapper;

    @Override
    public PageVO<Log> pageInfo(LogPageReqVO vo) {

        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<Log> sysLogs = sysLogMapper.selectAll(vo);
        return PageUtils.getPageVO(sysLogs);
    }

    @Override
    public void deleted(List<String> logIds) {
        sysLogMapper.batchDeletedLog(logIds);
    }
}
