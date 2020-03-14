package com.yiie.common.mapper;

import com.yiie.entity.VPNUserFlow;
import com.yiie.vo.request.VPNUserFlowPageReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Time： 2020-3-11 11:10
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
@Repository
@Mapper
public interface VPNUserFlowMapper {

    List<VPNUserFlow> selectAll(VPNUserFlowPageReqVO vo);

    int updateByPrimaryKeySelective(VPNUserFlow vpnUserFlow);
}
