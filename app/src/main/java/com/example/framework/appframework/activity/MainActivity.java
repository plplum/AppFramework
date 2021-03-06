package com.example.framework.appframework.activity;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.framework.appframework.R;
import com.example.framework.appframework.fragment.Fragment1;
import com.example.framework.appframework.fragment.Fragment2;
import com.example.framework.appframework.fragment.Fragment3;
import com.example.framework.appframework.fragment.Fragment4;
import com.example.framework.appframework.util.AndroidBug54971Workaround;
import com.example.framework.appframework.util.BottomNavigationViewHelper;
import com.example.framework.appframework.util.Util;
import com.example.framework.appframework.widget.CustomShapTextView;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;

    FragmentManager mFragmentManager;

    Fragment1 mFragment1;
    Fragment2 mFragment2;
    Fragment3 mFragment3;
    Fragment4 mFragment4;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(mNavigation);

        boolean hasNavBar = Util.checkDeviceHasNavigationBar(this);
        if (hasNavBar){
            AndroidBug54971Workaround.assistActivity(findViewById(android.R.id.content));
        }

        mFragment1 = new Fragment1();
        mFragment2 = new Fragment2();
        mFragment3 = new Fragment3();
        mFragment4 = new Fragment4();

        mFragmentManager = getFragmentManager();
        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
        mTransaction.add(R.id.content, mFragment1);
        mTransaction.commit();

        //获取菜单项，添加消息提醒小红点
        //获取整个的NavigationView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) mNavigation.getChildAt(0);
        //这里就是获取所添加的每一个Tab(或者叫menu)，
        View tab = menuView.getChildAt(3);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;
        //加载我们的角标View，新创建的一个布局
        View badge = LayoutInflater.from(this).inflate(R.layout.menu_badge, menuView, false);
        //添加到Tab上
        itemView.addView(badge);
        TextView count = (TextView) badge.findViewById(R.id.tv_msg_count);
        count.setText(String.valueOf(10));
        //count.setVisibility(View.GONE);
        verifyStoragePermissions(this);
    }

    @Override
    protected void initData() {
        requestPermissions();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_menu1:
                    transaction.replace(R.id.content, mFragment1);
                    transaction.commitAllowingStateLoss();
                    return true;
                case R.id.navigation_menu2:
                    transaction.replace(R.id.content, mFragment2);
                    transaction.commitAllowingStateLoss();
                    return true;
                case R.id.navigation_menu3:
                    transaction.replace(R.id.content, mFragment3);
                    transaction.commitAllowingStateLoss();
                    return true;
                case R.id.navigation_menu4:
                    transaction.replace(R.id.content, mFragment4);
                    transaction.commitAllowingStateLoss();
                    return true;
            }

            return false;
        }
    };

    /**
     * 权限申请
     */
    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission
                .requestEach(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.SEND_SMS)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Logger.t(TAG).i(permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Logger.t(TAG).i(permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Logger.t(TAG).i(permission.name + " is denied.");
                        }
                    }
                });


    }
}
