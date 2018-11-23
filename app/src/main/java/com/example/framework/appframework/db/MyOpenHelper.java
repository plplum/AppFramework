package com.example.framework.appframework.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.framework.appframework.model.DaoMaster;
import com.example.framework.appframework.model.User;
import com.example.framework.appframework.model.UserDao;

import org.greenrobot.greendao.database.Database;

public class MyOpenHelper extends DaoMaster.OpenHelper {
    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
 
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //操作数据库的更新 有几个表升级都可以传入到下面
        MigrationHelper.getInstance().migrate(db, UserDao.class);
    }
}