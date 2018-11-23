package com.example.framework.appframework.fragment;

import android.support.design.widget.TabLayout;
import android.view.View;

import com.example.framework.appframework.BaseApplication;
import com.example.framework.appframework.R;
import com.example.framework.appframework.model.DaoSession;
import com.example.framework.appframework.model.User;
import com.example.framework.appframework.model.UserDao;

import butterknife.OnClick;

/**
 * 公司：
 * 项目：
 * 简述：
 * 作者：Chenxp
 * 时间：2018/6/6
 * 版本：V1.0.0
 */
public class Fragment4 extends BaseFragment {

    DaoSession daoSession;

    private TabLayout mTabLayout;

    @Override
    protected int getFragmentRes() {
        return R.layout.fragment_4;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        //daoSession = GreenDaoManager.getInstance().getSession();
        daoSession = BaseApplication.getDaoInstance();
    }


    @OnClick({R.id.add, R.id.modify, R.id.search, R.id.delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                User user = new User();
                user.setName("test133");
                try {
                    daoSession.insert(user);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.modify:
                break;
            case R.id.search:
                break;
            case R.id.delete:
                UserDao userDao = daoSession.getUserDao();
                User findUser = userDao.queryBuilder().where(UserDao.Properties.Name.eq("test133")).build().unique();
                if(findUser != null){
                    userDao.deleteByKey(findUser.getId());
                }
                break;
        }
    }
}
