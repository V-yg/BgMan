package com.yiie.common.mapper;

import com.yiie.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Time：2020-1-2 10:12
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@Repository
@Mapper
public interface PermissionMapper {

    List<Permission> selectInfoByIds (List<String> ids);

    int deleteByPrimaryKey(String id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    List<Permission> selectAll();

    List<Permission> selectChild(String pid);

}
