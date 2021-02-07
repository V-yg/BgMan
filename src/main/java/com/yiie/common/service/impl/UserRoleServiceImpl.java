package com.yiie.common.service.impl;

import com.yiie.common.mapper.UserRoleMapper;
import com.yiie.common.service.UserRoleService;
import com.yiie.entity.UserRole;
import com.yiie.enums.BaseResponseCode;
import com.yiie.exceptions.BusinessException;
import com.yiie.vo.request.UserRoleOperationReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Time：2020-1-2 9:35
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@Service
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<String> getRoleIdsByUserId(String userId) {
        return userRoleMapper.getRoleIdsByUserId(userId);
    }

    @Override
    public List<String> getUserIdsByRoleIds(List<String> roleIds) {
        return userRoleMapper.getUserIdsByRoleIds(roleIds);
    }

    @Override
    public List<String> getInfoByUserIdByRoleId(String roleId) {
        return userRoleMapper.getInfoByUserIdByRoleId(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUserRoleInfo(UserRoleOperationReqVO vo) {
        if(vo.getRoleIds()==null||vo.getRoleIds().isEmpty()){
            return;
        }
        Date createTime=new Date();
        List<UserRole> list=new ArrayList<>();
        for (String roleId:vo.getRoleIds()){
            UserRole sysUserRole=new UserRole();
            sysUserRole.setId(UUID.randomUUID().toString());
            sysUserRole.setCreateTime(createTime);
            sysUserRole.setUserId(vo.getUserId());
            sysUserRole.setRoleId(roleId);
            list.add(sysUserRole);
        }
        userRoleMapper.removeByUserId(vo.getUserId());
        int count=userRoleMapper.batchUserRole(list);
        if (count==0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
    }

    @Override
    public int removeByUserId(String userId) {
        return userRoleMapper.removeByUserId(userId);
    }
    
    public int removeByUserId1(String userId) {
        return userRoleMapper.removeByUserId(userId);
    }
}
