package com.yiie.common.service;

import com.yiie.entity.User;
import com.yiie.vo.request.*;
import com.yiie.vo.response.HomeRespVO;
import com.yiie.vo.response.LoginRespVO;
import com.yiie.vo.response.PageVO;
import com.yiie.vo.response.UserOwnRoleRespVO;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Time：2020-1-1 17:50
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
public interface UserService {

    /**
     * 用户登录接口
     * @param vo
     * @return
     */
    LoginRespVO login(LoginReqVO vo);

    void logout(String accessToken,String refreshToken);

    User detailInfo(String userId);

    HomeRespVO getHomeInfo(String userId);

    String refreshToken(String refreshToken,String accessToken);

    PageVO<User> pageInfo(UserPageReqVO vo);

    void addUser(UserAddReqVO vo);

    void updateUserInfo(UserUpdateReqVO vo, String operationId);

    void setUserOwnRole(String userId, List<String> roleIds, HttpServletRequest request);

    void deletedUsers(List<String> userIds,String operationId);

    UserOwnRoleRespVO getUserOwnRole(String userId, HttpServletRequest request);

    List<User> getUserListByDeptIds(List<String> deptIds);

    PageVO<User> selectUserInfoByDeptIds(int pageNum, int pageSize,List<String> deptIds);

    void updatePwd(UpdatePasswordReqVO vo, String userId, String accessToken, String refreshToken);

}
