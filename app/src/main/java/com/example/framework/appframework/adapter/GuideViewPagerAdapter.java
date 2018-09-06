package com.example.framework.appframework.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.framework.appframework.R;
import com.example.framework.appframework.activity.MainActivity;
import java.util.List;

/**
 * 公司：大商道商品交易市场股份有限公司
 * 项目：大商道离岸交易平台
 * 简述：引导页面适配器
 * 作者：Chenxp
 * 时间：2017/9/21
 * 版本：V1.0.0
 */
public class GuideViewPagerAdapter extends PagerAdapter {

	private List<View> mViewList;
	
	private Activity mActivity;

	public GuideViewPagerAdapter(List<View> views, Activity activity) {
		this.mActivity = activity;
		this.mViewList = views;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mViewList.get(position));
	}

	@Override
	public int getCount() {
		if (mViewList != null) {
			return mViewList.size();
		}
		return 0;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = mViewList.get(position);
		container.addView(view);
		if (position == mViewList.size() - 1) {
			Button imageButton = (Button) view.findViewById(R.id.start_btn);
			imageButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					goHome();

				}
			});
		}
		return view;
	}

	private void goHome() {
		/*Intent intent = new Intent(mActivity, LoginActivity.class);
		mActivity.startActivity(intent);
		mActivity.finish();*/
		Intent intent = new Intent(mActivity, MainActivity.class);
		mActivity.startActivity(intent);
		mActivity.finish();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return (arg0 == arg1);
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
	}

	@Override
	public Parcelable saveState() {
		return null;
	}


}
