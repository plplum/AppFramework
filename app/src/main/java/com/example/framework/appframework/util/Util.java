package com.example.framework.appframework.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.framework.appframework.BaseApplication;
import com.example.framework.appframework.R;
import com.example.framework.appframework.dialog.WaitDialog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

public class Util {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";
    public static final String USER_OBJ = "user_obj";

    public static NotificationCompat.Builder mBuilder;
    /**
     * Notification管理
     */
    public static NotificationManager mNotificationManager;

    public static void openActivity(Context context, Class<?> activityClass, Bundle bundle) {
        Intent intent = new Intent(context, activityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void openActivityNoHistory(Context context, Class<?> activityClass, Bundle bundle) {
        Intent intent = new Intent(context, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void openUri(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }

    // 是否使用平板电脑
    private static Boolean m_IsTablet = null;

    public static boolean checkDeviceHasNavigationBar(Context activity) {
        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
        boolean hasMenuKey = ViewConfiguration.get(activity).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        if (!hasMenuKey && !hasBackKey) {
            // 做任何你需要做的,这个设备有一个导航栏
            return true;
        }
        return false;
    }

    public static boolean hasInternet() {
        boolean flag;
        if (((ConnectivityManager) BaseApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }


    public static boolean isTablet() {
        if (m_IsTablet == null) {
            boolean flag;
            if ((0xf & BaseApplication.getAppContext().getResources().getConfiguration().screenLayout) >= 3)
                flag = true;
            else
                flag = false;
            m_IsTablet = Boolean.valueOf(flag);
        }
        return m_IsTablet.booleanValue();
    }

    public static float dpToPixel(float dp) {
        return dp * (getDisplayMetrics().densityDpi / 160F);
    }

    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) BaseApplication.getAppContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(displaymetrics);
        return displaymetrics;
    }

    public static float getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    public static float getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

    /**
     * 判断是否是空字符串
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static Bitmap getBitmapFromByte(byte[] temp){
        if(temp != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            return bitmap;
        }else{
            return null;
        }
    }

    // 判断格式是否为email
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public static void backgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    public static void setPasswordVisible(Context context, EditText etPassword, ImageView ivShowPass) {
        if (etPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            //ivShowPass.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.denglu_icon_bi));
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            //ivShowPass.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.denglu_icon_kai));
            etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
    }

    public static void putObject(String key, Object obj, SharedPreferences preferences) {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            String serStr = bos.toString("ISO-8859-1");
            serStr = URLEncoder.encode(serStr, "UTF-8");
            preferences.edit().putString(key, serStr).commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Object getObject(String key, SharedPreferences preferences) {
        String serStr = preferences.getString(key, "");
        ByteArrayInputStream bai = null;
        ObjectInputStream ois = null;
        Object object = null;
        if (serStr != "") {
            try {
                String sedStr = URLDecoder.decode(serStr, "UTF-8");
                bai = new ByteArrayInputStream(sedStr.getBytes("ISO-8859-1"));
                ois = new ObjectInputStream(bai);
                object = ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    ois.close();
                    bai.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }

    public static boolean isServiceRunning(Context context, String className) {
        ActivityManager myManager = (ActivityManager) context.getApplicationContext().getSystemService(
                        Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager
                .getRunningServices(30);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString().equals(className)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPass(String pass){
        int l = pass.length();
        if (l<6||l>16){
            return false;
        }
        //判断密码是否包含数字：包含返回1，不包含返回0
        int i = pass.matches(".*\\d+.*") ? 1 : 0;
        //判断密码是否包含字母：包含返回1，不包含返回0
        int j = pass.matches(".*[a-zA-Z]+.*") ? 1 : 0;

        if (i==1&&j==1){
            return true;
        }else {
            return false;
        }
        //判断密码是否包含特殊符号(~!@#$%^&*()_+|<>,.?/:;'[]{}\)：包含返回1，不包含返回0
        //int k = pass.matches(".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*") ? 1 : 0;
    }

    public static boolean isSpecialchar(String str){
        return  str.matches(".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*") ? true : false;
    }


    public static WaitDialog getWaitDialog(Activity activity, String message) {
        WaitDialog dialog = null;
        try {
            dialog = new WaitDialog(activity, R.style.wait_dialog_bg);
            dialog.setMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dialog;
    }

    private static WaitDialog dialog = null;
    public static void showWaitDialog(Context mContext, String message) {
        try {
            dialog = new WaitDialog(mContext, R.style.wait_dialog_bg);
            dialog.setMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        dialog.show();
    }

    public static void hideWaitDialog() {
        if (dialog!=null)
            dialog.hide();
    }
}


