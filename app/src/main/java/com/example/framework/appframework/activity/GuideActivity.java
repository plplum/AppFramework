package com.example.framework.appframework.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.framework.appframework.R;
import com.example.framework.appframework.adapter.GuideViewPagerAdapter;


/**
 * 公司：
 * 项目：
 * 简述：
 * 作者：Chenxp
 * 时间：2017/9/20
 * 版本：V1.0.0
 */
public class GuideActivity extends Activity implements OnPageChangeListener {

	private ViewPager mViewPager;
	private GuideViewPagerAdapter mViewPagerAdapter;
	private List<View> mViewList;

	/** 底部小点图片 */
	private ImageView[] mDotsImage;

	/** 当前选中位置 */
	private int mCurrentIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		initViews();
		//initDots();
	}

	@SuppressLint("InflateParams")
	private void initViews() {
		LayoutInflater inflater = LayoutInflater.from(this);
		mViewList = new ArrayList<View>();
		mViewList.add(inflater.inflate(R.layout.what_new_one, null));
		mViewList.add(inflater.inflate(R.layout.what_new_two, null));
		mViewList.add(inflater.inflate(R.layout.what_new_three, null));
		mViewList.add(inflater.inflate(R.layout.what_new_four, null));
		mViewPagerAdapter = new GuideViewPagerAdapter(mViewList, this);
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mViewPager.setAdapter(mViewPagerAdapter);
		mViewPager.addOnPageChangeListener(this);
	}

	private void initDots() {
		LinearLayout dotLayout = (LinearLayout) findViewById(R.id.dot_layout);
		mDotsImage = new ImageView[mViewList.size()];
		// 初始化小图标
		for (int i = 0; i < mViewList.size(); i++) {
			mDotsImage[i] = (ImageView) dotLayout.getChildAt(i);
			mDotsImage[i].setEnabled(true);
		}
		mCurrentIndex = 0;
		mDotsImage[mCurrentIndex].setEnabled(false);
	}

	private void setCurrentDot(int position) {
		if (position < 0 || position > mViewList.size() - 1 || mCurrentIndex == position) {
			return;
		}
		mDotsImage[position].setEnabled(false);
		mDotsImage[mCurrentIndex].setEnabled(true);
		mCurrentIndex = position;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		//setCurrentDot(arg0);
	}

}
