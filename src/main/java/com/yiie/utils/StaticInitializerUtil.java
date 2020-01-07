package com.yiie.utils;

import org.springframework.stereotype.Component;

/**
 * Time：2020-1-2 11:09
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@Component
public class StaticInitializerUtil {

    private TokenSettings tokenSettings;

    public StaticInitializerUtil(TokenSettings tokenSettings) {
        JwtTokenUtil.setTokenSettings(tokenSettings);
    }
}
