package com.example.baseapp.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

import okhttp3.Response;

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

    class UserInfo implements Serializable {
//             "userid": 93,
//            "_name": "五月五",
//            "username": "15601645052",
//            "token": "93-1549941906307-28950D634FF8363F762A855E014288CC",
//            "istate": 1,
//            "company_id": 66,
//            "rightid": 3,
//            "isp": 0,
//            "company_name": "左驾好车测试",
//            "company_logo": "http://zuojiachetest.oss-cn-beijing.aliyuncs.com/companylogo/66/logo.jpg",
//            "province": "北京",
//            "city": "北京",
//            "province_id": 110000,
//            "city_id": 110100,
//            "area": "朝阳区",
//            "areaid": 110105,
//            "_position": "管理员",
//            "ver": "1.0"

        @Expose
        private int userid;
        @Expose
        private String _name;
        @Expose
        private String username;
        @Expose
        private String token;
        @Expose
        private String company_name;
        @Expose
        private String company_logo;
        @Expose
        private String province_id;
        @Expose
        private String province;
        @Expose
        private String city;
        @Expose
        private String city_id;
        @Expose
        private String area;
        @Expose
        private String areaid;
        @Expose
        private String _position;
        @Expose
        private String ver;
        @Expose
        private int istate;
        @Expose
        private int company_id;
        @Expose
        private int rightid;
        @Expose
        private int isp;

        public int getUserid() {
            return userid;
        }

        public UserInfo setUserid(int userid) {
            this.userid = userid;
            return this;
        }

        public String get_name() {
            return _name;
        }

        public UserInfo set_name(String _name) {
            this._name = _name;
            return this;
        }

        public String getUsername() {
            return username;
        }

        public UserInfo setUsername(String username) {
            this.username = username;
            return this;
        }

        public String getToken() {
            return token;
        }

        public UserInfo setToken(String token) {
            this.token = token;
            return this;
        }

        public String getCompany_name() {
            return company_name;
        }

        public UserInfo setCompany_name(String company_name) {
            this.company_name = company_name;
            return this;
        }

        public String getCompany_logo() {
            return company_logo;
        }

        public UserInfo setCompany_logo(String company_logo) {
            this.company_logo = company_logo;
            return this;
        }

        public String getProvince_id() {
            return province_id;
        }

        public UserInfo setProvince_id(String province_id) {
            this.province_id = province_id;
            return this;
        }

        public String getProvince() {
            return province;
        }

        public UserInfo setProvince(String province) {
            this.province = province;
            return this;
        }

        public String getCity() {
            return city;
        }

        public UserInfo setCity(String city) {
            this.city = city;
            return this;
        }

        public String getCity_id() {
            return city_id;
        }

        public UserInfo setCity_id(String city_id) {
            this.city_id = city_id;
            return this;
        }

        public String getArea() {
            return area;
        }

        public UserInfo setArea(String area) {
            this.area = area;
            return this;
        }

        public String getAreaid() {
            return areaid;
        }

        public UserInfo setAreaid(String areaid) {
            this.areaid = areaid;
            return this;
        }

        public String get_position() {
            return _position;
        }

        public UserInfo set_position(String _position) {
            this._position = _position;
            return this;
        }

        public String getVer() {
            return ver;
        }

        public UserInfo setVer(String ver) {
            this.ver = ver;
            return this;
        }

        public int getIstate() {
            return istate;
        }

        public UserInfo setIstate(int istate) {
            this.istate = istate;
            return this;
        }

        public int getCompany_id() {
            return company_id;
        }

        public UserInfo setCompany_id(int company_id) {
            this.company_id = company_id;
            return this;
        }

        public int getRightid() {
            return rightid;
        }

        public UserInfo setRightid(int rightid) {
            this.rightid = rightid;
            return this;
        }

        public int getIsp() {
            return isp;
        }

        public UserInfo setIsp(int isp) {
            this.isp = isp;
            return this;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "userid=" + userid +
                    ", _name='" + _name + '\'' +
                    ", username='" + username + '\'' +
                    ", token='" + token + '\'' +
                    ", company_name='" + company_name + '\'' +
                    ", company_logo='" + company_logo + '\'' +
                    ", province_id='" + province_id + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", city_id='" + city_id + '\'' +
                    ", area='" + area + '\'' +
                    ", areaid='" + areaid + '\'' +
                    ", _position='" + _position + '\'' +
                    ", ver='" + ver + '\'' +
                    ", istate=" + istate +
                    ", company_id=" + company_id +
                    ", rightid=" + rightid +
                    ", isp=" + isp +
                    '}';
        }
    }
}
