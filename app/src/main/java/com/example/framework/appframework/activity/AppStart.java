package com.example.framework.appframework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.example.framework.appframework.BaseApplication;
import com.example.framework.appframework.R;
import com.example.framework.appframework.util.AppManager;


/**
 * 公司：
 * 项目：
 * 简述：APP启动页面
 * 作者：Chenxp
 * 时间：2017/9/20
 * 版本：V1.0.0
 */
public class AppStart extends Activity {

	protected static final String TAG = AppStart.class.getSimpleName();

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
		BaseApplication baseApplication = BaseApplication.getApplication();
		boolean isFirstRun = baseApplication.getBoolean(BaseApplication.FIRSTRUN_PREFERENCES);
		if (isFirstRun) {
			baseApplication.putBoolean(BaseApplication.FIRSTRUN_PREFERENCES, false);
			//跳转到引导页面
			Intent intent = new Intent(AppStart.this, GuideActivity.class);
			startActivity(intent);
			finish();
		} else {
			final View view = View.inflate(this, R.layout.app_start, null);
			setContentView(view);

			// 渐变显示启动界面
			AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
			aa.setDuration(1500);
			view.startAnimation(aa);
			aa.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationEnd(Animation arg0) {
					//Intent intent = new Intent(AppStart.this, LoginActivity.class);
					Intent intent = new Intent(AppStart.this, MainActivity.class);
					startActivity(intent);
					finish();
				}

				@Override
				public void onAnimationRepeat(Animation animation) {
				}

				@Override
				public void onAnimationStart(Animation animation) {

				}
			});
		}
	}

	private void toLoginActivity(){
		/*Intent intent = new Intent(AppStart.this, LoginActivity.class);
		startActivity(intent);
		finish();*/
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		AppManager.getAppManager().AppExit();
	}

}
