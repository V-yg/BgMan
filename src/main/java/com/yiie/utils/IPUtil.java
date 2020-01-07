package com.yiie.utils;

import org.omg.CORBA.UNKNOWN;

import javax.servlet.http.HttpServletRequest;

/**
 * Time：2020-1-2 20:11
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
public class IPUtil {

    private static final String UNKNOWN = "unknown";

    protected IPUtil(){

    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
