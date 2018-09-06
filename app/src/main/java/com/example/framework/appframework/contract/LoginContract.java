package com.example.framework.appframework.contract;

import com.example.framework.appframework.BaseView;
import com.example.framework.appframework.model.LoginInfo;
import com.example.framework.appframework.model.UserInfo;

/**
 * 公司：
 * 项目：
 * 简述：
 * 作者：Chenxp
 * 时间：2018/6/7
 * 版本：V1.0.0
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void showUserInfo(String data);
    }

    interface Presenter {

        void login(String json);
        void login1(UserInfo loginInfo);
    }
}
