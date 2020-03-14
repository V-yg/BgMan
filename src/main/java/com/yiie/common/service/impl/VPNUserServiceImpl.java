package com.yiie.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.yiie.common.mapper.VPNUserMapper;
import com.yiie.common.service.VPNUserService;
import com.yiie.constant.Constant;
import com.yiie.entity.Dept;
import com.yiie.entity.User;
import com.yiie.entity.VPNUser;
import com.yiie.enums.BaseResponseCode;
import com.yiie.exceptions.BusinessException;
import com.yiie.utils.PageUtils;
import com.yiie.utils.PasswordUtil;
import com.yiie.vo.request.UserRoleOperationReqVO;
import com.yiie.vo.request.VPNUserAddReqVO;
import com.yiie.vo.request.VPNUserPageReqVO;
import com.yiie.vo.request.VPNUserUpdateReqVO;
import com.yiie.vo.response.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Time： 2020-3-10 11:56
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
@Service
@Slf4j
public class VPNUserServiceImpl implements VPNUserService {

    @Autowired
    private VPNUserMapper vpnUserMapper;

    @Override
    public PageVO<VPNUser> pageInfo(VPNUserPageReqVO vo) {

        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<VPNUser> vpnUsers = vpnUserMapper.selectAll(vo);
        return PageUtils.getPageVO(vpnUsers);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletedUsers(List<String> userIds) {
        int i = vpnUserMapper.deletedUsers(userIds);
        if(i == 0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
    }

    @Override
    public void addUser(VPNUserAddReqVO vo) {
        Calendar calendar =Calendar.getInstance();
        VPNUser vpnUser =new VPNUser();
        BeanUtils.copyProperties(vo,vpnUser);
        vpnUser.setEmail(vo.getEmail());
        vpnUser.setId(UUID.randomUUID().toString());
        vpnUser.setCreateTime(new Date());
        calendar.setTime(new Date());
        calendar.add(calendar.YEAR,1);
        vpnUser.setExpirationTime(calendar.getTime());
        vpnUser.setLastLogin(new Date());
        vpnUser.setLevel(vo.getLevel());
        vpnUser.setStatus(vo.getStatus());
        vpnUser.setDeleted(1);
        int i = vpnUserMapper.insertSelective(vpnUser);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
    }

    @Override
    public void updateUserInfo(VPNUserUpdateReqVO vo) {
        VPNUser vpnUser = vpnUserMapper.selectByPrimaryKey(vo.getId());
        if (null==vpnUser){
            log.error("传入 的 id:{}不合法",vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        BeanUtils.copyProperties(vo,vpnUser);

        int count= vpnUserMapper.updateByPrimaryKeySelective(vpnUser);
        if (count!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }

    }

}
