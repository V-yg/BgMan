package com.yiie.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Time：2020-1-2 17:20
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
public class CustomPasswordToken extends UsernamePasswordToken {
    private String token;

    public CustomPasswordToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}
