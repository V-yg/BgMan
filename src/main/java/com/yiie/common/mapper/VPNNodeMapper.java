package com.yiie.common.mapper;

import com.yiie.entity.VPNNode;
import com.yiie.vo.request.VPNNodePageReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Time： 2020-3-10 17:20
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
@Repository
@Mapper
public interface VPNNodeMapper {
    List<VPNNode> selectAll(VPNNodePageReqVO vo);

    int deletedNodes(List<String> nodeIds);

    VPNNode selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VPNNode vpnNode);

    int insertSelective(VPNNode vpnNode);
}
