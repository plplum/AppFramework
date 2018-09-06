package com.example.framework.appframework.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.framework.appframework.R;
import com.example.framework.appframework.activity.login.LoginActivity;
import com.example.framework.appframework.util.Util;

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


    @Override
    protected int getFragmentRes() {
        return R.layout.fragment_1;
    }

    @Override
    protected void initView(View view) {

    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        Util.openActivity(getActivity(), LoginActivity.class, null);
    }
}
