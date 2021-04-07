package com.example.framework.appframework.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.framework.appframework.biz.LoginBiz;
import com.example.framework.appframework.contract.LoginContract;
import com.example.framework.appframework.model.BaseEntity;
import com.example.framework.appframework.model.LoginInfo;
import com.example.framework.appframework.model.UserInfo;
import com.example.framework.appframework.services.LoginService;
import com.example.framework.appframework.util.BaseObserver1;
import com.example.framework.appframework.util.HttpClient;

import io.reactivex.Observable;

/**
 * 公司：
 * 项目：
 * 简述：
 * 作者：Chenxp
 * 时间：2018/6/7
 * 版本：V1.0.0
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mLoginView;

    private Activity mActivity;

    public LoginPresenter(@NonNull LoginContract.View loginView, Activity activity) {
        mLoginView = loginView;
        mActivity = activity;
        mLoginView.setPresenter(this);
    }


    @Override
    public void login(String json) {
        System.out.println(json);
        LoginBiz loginBiz = new LoginBiz();

        String str = "I love you";
        //post 请求-- 对应BASE_URL
        /*loginBiz.loginPost(new Observer<Translation1>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Translation1 value) {
                mLoginView.showUserInfo(value.getType());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, str);*/


        //get 请求-- 对应BASE_URL1
        /*loginBiz.loginGet(new Observer<Translation>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Translation value) {
                mLoginView.showUserInfo(value.toString());
                value.show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/

        /*loginBiz.loginPost(new BaseObserver<Translation1>(mActivity, true){

            @Override
            public void onSuccess(Translation1 t) {
                System.out.println(t);
            }

            @Override
            public void onFailure(int code, String message) {
                super.onFailure(code, message);
            }
        } ,str);*/

        /*loginBiz.loginPost(new BaseObserver<TestBean1>(mActivity, true){

            @Override
            public void onSuccess(TestBean1 t) {
                System.out.println(t);
            }

            @Override
            public void onFailure(int code, String message) {
                super.onFailure(code, message);
            }
        } ,str);*/


        /*loginBiz.loginPost(new BaseObserver1<LoginInfo>(mActivity, true){

            @Override
            public void onSuccess(LoginInfo t) {
                System.out.println(t);
            }

            @Override
            public void onFailure(int code, String message) {
                super.onFailure(code, message);
            }
        } ,str);*/


    }

    @Override
    public void login1(UserInfo loginInfo) {
        Observable<BaseEntity<LoginInfo>> observable = HttpClient.creatService(LoginService.class).login(loginInfo);
        HttpClient.executeMethod(observable,  new BaseObserver1<LoginInfo>(mActivity, true){
            @Override
            public void onSuccess(LoginInfo t) {
                mLoginView.showUserInfo(t.getUserId());
            }
        });

        /*LoginBiz loginBiz = new LoginBiz();
        loginBiz.loginPost(new BaseObserver1<LoginInfo>(mActivity, true){

            @Override
            public void onSuccess(LoginInfo t) {
                System.out.println(t);
                mLoginView.showUserInfo(t.getUserId());

            }

            @Override
            public void onFailure(int code, String message) {
                super.onFailure(code, message);
            }
        } ,loginInfo);
*/


    }
}
