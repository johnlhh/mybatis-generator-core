package com.test.entity;

import java.util.Date;

public class ServicePlus {
    /**
     * 
     * 表 : ServicePlus
     * 对应字段 : id
     */
    private Integer id;

    /**
     * 
     * 表 : ServicePlus
     * 对应字段 : service_id
     */
    private Integer serviceId;

    /**
     * 
     * 表 : ServicePlus
     * 对应字段 : name
     */
    private String name;

    /**
     * 
     * 表 : ServicePlus
     * 对应字段 : description
     */
    private String description;

    /**
     * 
     * 表 : ServicePlus
     * 对应字段 : icon
     */
    private String icon;

    /**
     * 
     * 表 : ServicePlus
     * 对应字段 : mobile_icon
     */
    private String mobileIcon;

    /**
     * Icon.id
     * 表 : ServicePlus
     * 对应字段 : iconId
     */
    private Integer iconId;

    /**
     * 
     * 表 : ServicePlus
     * 对应字段 : passport_url
     */
    private String passportUrl;

    /**
     * 
     * 表 : ServicePlus
     * 对应字段 : banner_url
     */
    private String bannerUrl;

    /**
     * 
     * 表 : ServicePlus
     * 对应字段 : time_recycle
     */
    private Date timeRecycle;

    /**
     * 
     * 表 : ServicePlus
     * 对应字段 : deadline_restore
     */
    private Date deadlineRestore;

    /**
     * 
     * 表 : ServicePlus
     * 对应字段 : extra
     */
    private String extra;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMobileIcon() {
        return mobileIcon;
    }

    public void setMobileIcon(String mobileIcon) {
        this.mobileIcon = mobileIcon;
    }

    public Integer getIconId() {
        return iconId;
    }

    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    public String getPassportUrl() {
        return passportUrl;
    }

    public void setPassportUrl(String passportUrl) {
        this.passportUrl = passportUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public Date getTimeRecycle() {
        return timeRecycle;
    }

    public void setTimeRecycle(Date timeRecycle) {
        this.timeRecycle = timeRecycle;
    }

    public Date getDeadlineRestore() {
        return deadlineRestore;
    }

    public void setDeadlineRestore(Date deadlineRestore) {
        this.deadlineRestore = deadlineRestore;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}