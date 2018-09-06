package com.example.framework.appframework.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.framework.appframework.R;
import com.example.framework.appframework.util.Util;


/**
 * 公司：
 * 项目：
 * 简述：通用等待对话框
 * 作者：Chenxp
 * 时间：2017/9/25
 * 版本：V1.0.0
 */
public class WaitDialog extends Dialog {

	private TextView mMessageTxt;

	public WaitDialog(Context context) {
		super(context);
		init(context);
	}

	public WaitDialog(Context context, int defStyle) {
		super(context, defStyle);
		init(context);
	}

	protected WaitDialog(Context context, boolean cancelable, OnCancelListener listener) {
		super(context, cancelable, listener);
		init(context);
	}

	public static boolean dismiss(WaitDialog dialog) {
		if (dialog != null) {
			dialog.dismiss();
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		//this.dismiss();

	}

	public static boolean hide(WaitDialog dialog) {
		if (dialog != null) {
			dialog.hide();
			return false;
		} else {
			return true;
		}
	}

	private void init(Context context) {
		setCancelable(false);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		View view = LayoutInflater.from(context).inflate(R.layout.dialog_wait, null);
		mMessageTxt = (TextView) view.findViewById(R.id.waiting_tv);
		setContentView(view);
	}

	public static boolean show(WaitDialog waitdialog) {
		boolean flag;
		if (waitdialog != null) {
			waitdialog.show();
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		if (Util.isTablet()) {
			int i = (int) Util.dpToPixel(360F);
			if (i < Util.getScreenWidth()) {
				WindowManager.LayoutParams params = getWindow().getAttributes();
				params.width = i;
				getWindow().setAttributes(params);
			}
		}
	}

	public void setMessage(int message) {
		mMessageTxt.setText(message);
	}

	public void setMessage(String message) {
		mMessageTxt.setText(message);
	}
	
	public void hideMessage() {
	    mMessageTxt.setVisibility(View.GONE);
	}
}
