package com.yiie.common.service;

import com.yiie.entity.VPNNode;
import com.yiie.vo.request.VPNNodeAddReqVO;
import com.yiie.vo.request.VPNNodePageReqVO;
import com.yiie.vo.request.VPNNodeUpdateReqVO;
import com.yiie.vo.response.PageVO;

import java.util.List;

/**
 * Time： 2020-3-10 17:21
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
public interface VPNNodeService {
    PageVO<VPNNode> pageInfo(VPNNodePageReqVO vo);

    void deletedNodes(List<String> nodeIds);

    void updateUserInfo(VPNNodeUpdateReqVO vo);

    void addUser(VPNNodeAddReqVO vo);
}
