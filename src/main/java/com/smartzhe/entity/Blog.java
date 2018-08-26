package com.smartzhe.entity;

import java.util.Date;

public class Blog {
    /**
     * 
     * 表 : Blog
     * 对应字段 : id
     */
    private Integer id;

    /**
     * 
     * 表 : Blog
     * 对应字段 : title
     */
    private String title;

    /**
     * 
     * 表 : Blog
     * 对应字段 : articleEditType
     */
    private Integer articleEditType;

    /**
     * 
     * 表 : Blog
     * 对应字段 : description
     */
    private String description;

    /**
     * 
     * 表 : Blog
     * 对应字段 : privateType
     */
    private Integer privateType;

    /**
     * 
     * 表 : Blog
     * 对应字段 : tags
     */
    private String tags;

    /**
     * 
     * 表 : Blog
     * 对应字段 : categories
     */
    private String categories;

    /**
     * 
     * 表 : Blog
     * 对应字段 : type
     */
    private Integer type;

    /**
     * 
     * 表 : Blog
     * 对应字段 : status
     */
    private Integer status;

    /**
     * 
     * 表 : Blog
     * 对应字段 : timeCreate
     */
    private Date timeCreate;

    /**
     * 
     * 表 : Blog
     * 对应字段 : timeUpdate
     */
    private Date timeUpdate;

    /**
     * 
     * 表 : Blog
     * 对应字段 : channel
     */
    private Integer channel;

    /**
     * 
     * 表 : Blog
     * 对应字段 : content
     */
    private String content;

    /**
     * 
     * 表 : Blog
     * 对应字段 : markdownContent
     */
    private String markdownContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getArticleEditType() {
        return articleEditType;
    }

    public void setArticleEditType(Integer articleEditType) {
        this.articleEditType = articleEditType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrivateType() {
        return privateType;
    }

    public void setPrivateType(Integer privateType) {
        this.privateType = privateType;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMarkdownContent() {
        return markdownContent;
    }

    public void setMarkdownContent(String markdownContent) {
        this.markdownContent = markdownContent;
    }
}