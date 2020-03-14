package com.yiie.entity;

import lombok.Data;

import java.security.SecureRandom;

/**
 * Time： 2020-3-10 10:33
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
@Data
public class VPNUserFlow {

    private String id;

    private String username;

    private Integer credit;

    private Integer connect;

    private Integer money;

    private Integer flow;

    private Integer maxUp;

    private Integer maxDown;

    private Integer deleted;

}
