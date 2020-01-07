package com.yiie.common.service;

import com.yiie.entity.Dept;
import com.yiie.entity.User;
import com.yiie.vo.request.DeptAddReqVO;
import com.yiie.vo.request.DeptPageReqVO;
import com.yiie.vo.request.DeptUpdateReqVO;
import com.yiie.vo.request.UserPageUserByDeptReqVO;
import com.yiie.vo.response.DeptRespNodeVO;
import com.yiie.vo.response.PageVO;

import java.util.List;

/**
 * Time：2020-1-2 15:33
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
public interface DeptService {

    Dept detailInfo(String id);

    List<DeptRespNodeVO> deptTreeList(String deptId);

    Dept addDept(DeptAddReqVO vo);

    void updateDept(DeptUpdateReqVO vo);

    void deleted(String id);

    PageVO<Dept> pageInfo(DeptPageReqVO vo);

    PageVO<User> pageDeptUserInfo(UserPageUserByDeptReqVO vo);

    List<Dept> selectAll();
}
