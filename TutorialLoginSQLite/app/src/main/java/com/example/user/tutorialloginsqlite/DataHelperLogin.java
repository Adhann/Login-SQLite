package com.example.user.tutorialloginsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelperLogin extends SQLiteOpenHelper {

    private static final String DB_NAME = "login.db"; //untuk membuat nama database
    private static final int DB_VERSION = 1;

    SQLiteDatabase db = this.getWritableDatabase();

    public DataHelperLogin(Context context) {
        super(context, DB_NAME, null, DB_VERSION); // untuk memanggil constructor SQLite Super class SQLiteOpenHelper
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(username text primary key, password text)"); // membuat query dan mengesekusi perintah sql
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(String username, String password) { // membuat method insert
        ContentValues contentValues = new ContentValues(); // instansiasi object
        contentValues.put("username", username); // mengisikan value username sesuai yang diinputkan
        contentValues.put("password", password); // mengisikan value password sesuai yang diinputkan
        long insert = db.insert("user", null, contentValues);
        if (insert == -1) // jika eksekusi gagal maka hasilnya -1
            return false;
        else
            return true;
    }

    public Boolean isUsernameExist(String username) { // membuat method isUsernameExist
        //digunakan untuk mengambil tabel user where username
        Cursor cursor = db.rawQuery("Select * from user where username=?",new String[]{username});
        if (cursor.getCount() > 0) // jika record lebih dari 0 hasilnya false
            return false;
        else
            return true;
    }

    public Boolean checkLogin(String username, String password) { // membuat method validasi checkLogin
        // digunakan untuk mengambil tabel user where username dan password
        Cursor cursor = db.rawQuery("Select * from user where username=? and password=?", new String[]{username,password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}

