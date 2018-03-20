package com.dokgo.junkiproj.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by JawsGeun on 2018-03-20.
 */

public class InnerDB extends SQLiteOpenHelper {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    public InnerDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE CAL (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, memo TEXT, date TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {    }
    public void insert(String id,String name, String address, String memo){
        SQLiteDatabase db = getWritableDatabase();
        Date today = new Date();
        String date = simpleDateFormat.format(today);
        db.execSQL("INSERT INTO CAL VALUES('" + id + "','" + name + "', '" + address + "', '" + memo + "', '"+ date+"');");
        Log.e("삽입","INSERT INTO CAL VALUES(null, '" + name + "', '" + address + "', '" + memo + "', '"+ date+"');");
        db.close();
    }
    public ArrayList<ArrayList<String>> getResult(Date mdate){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> tmp;
        String date = simpleDateFormat.format(mdate);
        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM CAL WHERE date ="+date, null);
        while (cursor.moveToNext()) {
            tmp = new ArrayList<>();
            tmp.add(cursor.getString(0)); // id
            tmp.add(cursor.getString(1)); // 이름
            tmp.add(cursor.getString(2)); // 주소
            tmp.add(cursor.getString(3)); // 메모
            tmp.add(cursor.getString(4)); // 날짜
            result.add(tmp);
        }
        return result;
    }
}
