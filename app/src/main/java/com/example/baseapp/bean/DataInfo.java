package com.example.baseapp.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * @author: Five_伍
 * @create: 2019/1/24
 * @Describe:
 */
public class DataInfo implements Serializable {
    @Expose
    protected int status;
    @Expose
    protected String msg;


    public int getStatus() {
        return status;
    }

    public DataInfo setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public DataInfo setMsg(String msg) {
        this.msg = msg;
        return this;
    }


    @Override
    public String toString() {
        return "DataInfo{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
