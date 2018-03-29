package com.example.webclues;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dell on 27-03-2018.
 */

public class UserHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "mydb";
    public static final String TABLENAME = "user";
    public static final int version = 1;
    public static final String ID = "ID";
    public static final String EMAIL = "email";

    public UserHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create = "CREATE TABLE " + TABLENAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + EMAIL + " TEXT)";
        db.execSQL(Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        onCreate(db);
    }


}
