package com.example.baseapp.net.api;


import com.example.baseapp.bean.ResultInfo;
import com.example.baseapp.bean.UserLoginInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author: Five_伍
 * @create: 2019/1/23
 * @Describe: 接口类
 */
public interface ExampleAPI {


    /**
     * @param userName   账号
     * @param pwd        密码
     * @param deviceCode 设备号
     * @return
     */
    @FormUrlEncoded
    @POST(APIConstant.LOGIN_URL)
    Observable<ResultInfo<UserLoginInfo>> login(@Field("user") String userName,
                                                @Field("pass") String pwd,
                                                @Field("deviceCode") String deviceCode);

}
