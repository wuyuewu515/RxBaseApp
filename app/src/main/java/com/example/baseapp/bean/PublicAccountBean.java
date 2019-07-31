package com.example.baseapp.bean;

import java.util.List;

/**
 * @ClassName: PublicAccountBean
 * @Author: 伍跃武
 * @Date: 2019/7/30 16:12
 * @Description:
 */
public class PublicAccountBean {
    private List<BeanData> data;
    private int errorCode;
    private String errorMsg;
    public void setData(List<BeanData> data) {
        this.data = data;
    }
    public List<BeanData> getData() {
        return data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
}
