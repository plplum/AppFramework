package com.example.framework.appframework;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framework.appframework.db.DBHelper;
import com.example.framework.appframework.model.LoginInfo;
import com.example.framework.appframework.util.CrashHandler;
import com.example.framework.appframework.util.LogUtil;
import com.example.framework.appframework.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.framework.appframework.util.Util.USER_OBJ;

/**
 * 公司：
 * 项目：
 * 简述：Application类
 * 作者：Chenxp
 * 时间：2017/9/21
 * 版本：V1.0.0
 */
public class BaseApplication extends Application {

    protected static final String TAG = BaseApplication.class.getSimpleName();

    private static BaseApplication mInstance;
    private static Context mContext;
    private static String mLastToast = "";
    private static long mLastToastTime;

    private SharedPreferences mPreferences;

    private String mAppPreferences = "APP_PERS";

    public static List<String> mAccountList = new ArrayList<>();

    public static String USERNAME_PREFERENCES = "APP_PERS_USERNAME";
    public static String USERPASS_PREFERENCES = "APP_PERS_USERPASS";
    public static String FIRSTRUN_PREFERENCES = "APP_PERS_FIRSTRUN";
    public static String TOKEN = "token";

    public static final String APP_LANGUAGE = "app_language";

    public static final int LANGUAGE_DEFAULT = 0;
    public static final int LANGUAGE_SCH = 1;
    public static final int LANGUAGE_ENG = 2;
    public static final int LANGUAGE_AUTO = 3;

    /**
     * 是否已登录
     */
    public boolean mIsLogin = false;

    /**
     * Notification管理
     */
    public NotificationManager mNotificationManager;

    /**
     * 是否需要启用通知
     */
    public boolean mIsNeedNotifi = true;

    /**
     * Notification的ID
     */
    public int notifyId = 100;

    private LoginInfo mLoginInfo;

    private DBHelper mDBHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        //MultiDex.install(this);
        mInstance = this;
        mContext = this.getApplicationContext();
        mPreferences = mContext.getSharedPreferences(mAppPreferences, Context.MODE_PRIVATE);
        mDBHelper = new DBHelper(mContext);
        // 初始化网络请求
        /*AsyncHttpClient client = new AsyncHttpClient();
        HttpClient.setHttpClient(client);
*/
        //if (!BuildConfig.DEBUG) {
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(this);
        //}
        //ToolImageLoader.initImageLoader(mContext);
        //LocalImageHelper.init(this);

        LogUtil.i(TAG, "init application ： OnCreate()");

    }

    public static BaseApplication getApplication() {
        return mInstance;
    }

    public static synchronized BaseApplication getAppContext() {
        return (BaseApplication) mContext;
    }

    public SharedPreferences getSharedPreferences() {
        return mPreferences;
    }

    public void putString(String key, String value) {
        mPreferences.edit().putString(key, value).commit();
    }

    public String getString(String key) {
        return mPreferences.getString(key, "");
    }

    public void putBoolean(String key, boolean value) {
        mPreferences.edit().putBoolean(key, value).commit();
    }

    public int getInt(String key) {
        return mPreferences.getInt(key, 0);
    }

    public void putInt(String key, int value) {
        mPreferences.edit().putInt(key, value).commit();
    }

    public boolean getBoolean(String key) {
        return mPreferences.getBoolean(key, true);
    }

    /*public void saveUserInfo(LoginInfo user) throws Exception {
        putString(USERNAME_PREFERENCES, user.getUserName());
        putString(USERPASS_PREFERENCES, AESCrypt.encrypt(Constants.KEY, user.getPassword()));
    }
*/
    public static void showShortToast(int messageResId) {
        showToast(messageResId, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM);
    }

    public static void showLongToast(int messageResId) {
        showToast(messageResId, Toast.LENGTH_LONG, 0, Gravity.BOTTOM);
    }

    public static void showToast(int messageResId, int duration, int icon, int gravity) {
        String message = mContext.getString(messageResId);
        if (message != null && !message.equalsIgnoreCase("")) {
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(mLastToast) || Math.abs(time - mLastToastTime) > 2000) {
                View view = LayoutInflater.from(getAppContext()).inflate(R.layout.view_toast, null);
                ((TextView) view.findViewById(R.id.title_tv)).setText(message);
                if (icon != 0) {
                    ((ImageView) view.findViewById(R.id.icon_iv)).setImageResource(icon);
                    view.findViewById(R.id.icon_iv).setVisibility(View.VISIBLE);
                }
                Toast toast = new Toast(getAppContext());
                toast.setView(view);
                if (gravity == Gravity.CENTER) {
                    toast.setGravity(gravity, 0, 0);
                } else {
                    toast.setGravity(gravity, 0, 180);
                }
                toast.setDuration(duration);
                toast.show();
                mLastToast = message;
                mLastToastTime = System.currentTimeMillis();
            }
        }
    }

    public static void setAppLanguage(Context context, int languageId) {
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Locale locale = Locale.ENGLISH;
        if (languageId == LANGUAGE_DEFAULT || languageId == LANGUAGE_AUTO) {
            config.locale = Locale.getDefault();
            resources.updateConfiguration(config, dm);
        } else {
            switch (languageId) {
                case LANGUAGE_ENG:
                    config.locale = Locale.ENGLISH;
                    resources.updateConfiguration(config, dm);
                    break;
                case LANGUAGE_SCH:
                    config.locale = Locale.SIMPLIFIED_CHINESE;
                    resources.updateConfiguration(config, dm);
                    break;
                default:
                    config.locale = Locale.SIMPLIFIED_CHINESE;
                    resources.updateConfiguration(config, dm);
                    break;
            }
        }
//		Locale.setDefault(locale);
//		Configuration configuration = new Configuration();
//		configuration.locale = locale;
//		context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
//		mContext.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
    }

    public boolean isNeedNotifi() {
        return mIsNeedNotifi;
    }

    public void setIsNeedNotifi(boolean mIsNeedNotifi) {
        this.mIsNeedNotifi = mIsNeedNotifi;
    }

    /** 显示常驻通知栏 */
    public void showCzNotify(PendingIntent pendingIntent, String title, String message) {
        if (!isNeedNotifi()) return;
        notifyId++;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher).setContentTitle(title).setAutoCancel(true).setContentText(message).setContentIntent(pendingIntent);
        Notification mNotification = mBuilder.build();
        mNotification.flags |= Notification.FLAG_AUTO_CANCEL;
        mNotification.defaults = Notification.DEFAULT_SOUND;
        mNotification.when = System.currentTimeMillis();
        mNotificationManager.notify(notifyId, mNotification);
    }

    public void showNotification(int EventNotification, PendingIntent pendingIntent, String title, String content) {
        if (!isNeedNotifi()) return;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notifyId++;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setSmallIcon(R.mipmap.ic_launcher).setContentTitle(title).setAutoCancel(true).setContentText(content).setContentIntent(pendingIntent);
        Notification mNotification = mBuilder.build();
        mNotification.flags |= Notification.FLAG_AUTO_CANCEL;
        mNotification.when = System.currentTimeMillis();
        if (EventNotification == 0) {
            mNotification.defaults |= Notification.DEFAULT_SOUND;
        } else if (EventNotification == 1) {
            mNotification.defaults |= Notification.DEFAULT_VIBRATE;
        } else if (EventNotification == 2) {
            mNotification.defaults |= Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND;
        } else if (EventNotification == -1) {
            mNotification.defaults |= Notification.DEFAULT_SOUND;
        }
        notificationManager.notify(notifyId, mNotification);
    }

    public void removeAllNotify(){
        //if (mNotificationManager!=null){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        //}
    }

    public LoginInfo getLoginInfo() {
        if (mLoginInfo==null){
            mLoginInfo = (LoginInfo) Util.getObject(USER_OBJ, getSharedPreferences());
        }
        return mLoginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.mLoginInfo = loginInfo;
        if (this.mLoginInfo!=null){
            mIsLogin = true;
        }else {
            mIsLogin = false;
        }
    }

    public boolean isLogin() {
        return mIsLogin;
    }

    private boolean isNeedPushMsg(String sNum){
        int countNumber = 0;
        try{
            countNumber = Integer.parseInt(sNum);
        }catch (Exception e){
            countNumber = 0;
        }
        if (countNumber==0){
            return false;
        }
        return true;
    }

    public DBHelper getDBHelper() {
        if (mDBHelper == null) {
            mDBHelper = new DBHelper(mContext);
        }
        return mDBHelper;
    }

}