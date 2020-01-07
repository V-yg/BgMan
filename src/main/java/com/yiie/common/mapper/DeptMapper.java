package com.yiie.common.mapper;

import com.yiie.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Time：2020-1-2 15:35
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@Repository
@Mapper
public interface DeptMapper {

    int deleteByPrimaryKey(String id);

    int insert(Dept record);

    int insertSelective(Dept record);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    /**
     * 多个数据 要用 @Param
     * @param oldStr
     * @param newStr
     * @param relationCode
     * @return
     */
    int updateRelationCode(@Param("oldStr") String oldStr, @Param("newStr") String newStr, @Param("relationCode") String relationCode);

    Dept selectByPrimaryKey(String id);

    List<Dept> selectAll();

    List<String> selectChildIds(String relationCode);

    List<Dept> selectAllByNotContainChild(List<String> list);
}
