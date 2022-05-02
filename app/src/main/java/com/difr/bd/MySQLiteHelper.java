package com.difr.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String COMENTS_TABLE_CREATE =
            "CREATE TABLE comments("+
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, comment TEXT,type TEXT)";
    private static final String DB_NAME = "comments.sqlite";
    public static final int DB_VERSION = 1;
    public MySQLiteHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(COMENTS_TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
