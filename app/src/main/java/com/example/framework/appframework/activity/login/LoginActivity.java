package com.example.framework.appframework.activity.login;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.example.framework.appframework.R;
import com.example.framework.appframework.activity.BaseActivity;
import com.example.framework.appframework.contract.LoginContract;
import com.example.framework.appframework.model.UserInfo;
import com.example.framework.appframework.presenter.LoginPresenter;
import com.example.framework.appframework.util.ACache;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 公司：
 * 项目：
 * 简述：
 * 作者：Chenxp
 * 时间：2018/6/7
 * 版本：V1.0.0
 */
public class LoginActivity extends BaseActivity implements LoginContract.View{


    @BindView(R.id.id_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;

    private ACache aCache;

    private LoginContract.Presenter mLoginPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void initData() {
        new LoginPresenter(this, this);
        //缓存实例
        /*initAcache();
        String str = aCache.getAsString("newText11");
        aCache.put("newText11", "77777777", 10);*/
    }

    private void initAcache() {
        aCache = ACache.get(this);// 默认选择的路径是new File(context.getCacheDir(),// "ACache")
        // String path = getExternalCacheDir().getAbsolutePath();
        // aCache = ACache.get(new File(path));//设置存储路径用于手动清空缓存使用，
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        String name = etUsername.getText().toString();
        String pass = etPassword.getText().toString();
        System.out.print(mLoginPresenter);
        //mLoginPresenter.login("loginStr");

        /*String cacheData = aCache.getAsString("newText");// 从缓存中取数据
        if (cacheData != null) {
            System.out.println(cacheData);
        } else {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername("plplum@163.com");
            userInfo.setPassword("111111");
            userInfo.setRememberMe("false");
            //String json = JsonUtil.getJsonParam(userInfo);
            mLoginPresenter.login1(userInfo);
        }*/

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("plplum@163.com");
        userInfo.setPassword("111111");
        userInfo.setRememberMe("false");
        //String json = JsonUtil.getJsonParam(userInfo);
        mLoginPresenter.login1(userInfo);
    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mLoginPresenter = presenter;
    }

    @Override
    public void showErrorMsg(@NonNull String type, @NonNull String msg) {

    }

    @Override
    public void showSuccessMsg(@NonNull String msg) {
        System.out.println(msg);
    }

    @Override
    public void showUserInfo(String data) {
        System.out.println(data);
        //aCache.put("newText", data, 10);//间数据放到缓存中，保存时间是2秒


        etPassword.setText(data);
    }
}
