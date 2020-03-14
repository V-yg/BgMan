package com.yiie.entity;

import java.util.Date;

/**
 * Time： 2020-3-10 10:33
 * Email： yiie315@163.com
 * Desc：
 *
 * @Author： yiie
 * @Version： 1.0
 */
public class VPNUser {

    private String id;

    private String username;

    private String email;

    private Integer status;

    private Integer deleted;

    private Date createTime; //创建时间

    private String level; //会员等级

    private Date lastLogin; //最后登录时间

    private Date expirationTime; //会员过期时间

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status == null ? null : status;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted == null ? null : deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "VPNUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", deleted=" + deleted +
                ", createTime=" + createTime +
                ", level='" + level + '\'' +
                ", lastLogin=" + lastLogin +
                ", expirationTime=" + expirationTime +
                '}';
    }
}
