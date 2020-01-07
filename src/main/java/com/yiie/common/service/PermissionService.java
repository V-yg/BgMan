package com.yiie.common.service;

import com.yiie.entity.Permission;
import com.yiie.vo.request.PermissionAddReqVO;
import com.yiie.vo.request.PermissionPageReqVO;
import com.yiie.vo.request.PermissionUpdateReqVO;
import com.yiie.vo.response.PageVO;
import com.yiie.vo.response.PermissionRespNode;

import java.util.List;
import java.util.Set;

/**
 * Time：2020-1-2 9:31
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
public interface PermissionService {

    List<Permission> getPermission(String userId);

    Set<String> getPermissionsByUserId(String userId);

    List<PermissionRespNode> permissionTreeList(String userId);

    Permission addPermission(PermissionAddReqVO vo);

    void deleted(String permissionId);

    void updatePermission(PermissionUpdateReqVO vo);

    PageVO<Permission> pageInfo(PermissionPageReqVO vo);

    Permission detailInfo(String permissionId);

    List<Permission> selectAll();

    List<PermissionRespNode> selectAllByTree();

    List<PermissionRespNode> selectAllMenuByTree(String permissionId);
}
