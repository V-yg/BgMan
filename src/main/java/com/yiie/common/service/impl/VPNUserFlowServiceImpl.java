package com.yiie.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.yiie.common.mapper.VPNUserFlowMapper;
import com.yiie.common.service.VPNUserFlowService;
import com.yiie.entity.VPNUser;
import com.yiie.entity.VPNUserFlow;
import com.yiie.enums.BaseResponseCode;
import com.yiie.exceptions.BusinessException;
import com.yiie.utils.PageUtils;
import com.yiie.vo.request.VPNUserFlowPageReqVO;
import com.yiie.vo.request.VPNUserFlowUpdateReqVO;
import com.yiie.vo.response.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Time： 2020-3-11 11:08
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
@Service
@Slf4j
public class VPNUserFlowServiceImpl implements VPNUserFlowService {

    @Autowired
    private VPNUserFlowMapper vpnUserFlowMapper;

    @Override
    public PageVO<VPNUserFlow> pageInfo(VPNUserFlowPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<VPNUserFlow> vpnUserFlows = vpnUserFlowMapper.selectAll(vo);
        return PageUtils.getPageVO(vpnUserFlows);
    }

    @Override
    public void updateUserInfo(VPNUserFlowUpdateReqVO vo) {
        VPNUserFlow vpnUserFlow = new VPNUserFlow();
        BeanUtils.copyProperties(vo,vpnUserFlow);
        int count = vpnUserFlowMapper.updateByPrimaryKeySelective(vpnUserFlow);
        if (count!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
    }
}
