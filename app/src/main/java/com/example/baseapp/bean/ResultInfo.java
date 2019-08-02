package com.example.baseapp.bean;

/**
 * @author: Five_伍
 * @create: 2019/1/23
 * @Describe: 接口返回的数据总类---最强王者
 */
public class ResultInfo<T> {

    private String msg; //提示信息
    private int code;//请求码--与后台约定的
    private T data;//返回的数据信息

    public String getMessage() {
        return msg;
    }

    public ResultInfo setMessage(String message) {
        this.msg = message;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ResultInfo setCode(int code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultInfo setData(T data) {
        this.data = data;
        return this;
    }
}

