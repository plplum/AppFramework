package com.example.framework.appframework.model;

import java.io.Serializable;

/**
 * 公司：
 * 项目：
 * 简述：
 * 作者：Chenxp
 * 时间：2018/6/27
 * 版本：V1.0.0
 */
public class Admin implements Serializable {

    private static final long serialVersionUID = 2598845360908912190L;

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
