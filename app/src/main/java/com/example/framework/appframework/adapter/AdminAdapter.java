package com.example.framework.appframework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.framework.appframework.R;
import com.example.framework.appframework.model.Admin;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 公司：
 * 项目：
 * 简述：操作员列表适配器
 * 作者：Chenxp
 * 时间：
 * 版本：V1.0.0
 */
public class AdminAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Admin> mDatas;
    private LayoutInflater mInflater;

    public AdminAdapter(Context context, List<Admin> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public AdminViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdminViewHolder holder = new AdminViewHolder(mInflater.inflate(R.layout.listview_item_admin, parent, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final AdminViewHolder viewHolder = (AdminViewHolder) holder;
        Admin admin = mDatas.get(position);
        viewHolder.textAccount.setText(admin.getId());

        if (mOnItemClickLitener != null) {
            viewHolder.adminLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(v, position);
                }
            });

            viewHolder.adminLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickLitener.onItemLongClick(v, position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(int position, Admin admin) {
        mDatas.add(position, admin);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    class AdminViewHolder extends ViewHolder {

        @BindView(R.id.account)
        TextView textAccount;

        @BindView(R.id.layout_admin)
        LinearLayout adminLayout;

        public AdminViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            //textAccount = (TextView) view.findViewById(R.id.account);
            //adminLayout = (LinearLayout) view.findViewById(R.id.layout_admin);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}