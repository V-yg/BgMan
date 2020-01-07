package com.yiie.common.mapper;

import com.yiie.entity.Role;
import com.yiie.vo.request.RolePageReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Time：2020-1-2 9:50
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@Repository
@Mapper
public interface RoleMapper {

    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getRoleInfoByIds(List<String> ids);

    List<Role> selectAll(RolePageReqVO vo);
}
