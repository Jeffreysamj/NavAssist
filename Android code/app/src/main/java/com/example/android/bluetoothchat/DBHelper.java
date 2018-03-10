package com.example.android.bluetoothchat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Khanita Taskeen on 23-01-2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Numbers", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table num (contact text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table num");
        onCreate(db);
    }
    public boolean addData(String contact) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("contact",contact);
        long l=db.insert("num",null,cv);
        if(l==-1)
            return false;
        else
            return true;
    }
    public Cursor fetchData() {
        Cursor curData=null;
        SQLiteDatabase db=getWritableDatabase();
        curData=db.rawQuery("select * from num",null);
        return curData;
    }
}
