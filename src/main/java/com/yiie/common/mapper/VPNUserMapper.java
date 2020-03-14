package com.yiie.common.mapper;

import com.yiie.entity.Role;
import com.yiie.entity.User;
import com.yiie.entity.VPNUser;
import com.yiie.vo.request.RolePageReqVO;
import com.yiie.vo.request.VPNUserPageReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Time： 2020-3-10 11:59
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
@Repository
@Mapper
public interface VPNUserMapper {

    List<VPNUser> selectAll(VPNUserPageReqVO vo);

    int deletedUsers(@Param("list") List<String> list);

    int insertSelective(VPNUser vpnUser);

    VPNUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VPNUser vpnUser);
}
