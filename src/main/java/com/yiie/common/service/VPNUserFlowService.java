package com.yiie.common.service;

import com.yiie.entity.VPNUserFlow;
import com.yiie.vo.request.VPNUserFlowPageReqVO;
import com.yiie.vo.request.VPNUserFlowUpdateReqVO;
import com.yiie.vo.response.PageVO;

/**
 * Time： 2020-3-11 11:08
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
public interface VPNUserFlowService {
    PageVO<VPNUserFlow> pageInfo(VPNUserFlowPageReqVO vo);

    void updateUserInfo(VPNUserFlowUpdateReqVO vo);
}
