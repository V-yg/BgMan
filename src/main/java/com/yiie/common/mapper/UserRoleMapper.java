package com.yiie.common.mapper;

import com.yiie.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Time：2020-1-2 9:59
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@Repository
@Mapper
public interface UserRoleMapper {

    List<String> getRoleIdsByUserId(String userId);

    List<String> getInfoByUserIdByRoleId(String roleId);

    List<String> getUserIdsByRoleIds(List<String> roleIds);

    int batchUserRole(List<UserRole> list);

    int removeByUserId(String userId);
}
