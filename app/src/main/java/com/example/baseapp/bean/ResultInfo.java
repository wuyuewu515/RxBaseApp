package com.example.baseapp.bean;

/**
 * @author: Five_伍
 * @create: 2019/1/23
 * @Describe: 接口返回的数据总类---最强王者
 */
public class ResultInfo<T> {

    private String errorMsg; //提示信息
    private int errorCode;//请求码--与后台约定的
    private T data;//返回的数据信息

    public String getMessage() {
        return errorMsg;
    }

    public ResultInfo setMessage(String message) {
        this.errorMsg = message;
        return this;
    }

    public int getCode() {
        return errorCode;
    }

    public ResultInfo setCode(int code) {
        this.errorCode = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultInfo setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "message='" + errorMsg + '\'' +
                ", code=" + errorCode +
                ", data=" + data +
                '}';
    }

//    class DataInfo<T> implements Serializable {
//        @Expose
//        private int status;
//        @Expose
//        private String msg;
//        @Expose
//        private T t;
//
//        public int getStatus() {
//            return status;
//        }
//
//        public DataInfo<T> setStatus(int status) {
//            this.status = status;
//            return this;
//        }
//
//        public String getMsg() {
//            return msg;
//        }
//
//        public DataInfo<T> setMsg(String msg) {
//            this.msg = msg;
//            return this;
//        }
//
//        public T getT() {
//            return t;
//        }
//
//        public DataInfo<T> setT(T t) {
//            this.t = t;
//            return this;
//        }
//
//        @Override
//        public String toString() {
//            return "DataInfo{" +
//                    "status=" + status +
//                    ", msg='" + msg + '\'' +
//                    ", t=" + t +
//                    '}';
//        }
//    }
}

