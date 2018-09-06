package com.example.framework.appframework.activity;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.framework.appframework.BaseApplication;
import com.example.framework.appframework.dialog.WaitDialog;
import com.example.framework.appframework.model.LoginInfo;
import com.example.framework.appframework.util.AppManager;
import com.example.framework.appframework.util.Util;

import butterknife.ButterKnife;

import static com.example.framework.appframework.util.Util.USER_OBJ;


/**
 * 公司：
 * 项目：
 * 简述：所有activity的基类
 * 作者：Chenxp
 * 时间：2017/9/20
 * 版本：V1.0.0
 */

public abstract class BaseActivity extends AppCompatActivity {

    public BaseApplication mApplication;

    /** layoutResID */
    protected int mLayoutResId;

    protected WaitDialog mWaitDialog;

    protected LoginInfo mLoginInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = BaseApplication.getApplication();
        LoginInfo loginInfo = (LoginInfo) Util.getObject(USER_OBJ, mApplication.getSharedPreferences());
        mApplication.setLoginInfo(loginInfo);

        int language = BaseApplication.getAppContext().getInt(BaseApplication.APP_LANGUAGE);
        //change app language
        BaseApplication.setAppLanguage(getBaseContext(), language);

        mLayoutResId = getLayoutResId();
        if (mLayoutResId != -1)
            setContentView(getLayoutResId());
        // add current activity to stack
        AppManager.getAppManager().addActivity(this);

        mLoginInfo = BaseApplication.getAppContext().getLoginInfo();

        //动态申请权限
        //verifyStoragePermissions(this);

        // 通过注解绑定控件
        ButterKnife.bind(this);
        initView();
        initData();
    }

    /** 获取页面布局文件资源id */
    protected int getLayoutResId() {
        return -1;
    }

    /** 初始化view */
    protected abstract void initView();

    /** 初始化数据 */
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showWaitDialog(String message) {
        if (mWaitDialog == null) {
            mWaitDialog = Util.getWaitDialog(this, message);
        }
        if (mWaitDialog != null) {
            mWaitDialog.setMessage(message);
            mWaitDialog.show();
        }
    }

    public void dismissWaitDialog() {
        if (mWaitDialog != null && mWaitDialog.isShowing()) {
            try {
                mWaitDialog.dismiss();
                mWaitDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            "android.[.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
