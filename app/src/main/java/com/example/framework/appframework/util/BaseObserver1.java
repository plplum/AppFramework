package com.example.framework.appframework.util;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.util.Log;
import android.widget.Toast;
import com.example.framework.appframework.R;
import com.example.framework.appframework.model.BaseEntity;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class BaseObserver1<T> implements Observer<BaseEntity<T>> {

    private final String TAG = BaseObserver1.class.getSimpleName();

    private final int RESPONSE_CODE_OK = 0;
    private final int RESPONSE_CODE_FAILED = 1;

    private boolean showProgress = true;

    private Context mContext;

    /**
     * 根据具体的业务逻辑去重写onSuccess方法
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * @param mContext
     * @param showProgress 是否显示进度框
     */
    public BaseObserver1(Context mContext, boolean showProgress) {
        this.showProgress = showProgress;
        this.mContext = mContext;
        if (showProgress) {
            String message = mContext.getString(R.string.action_doing);
            Util.showWaitDialog(mContext, message);
        }
    }

    @Override
    public final void onSubscribe(Disposable d) {
    }

    @Override
    public final void onNext(BaseEntity<T> response) {
        LogUtil.d(TAG, response.toString());
        Util.hideWaitDialog();
        if (response.getMsgCode() == RESPONSE_CODE_OK) {
            onSuccess(response.getData());
        } else {
            onFailure(response.getMsgCode(), response.getMessage());
        }
    }

    @Override
    public final void onError(Throwable t) {
        Util.hideWaitDialog();
        int code = 0;
        String errorMessage = "未知错误";
        if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            String meg = httpException.response().toString();
            code = httpException.code();
            errorMessage = httpException.getMessage();
        } else if (t instanceof SocketTimeoutException) {  //VPN open
            code = RESPONSE_CODE_FAILED;
            errorMessage = "服务器响应超时";
        } else if (t instanceof ConnectException) {
            code = RESPONSE_CODE_FAILED;
            errorMessage = "网络连接异常，请检查网络";
        } else if (t instanceof RuntimeException) {
            code = RESPONSE_CODE_FAILED;
            errorMessage = "运行时错误";
        } else if (t instanceof UnknownHostException) {
            code = RESPONSE_CODE_FAILED;
            errorMessage = "无法解析主机，请检查网络连接";
        } else if (t instanceof UnknownServiceException) {
            code = RESPONSE_CODE_FAILED;
            errorMessage = "未知的服务器错误";
        } else if (t instanceof IOException) {
            code = RESPONSE_CODE_FAILED;
            errorMessage = "没有网络，请检查网络连接";
        }

        /**
         * 严重的错误弹出dialog，一般的错误就只要Toast
         */
        /*if (RESPONSE_CODE_FAILED == code) {
            onFailure(RESPONSE_CODE_FAILED, errorMessage);
        } else {
            if (mContext != null && !((Activity) mContext).isFinishing()) {
                Toast.makeText(mContext, errorMessage + " - " + code, Toast.LENGTH_SHORT).show();
            }
        }*/

        Toast.makeText(mContext, errorMessage + " ,error code =  " + code, Toast.LENGTH_SHORT).show();
    }

    @Override
    public final void onComplete() {
        Util.hideWaitDialog();
    }

    /**
     * @param code
     * @param message
     */
    @CallSuper  //if overwrite,you should let it run.
    public void onFailure(int code, String message) {
        if (code == RESPONSE_CODE_FAILED && mContext != null) {
           // HttpUiTips.alertTip(mContext, message, code);
            Toast.makeText(mContext, message + " ,error code =  " + code, Toast.LENGTH_SHORT).show();
        } else {
            disposeErrorCode(message, code);
        }
    }

    /**
     * 对通用问题的统一拦截处理
     *
     * @param code
     */
    private final void disposeErrorCode(String message, int code) {
        switch (code) {
            case 101:
            case 112:
            case 123:
            case 401:
                break;
        }
        Toast.makeText(mContext, message + " # " + code, Toast.LENGTH_SHORT).show();
    }

}
