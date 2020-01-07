package com.yiie.utils;

import java.util.UUID;

/**
 * Time：2020-1-2 8:56
 * Email： yiie315@163.com
 * Desc：密码工具类
 *
 * @author： yiie
 * @version：1.0.0
 */
public class PasswordUtil {
    /**
     * 匹配密码
     * @param salt 盐
     * @param rawPass 明文
     * @param encPass 密文
     * @return
     */
    public static boolean matches(String salt, String rawPass, String encPass) {
        return new PasswordEncoderUtil(salt).matches(encPass, rawPass);
    }

    /**
     * 明文密码加密
     * @param rawPass 明文
     * @param salt
     * @return
     */
    public static String encode(String rawPass, String salt) {
        return new PasswordEncoderUtil(salt).encode(rawPass);
    }

    /**
     * 获取加密盐
     * @return
     */
    public static String getSalt() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
    }
}
