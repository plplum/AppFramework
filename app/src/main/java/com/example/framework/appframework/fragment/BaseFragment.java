package com.example.framework.appframework.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.framework.appframework.BaseApplication;
import com.example.framework.appframework.model.LoginInfo;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 公司：
 * 项目：
 * 简述：应用中所有Fragment的基类
 * 作者：Chenxp
 * 时间：2017-9-20
 * 版本：V1.0.0
 */
public abstract class BaseFragment extends Fragment {

    public BaseApplication mApplication;

    protected String mTask;

    protected Bundle bundle;

    protected int mTheme;

    protected SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());

    //private WaitDialog mWaitDialog;

    protected LoginInfo mLoginInfo;

    Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundle = getArguments();
        if (bundle != null) {
            //mTask = bundle.getString(CommonActivity.BUNDLE_KEY_TASK);
        }
        mApplication = BaseApplication.getApplication();
        mLoginInfo = mApplication.getLoginInfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getFragmentRes(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initView(view);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected abstract int getFragmentRes();

    protected abstract void initView(View view);

    protected void initData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
