package com.example.quanlidulich.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {

        super(context, "QLDL", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + "KHACHHANG" +
                " (" + "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "makh" + " TEXT, " +
                "tenkh" + " TEXT, " +
                "diachi" + " TEXT );";
        db.execSQL(sql);

        String sql2 = "CREATE TABLE " + "PHIEUDANGKY" +
                " (" + "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "sophieu" + " TEXT, " +
                "makh" + " TEXT, " +
                "matour" + " TEXT, " +
                "ngaydk" + " TEXT, " +
                "songuoi" + " INTEGER );";
        db.execSQL(sql2);

        String sql4 = "CREATE TABLE " + "DSTOUR" +
                " (" + "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "matour" + " TEXT, " +
                "lotrinh" + " TEXT, " +
                "hanhtrinh" + " TEXT, " +
                "giatour" + " INTEGER );";
        db.execSQL(sql4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "KHACHHANG");
        db.execSQL("DROP TABLE IF EXISTS " + "DSTOUR");
        db.execSQL("DROP TABLE IF EXISTS " + "PHIEUDANGKY");
        onCreate(db);
    }
}