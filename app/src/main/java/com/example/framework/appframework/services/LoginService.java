package com.example.framework.appframework.services;

import com.example.framework.appframework.model.BaseEntity;
import com.example.framework.appframework.model.LoginInfo;
import com.example.framework.appframework.model.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 公司：
 * 项目：
 * 简述：
 * 作者：Chenxp
 * 时间：2018/6/8
 * 版本：V1.0.0
 */
public interface LoginService {

    /*@GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Observable<Translation> getTranlateResult();

    //传入请求参数
    @GET("ajax.php?a=fy&f=auto&t=auto")
    Observable<Translation> getTranlateResult(@Query("w") String w);


    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
        //@Headers("t:1")
    Observable<Translation1> getTranlateResultPost(@Field("i") String targetSentence);

    @POST("translate")
    Observable<Translation1> getTranlateResultPost2(@Body LoginParams loginParams);
*/


    @POST("/appGateway/sysBaseApp/doAppLogin")
    Observable<BaseEntity<LoginInfo>> login(@Body UserInfo loginInfo);

    /*@POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    //@Headers("t:1")
    Observable<BaseEntity<TestBean>> login(@Field("i") String targetSentence);*/


    /*@POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
        //@Headers("t:1")
    Observable<TestBean1> login(@Field("i") String targetSentence);
*/

}
