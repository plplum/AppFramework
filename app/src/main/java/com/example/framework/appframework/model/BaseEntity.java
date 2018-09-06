package com.example.framework.appframework.model;

import java.io.Serializable;

public class BaseEntity<E> implements Serializable {


    private int msgCode;

    private String message;

    private E data;


    public int getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}

