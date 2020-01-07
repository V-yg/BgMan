package com.yiie.common.service;

import com.yiie.vo.request.PermissionAddReqVO;
import com.yiie.vo.request.PermissionPageReqVO;
import com.yiie.vo.request.PermissionUpdateReqVO;
import com.yiie.vo.request.RolePermissionOperationReqVO;
import com.yiie.vo.response.PermissionRespNode;

import java.util.List;

/**
 * Time：2020-1-2 10:12
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
public interface RolePermissionService {

    List<String> getPermissionIdsByRoles(List<String> roleIds);

    List<String> getPermissionIdsByRoleId(String roleId);

    int removeByRoleId(String roleId);

    void addRolePermission(RolePermissionOperationReqVO vo);

    int removeByPermissionId(String permissionId);

    List<String> getRoleIds(String permissionId);
}
