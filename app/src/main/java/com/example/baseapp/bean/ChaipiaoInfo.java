package com.example.baseapp.bean;

/**
 * 彩票的数据实体类
 */
public class ChaipiaoInfo {
//    "openCode": "10,12,15,25,26,27+14",
//    "code": "ssq",
//    "expect": "2018136",
//            "name": "双色球",
    //             "time": "2018-11-20 21:18:20"

    private String openCode;
    private String code;
    private String expect;
    private String name;
    private String time;

    public String getOpenCode() {
        return openCode;
    }

    public ChaipiaoInfo setOpenCode(String openCode) {
        this.openCode = openCode;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ChaipiaoInfo setCode(String code) {
        this.code = code;
        return this;
    }

    public String getExpect() {
        return expect;
    }

    public ChaipiaoInfo setExpect(String expect) {
        this.expect = expect;
        return this;
    }

    public String getName() {
        return name;
    }

    public ChaipiaoInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getTime() {
        return time;
    }

    public ChaipiaoInfo setTime(String time) {
        this.time = time;
        return this;
    }
}
