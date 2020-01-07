package com.yiie.utils;

import com.github.pagehelper.Page;
import com.yiie.vo.response.PageVO;

import java.util.List;

/**
 * Time：2020-1-3 9:31
 * Email： yiie315@163.com
 * Desc：分页工具类
 *
 * @author： yiie
 * @version：1.0.0
 */
public class PageUtils {
    private PageUtils() {}

    public static <T> PageVO<T> getPageVO(List<T> list) {
        PageVO<T> result = new PageVO<>();
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            result.setTotalRows(page.getTotal());
            result.setTotalPages(page.getPages());
            result.setPageNum(page.getPageNum());
            result.setPageSize(page.getPageSize());
            result.setCurPageSize(page.size());
            result.setList(page.getResult());
        }
        return result;
    }
}
