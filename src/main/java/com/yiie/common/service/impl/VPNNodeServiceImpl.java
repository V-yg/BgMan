package com.yiie.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.yiie.common.mapper.VPNNodeMapper;
import com.yiie.common.service.VPNNodeService;
import com.yiie.entity.VPNNode;
import com.yiie.entity.VPNUser;
import com.yiie.enums.BaseResponseCode;
import com.yiie.exceptions.BusinessException;
import com.yiie.utils.DataResult;
import com.yiie.utils.PageUtils;
import com.yiie.vo.request.VPNNodeAddReqVO;
import com.yiie.vo.request.VPNNodePageReqVO;
import com.yiie.vo.request.VPNNodeUpdateReqVO;
import com.yiie.vo.response.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Time： 2020-3-10 17:21
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */

@Service
@Slf4j
public class VPNNodeServiceImpl implements VPNNodeService {

    @Autowired
    private VPNNodeMapper vpnNodeMapper;

    @Override
    public PageVO<VPNNode> pageInfo(VPNNodePageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<VPNNode> vpnNodes = vpnNodeMapper.selectAll(vo);
        return PageUtils.getPageVO(vpnNodes);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletedNodes(List<String> nodeIds) {
        int i = vpnNodeMapper.deletedNodes(nodeIds);
        if(i == 0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
    }

    @Override
    public void updateUserInfo(VPNNodeUpdateReqVO vo) {
        VPNNode vpnNode = vpnNodeMapper.selectByPrimaryKey(vo.getId());
        if (null==vpnNode){
            log.error("传入 的 id:{}不合法",vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        BeanUtils.copyProperties(vo,vpnNode);

        int count= vpnNodeMapper.updateByPrimaryKeySelective(vpnNode);
        if (count!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
    }

    @Override
    public void addUser(VPNNodeAddReqVO vo) {
        VPNNode vpnNode = new VPNNode();
        BeanUtils.copyProperties(vo,vpnNode);
        vpnNode.setDelay(new Random(System.currentTimeMillis()).nextInt(100)+1);
        vpnNode.setDeleted(1);
        int i = vpnNodeMapper.insertSelective(vpnNode);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
    }
}
