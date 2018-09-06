package com.example.framework.appframework.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;


public class DBHelper {
    // 数据库名称
    private static String DB_NAME = "dasdaodb.db";
    // 数据库版本
    private static int DB_VERSION = 1;
    private SQLiteDatabase db;
    private DBManager dbHelper;

    public DBHelper(Context context) {
        dbHelper = new DBManager(context, DB_NAME, null, DB_VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        db.close();
        dbHelper.close();
    }

    /**
     * 保存通用数据到数据库
     * @param commonDictBase
     * @return
     *//*
    public Long saveCommonDict(CommonDictBase commonDictBase) {
        ContentValues values = new ContentValues();
        values.put("dictCode", commonDictBase.getDictCode());
        values.put("dictKey", commonDictBase.getDictKey());
        values.put("dictValue", commonDictBase.getDictValue());
        values.put("dictEnValue", commonDictBase.getDictEnValue());
        values.put("custName", commonDictBase.getCustName());
        values.put("orders", commonDictBase.getOrders());
        values.put("isSysParam", commonDictBase.getIsSysParam());
        values.put("remarks", commonDictBase.getRemarks());
        Long uid = db.insert(DBManager.TB_COMMONDICT, null, values);
        LogUtil.i("DBHelp", "insert commonDictBase data to db." );
        return uid;
    }

    *//**
     * 查询通用数据
     *
     * @return list
     *//*
    public List<CommonDictBase> getCommonDictList(String code) {
        List<CommonDictBase> commonDictBasesList = new ArrayList<CommonDictBase>();
        Cursor cursor = db.query(DBManager.TB_COMMONDICT, null, "dictCode =  ?", new String[]{ code }, null, null, "orders asc");
        cursor.moveToFirst();
        while (!cursor.isAfterLast() && (cursor.getString(1) != null)) {
            CommonDictBase commonDictBase = new CommonDictBase();
            commonDictBase.setId(cursor.getInt(0));
            commonDictBase.setDictCode(cursor.getString(1));
            commonDictBase.setDictKey(cursor.getString(2));
            commonDictBase.setDictValue(cursor.getString(3));
            commonDictBase.setDictEnValue(cursor.getString(4));
            commonDictBase.setCustName(cursor.getString(5));
            commonDictBase.setOrders(cursor.getInt(6));
            commonDictBase.setIsSysParam(cursor.getInt(7));
            commonDictBase.setRemarks(cursor.getString(8));
            commonDictBasesList.add(commonDictBase);
            cursor.moveToNext();
        }
        cursor.close();
        return commonDictBasesList;
    }

    public int delCommonDict(String code) {
        int id = db.delete(DBManager.TB_COMMONDICT, "dictCode =?", new String[]{ code });
        return id;
    }*/

}
