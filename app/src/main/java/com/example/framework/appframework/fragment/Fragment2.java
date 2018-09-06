package com.example.framework.appframework.fragment;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.appframework.R;
import com.example.framework.appframework.util.DownloadUtil;
import com.example.framework.appframework.util.GlideApp;
import com.example.framework.appframework.widget.HackyViewPager;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import butterknife.BindView;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * 公司：
 * 项目：
 * 简述：
 * 作者：Chenxp
 * 时间：2018/6/6
 * 版本：V1.0.0
 */
public class Fragment2 extends BaseFragment {

    @BindView(R.id.id_toolbar)
    Toolbar mToolbar;

    //@BindView(R.id.image)
    //PhotoView mPhotoView;

    @BindView(R.id.view_pager)
    ViewPager mHackyViewPager;

    @BindView(R.id.textview)
    TextView mTextView;

    private List<String> mList = new ArrayList<String>();

    private SamplePagerAdapter1 mAdapter;

    @Override
    protected int getFragmentRes() {
        return R.layout.fragment_2;
    }

    @Override
    protected void initView(View view) {
        //mHackyViewPager.setAdapter(new SamplePagerAdapter());
        mAdapter = new SamplePagerAdapter1(this.getActivity(), mList);
        mHackyViewPager.setAdapter(mAdapter);
        mHackyViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTextView.setText(position+1 + "/" + mList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    protected void initData() {
        mList.clear();
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530251002&di=c4a8a7c5806d3f43728d8c328924503b&imgtype=jpg&er=1&src=http%3A%2F%2Fh.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F58ee3d6d55fbb2fb2c24ab314e4a20a44623dc82.jpg");
        mList.add("http://img4.imgtn.bdimg.com/it/u=1954921021,3801498013&fm=15&gp=0.jpg");
        mAdapter.notifyDataSetChanged();
        if (mList.size()>0){
            mTextView.setText(1 + "/" + mList.size());
        }
        //mPhotoView.setImageResource(R.drawable.guide_a);
        //Glide.with(this.getActivity()).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530251002&di=c4a8a7c5806d3f43728d8c328924503b&imgtype=jpg&er=1&src=http%3A%2F%2Fh.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F58ee3d6d55fbb2fb2c24ab314e4a20a44623dc82.jpg").into(mPhotoView);
    }


    static class SamplePagerAdapter extends PagerAdapter {

        private static final int[] sDrawables = { R.drawable.guide_a, R.drawable.guide_b, R.drawable.guide_c, R.drawable.guide_d};

        @Override
        public int getCount() {
            return sDrawables.length;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            photoView.setImageResource(sDrawables[position]);
            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

    static class SamplePagerAdapter1 extends PagerAdapter {
        private Context mContext;
        private List<String> mList;

        public SamplePagerAdapter1(Context context, List<String> list){
            mList = list;
            mContext = context;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            final String url = mList.get(position);
            PhotoView photoView = new PhotoView(container.getContext());
            GlideApp.with(mContext).load(mList.get(position))
                    .into(photoView)
                    .onLoadStarted(mContext.getResources().getDrawable(R.drawable.eye_login));
            /*Glide.with(mContext).load(mList.get(position))
                    .into(photoView)
                    .onLoadStarted(mContext.getResources().getDrawable(R.drawable.eye_login));*/
            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


            //点击图片保存到本地相册
            photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            DownloadUtil.saveImage(url, mContext);
                        }
                    }.start();
                }
            });
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}


