package com.example.framework.appframework.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 崩溃日志收集
 */
public class CrashHandler implements UncaughtExceptionHandler {

	public static final String TAG = "CrashHandler";
	// 系统默认的UncaughtException处理类
	private UncaughtExceptionHandler mDefaultHandler;
	// CrashHandler实例
	private static CrashHandler INSTANCE = new CrashHandler();
	// 程序的Context对象
	private Context mContext;
	// 用来存储设备信息和异常信息
	private Map<String, String> infos = new HashMap<String, String>();

	// 用于格式化日期,作为日志文件名的一部分
	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private String nameString;

	private String localFileUrl;

	/** 保证只有一个CrashHandler实例 */
	private CrashHandler() {
	}

	/** 获取CrashHandler实例 ,单例模式 */
	public static CrashHandler getInstance() {
		return INSTANCE;
	}

	/**
	 * 初始化
	 * 
	 * @param context
	 */
	public void init(Context context) {
		mContext = context;
		// 获取系统默认的UncaughtException处理器
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		// 设置该CrashHandler为程序的默认处理器
		Thread.setDefaultUncaughtExceptionHandler(this);
		// nameString =
		// BmobUserManager.getInstance(mContext).getCurrentUserName();
	}

	/**
	 * 当UncaughtException发生时会转入该函数来处理
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {
			// 如果用户没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Log.e(TAG, e.getMessage());
			}
			// 退出程序
			//android.os.Process.killProcess(android.os.Process.myPid());
			//System.exit(1);
			AppManager.getAppManager().AppExit();
		}
	}

	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
	 * 
	 * @param ex
	 * @return true:如果处理了该异常信息;否则返回false.
	 */
	private boolean handleException(Throwable ex) {
		if (ex == null) {
			return false;
		}
		// WonderMapApplication.getInstance().getSpUtil().setCrashLog(true);//
		// 每次进入应用检查，是否有log，有则上传
		// 使用Toast来显示异常信息
		/*new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				Toast.makeText(mContext, mContext.getString(R.string.app_error), Toast.LENGTH_LONG).show();
				Looper.loop();
			}
		}.start();*/
		// 收集设备参数信息
		collectDeviceInfo(mContext);
		// 保存日志文件
		writeCrashInfoToFile(ex);
		return true;
	}

	/**
	 *
	 * @param ctx
	 * 手机设备相关信息
	 */
	public void collectDeviceInfo(Context ctx) {
		try {
			PackageManager pm = ctx.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
			if (pi != null) {
				String versionName = pi.versionName == null ? "null" : pi.versionName;
				String versionCode = pi.versionCode + "";
				infos.put("versionName", versionName);
				infos.put("versionCode", versionCode);
				infos.put("crashTime", formatter.format(new Date()));
			}
		} catch (NameNotFoundException e) {
			Log.e(TAG, "an error occured when collect package info", e);
		}
		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				infos.put(field.getName(), field.get(null).toString());
				Log.d(TAG, field.getName() + " : " + field.get(null));
			} catch (Exception e) {
				Log.e(TAG, "an error occured when collect crash info", e);
			}
		}
	}

	/**
	 *
	 * @param ex
	 *            将崩溃写入文件系统
	 */
	private void writeCrashInfoToFile(Throwable ex) {
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : infos.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key + "=" + value + "\n");
		}
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		String result = writer.toString();
		sb.append(result);
		// 这里把刚才异常堆栈信息写入SD卡的Log日志里面
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			String sdcardPath = Environment.getExternalStorageDirectory().getPath();
			String filePath = sdcardPath + "/appname/crash/";
			localFileUrl = writeLog(sb.toString(), filePath);
		}
	}

	/**
	 *
	 * @param log
	 * @param name
	 * @return 返回写入的文件路径 写入Log信息的方法，写入到SD卡里面
	 */
	private String writeLog(String log, String name) {
		CharSequence timestamp = new Date().toString().replace(" ", "");
		timestamp = "crash";
		String filename = name + timestamp + ".log";
		File file = new File(filename);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try {
			Log.d("TAG", "写入到SD卡里面");
			// FileOutputStream stream = new FileOutputStream(new
			// File(filename));
			// OutputStreamWriter output = new OutputStreamWriter(stream);
			file.createNewFile();
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			// 写入相关Log到文件
			bw.write(log);
			bw.newLine();
			bw.close();
			fw.close();
			return filename;
		} catch (IOException e) {
			Log.e(TAG, "an error occured while writing file...", e);
			e.printStackTrace();
			return null;
		}
	}
	
/*	private void restart() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Log.e(TAG, "error : ", e);
		}
		Intent intent = new Intent(context.getApplicationContext(), SendCrashActivity.class);
		PendingIntent restartIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, intent,
				Intent.FLAG_ACTIVITY_NEW_TASK);
		// 退出程序
		AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent); // 1秒钟后重启应用
	}*/
}