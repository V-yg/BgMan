package com.yiie.common.service;

import com.yiie.entity.User;
import com.yiie.entity.VPNUser;
import com.yiie.vo.request.*;
import com.yiie.vo.response.PageVO;

import java.util.List;

/**
 * Time： 2020-3-10 11:47
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
public interface VPNUserService {

    PageVO<VPNUser> pageInfo(VPNUserPageReqVO vo);

    void deletedUsers(List<String> userIds);

    void addUser(VPNUserAddReqVO vo);

    void updateUserInfo(VPNUserUpdateReqVO vo);
}
