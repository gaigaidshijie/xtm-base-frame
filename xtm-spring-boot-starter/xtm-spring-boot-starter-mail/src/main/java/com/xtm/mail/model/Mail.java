package com.xtm.mail.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * <p>Description:[] </p>
 * Created on : 2020/8/19 09:51
 *
 * @author: <a href="mailto: xietianmi@gamebh.cn">xietianmi</a>
 * version 1.0
 * Copyright (c) 2020 贵州多彩博虹科技有限公司
 */
public class Mail implements Serializable {

    /**
     * 是否包含附件
     */
    private int type=0;

    /**
     * 接收方邮件
     */
    private String[] email;
    /**
     * 主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;

    /**
     * 附件地址
     */
    private String[] fileUrl;

    /**
     * 图片地址
     */
    private String[] imgUrl;
    /**
     * 模板
     */
    private String template;
    /**
     * 自定义参数
     */
    private HashMap<String, String> kvMap;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String[] getEmail() {
        return email;
    }

    public void setEmail(String[] email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public HashMap<String, String> getKvMap() {
        return kvMap;
    }

    public void setKvMap(HashMap<String, String> kvMap) {
        this.kvMap = kvMap;
    }

    public String[] getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String[] fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String[] getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String[] imgUrl) {
        this.imgUrl = imgUrl;
    }
}
