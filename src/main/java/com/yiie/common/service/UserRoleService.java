package com.yiie.common.service;

import com.yiie.vo.request.UserRoleOperationReqVO;

import java.util.List;

/**
 * Time：2020-1-2 9:31
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
public interface UserRoleService {

    List<String> getRoleIdsByUserId(String userId);

    List<String> getUserIdsByRoleIds(List<String> roleIds);

    List<String> getInfoByUserIdByRoleId( String roleId);

    void addUserRoleInfo(UserRoleOperationReqVO vo);

    int removeByUserId(String userId);
}
