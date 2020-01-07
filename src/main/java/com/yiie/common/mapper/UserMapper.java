package com.yiie.common.mapper;

import com.yiie.entity.User;
import com.yiie.vo.request.UserPageReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Time：2020-1-1 22:38
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@Repository
@Mapper
public interface UserMapper {

    /**
     * 通过用户名获取用户信息
     * @param username 用户名
     * @return
     */
    User getUserInfoByName(String username);

    User selectByPrimaryKey(String userId);

    List<User> selectAll(UserPageReqVO vo);

    List<User> selectUserInfoByDeptIds (List<String> deptIds);

    List<User> getUserListByDeptId(String deptId);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    int deletedUsers(@Param("sysUser") User sysUser, @Param("list") List<String> list);
}
