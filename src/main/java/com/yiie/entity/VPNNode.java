package com.yiie.entity;


import java.util.Date;

/**
 * Time： 2020-3-10 17:15
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
@lombok.Data
public class VPNNode {

    private Integer id;

    private String node;

    private String ip;

    private Integer port;

    private Date createTime;

    private String protocol;

    private Integer status;

    private Integer maxUp;

    private Integer maxDown;

    private Integer delay;

    private String link;

    private Integer deleted;

}