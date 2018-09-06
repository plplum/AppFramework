package com.example.framework.appframework.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBManager extends SQLiteOpenHelper {

    public static final String TB_COMMONDICT = "commondict";

    public DBManager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // 创建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_COMMONDICT + "(" + "id integer primary key AUTOINCREMENT,"
                + "dictCode varchar," + "dictKey varchar," + "dictValue varchar," + "dictEnValue varchar," + "custName varchar,"
                + "orders integer," + "isSysParam integer," + "remarks varchar" + ")");


        Log.e("Database", "onCreate");
    }

    // 更新表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       /* db.execSQL("DROP TABLE IF EXISTS " + TB_COMMONDICT);
        */
        //onCreate(db);
        Log.e("onUpgrade == ", "oldVersion == " + oldVersion + "newVersion == " + newVersion);
    }

    // 更新列
    public void updateColumn(SQLiteDatabase db, String oldColumn, String newColumn, String typeColumn) {
        try {
            db.execSQL("ALTER TABLE " + TB_COMMONDICT + " CHANGE " + oldColumn + " " + newColumn + " " + typeColumn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}