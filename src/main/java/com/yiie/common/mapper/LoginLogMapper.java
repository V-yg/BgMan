package com.yiie.common.mapper;

import com.yiie.entity.LoginLog;
import com.yiie.vo.request.LogPageReqVO;
import com.yiie.vo.request.LoginLogPageReqVO;
import com.yiie.vo.response.NameAndCntVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sun.security.krb5.internal.LoginOptions;

import java.util.List;

/**
 * Time：2020-1-2 21:01
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@Repository
@Mapper
public interface LoginLogMapper {

    int saveLoginLog(LoginLog log);

    List<LoginLog> selectAll(LoginLogPageReqVO vo);

    void batchDeletedLog(List<String> logIds);

    int getTotalSuccessLoginCnt();

    int getTodaySuccessLoginCnt();

    int getWeekSuccessLoginCnt();

    int getTodayFailLoginCnt();

    int getTotalFailLoginCnt();

    List<NameAndCntVO> getDateAndCntList();

}
