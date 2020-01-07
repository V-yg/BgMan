package com.yiie.common.mapper;

import com.yiie.entity.Log;
import com.yiie.vo.request.LogPageReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Time：2020-1-4 14:26
 * Email：yiie315@163.com
 * Desc：TODO
 *
 * @Author：yiie
 * @Version：1.0.0
 */
@Repository
@Mapper
public interface OperLogMapper {

    int deleteByPrimaryKey(String id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    List<Log> selectAll(LogPageReqVO vo);

    void batchDeletedLog(List<String> logIds);
}
