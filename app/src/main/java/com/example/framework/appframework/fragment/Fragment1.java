package com.example.framework.appframework.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.framework.appframework.R;
import com.example.framework.appframework.activity.login.LoginActivity;
import com.example.framework.appframework.util.Util;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 公司：
 * 项目：
 * 简述：
 * 作者：Chenxp
 * 时间：2018/6/6
 * 版本：V1.0.0
 */
public class Fragment1 extends BaseFragment {

    private static final String TAG = Fragment1.class.getSimpleName();


    @Override
    protected int getFragmentRes() {
        return R.layout.fragment_1;
    }

    @Override
    protected void initView(View view) {

        String[] titles = {"我的自选", "资讯直播", "行情直播", "发布订单", "消息中心", "交易行情", "订单管理", "贸易伙伴"};

        Logger.t(TAG).i("init view.");
        Logger.d("hello %s", "world");

//        Logger.d(MAP);
//        Logger.d(SET);
//        Logger.d(LIST);
        Logger.d(titles);
//        Logger.json(JSON_CONTENT);
//        Logger.xml(XML_CONTENT);


    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        Util.openActivity(getActivity(), LoginActivity.class, null);
    }
}
