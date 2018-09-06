package com.example.framework.appframework.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.framework.appframework.R;
import com.example.framework.appframework.adapter.AdminAdapter;
import com.example.framework.appframework.model.Admin;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 公司：
 * 项目：
 * 简述：
 * 作者：Chenxp
 * 时间：2018/6/6
 * 版本：V1.0.0
 */
public class Fragment3 extends BaseFragment {

    @BindView(R.id.error_layout)
    LinearLayout mErrorLayout;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private AdminAdapter mAdapter;

    /** data list */
    private ArrayList<Admin> mList = new ArrayList<>();

    @Override
    protected int getFragmentRes() {
        return R.layout.fragment_3;
    }

    @Override
    protected void initView(View view) {

        mAdapter = new AdminAdapter(getActivity(), mList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(mAdapter);
        recyclerview.setItemAnimator(new DefaultItemAnimator());


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                queryAdmin();
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(1000, true);
                Admin admin = new Admin();
                admin.setId("0001");

                Admin admin2 = new Admin();
                admin2.setId("0002");

                mList.add(admin);
                mList.add(admin2);
                mAdapter.notifyDataSetChanged();
            }
        });

        mAdapter.setOnItemClickLitener(new AdminAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                System.out.println(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        mList.clear();

        Admin admin = new Admin();
        admin.setId("0001");

        Admin admin2 = new Admin();
        admin2.setId("0002");

        mList.add(admin);
        mList.add(admin2);
        mAdapter.notifyDataSetChanged();
        mErrorLayout.setVisibility(View.GONE);
    }


    private void queryAdmin(){
        Admin admin = new Admin();
        admin.setId("0003");
        Admin admin2 = new Admin();
        admin2.setId("0004");
        mList.add(admin);
        mList.add(admin2);

        if(refreshLayout!=null)
            refreshLayout.finishRefresh(500);

        mAdapter.notifyDataSetChanged();

    }
}
