package com.example.baseapp.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * @author: Five_伍
 * @create: 2019/2/12
 * @Describe: 用户的登陆信息
 */
public class UserLoginInfo implements Serializable {
    @Expose
    private int status;
    @Expose
    private String msg;
    @Expose
    private UserInfo userInfo;


    public UserInfo getUserInfo() {
        return userInfo;
    }

    public UserLoginInfo setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public UserLoginInfo setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public UserLoginInfo setMsg(String msg) {
        this.msg = msg;
        return this;
    }


    @Override
    public String toString() {
        return "DataInfo{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }


}
