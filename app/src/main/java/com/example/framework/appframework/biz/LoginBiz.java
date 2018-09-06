package com.example.framework.appframework.biz;

import com.example.framework.appframework.model.LoginInfo;
import com.example.framework.appframework.model.UserInfo;
import com.example.framework.appframework.services.LoginService;
import com.example.framework.appframework.util.BaseObserver1;
import com.example.framework.appframework.util.HttpClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 公司：
 * 项目：
 * 简述：
 * 作者：Chenxp
 * 时间：2018/6/8
 * 版本：V1.0.0
 */
public class LoginBiz {

    //Rxjava2.x

    /**
     * login get request
     * @param observer
     */
    /*public void loginGet(Observer<Translation> observer) {
        HttpClient.creatService(LoginService.class).getTranlateResult()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }*/

    /*public void loginPost(Observer<Translation1> observer, String str) {
        HttpClient.creatService(LoginService.class).getTranlateResultPost(str)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }*/

   /* public void loginPost(BaseObserver<Translation1> observer, String str) {
        HttpClient.creatService(LoginService.class).getTranlateResultPost(str)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }*/


    /*public void loginPost(BaseObserver<TestBean1> observer, String str) {
        HttpClient.creatService(LoginService.class).login(str)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }*/

    /*public void loginPost(BaseObserver1<TestBean> observer, String str) {
        HttpClient.creatService(LoginService.class).login(str)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }*/


    public void loginPost(BaseObserver1<LoginInfo> observer, UserInfo loginInfo) {
        HttpClient.creatService(LoginService.class).login(loginInfo)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }



}
