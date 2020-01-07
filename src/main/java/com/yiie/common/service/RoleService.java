package com.yiie.common.service;

import com.yiie.entity.Role;
import com.yiie.vo.request.RoleAddReqVO;
import com.yiie.vo.request.RolePageReqVO;
import com.yiie.vo.request.RoleUpdateReqVO;
import com.yiie.vo.response.PageVO;

import java.util.List;

/**
 * Time：2020-1-2 9:30
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
public interface RoleService {

    Role addRole(RoleAddReqVO vo);

    void updateRole(RoleUpdateReqVO vo, String accessToken);

    Role detailInfo(String id);

    void deletedRole(String id);

    PageVO<Role> pageInfo(RolePageReqVO vo);

    List<String> getRoleNames(String userId);

    List<Role> getRoleInfoByUserId(String userId);

    List<Role> selectAllRoles();
}
