package com.yiie.common.mapper;

import com.yiie.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Time：2020-1-2 10:15
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@Repository
@Mapper
public interface RolePermissionMapper {

    List<String> getPermissionIdsByRoleId(String roleId);

    List<String> getPermissionIdsByRoles(List<String> roleIds);

    int deleteByPrimaryKey(String id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    int removeByRoleId(String roleId);


    int batchRolePermission(List<RolePermission> list);

    int removeByPermissionId(String permissionId);

    List<String> getRoleIds(String permissionId);
}
