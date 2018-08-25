package com.smartzhe.entity;

import java.util.Date;

public class Account {
    /**
     * 
     * 表 : Account
     * 对应字段 : id
     */
    private Integer id;

    /**
     * 姓名
     * 表 : Account
     * 对应字段 : name
     */
    private String name;

    /**
     * 邮箱
     * 表 : Account
     * 对应字段 : email
     */
    private String email;

    /**
     * 
     * 表 : Account
     * 对应字段 : mobile
     */
    private String mobile;

    /**
     * 
     * 表 : Account
     * 对应字段 : password
     */
    private String password;

    /**
     * 
     * 表 : Account
     * 对应字段 : timeCreate
     */
    private Date timeCreate;

    /**
     * 
     * 表 : Account
     * 对应字段 : timeUpdate
     */
    private Date timeUpdate;

    /**
     * 
     * 表 : Account
     * 对应字段 : lastLoginTime
     */
    private Date lastLoginTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Date getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Date timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}