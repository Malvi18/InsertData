package com.example.webclues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.webclues.UserHelper.EMAIL;

/**
 * Created by Dell on 27-03-2018.
 */

public class UserDao {
    public long insertData(Context context, User user){
        UserHelper userHelper=new UserHelper(context);
        SQLiteDatabase sqLiteOpenHelper=userHelper.getWritableDatabase();


        ContentValues values=new ContentValues();
        values.put(EMAIL,user.getEmail());
        long result= sqLiteOpenHelper.insert(UserHelper.TABLENAME,null,values);
        return result;

    }

    public List<User> selectData(Context context) {
        List<User> users = new ArrayList<User>();
        UserHelper userHelper = new UserHelper(context);
        SQLiteDatabase sqLiteDatabase = userHelper.getWritableDatabase();
        String s = "SELECT * FROM " + UserHelper.TABLENAME + "";
        Cursor cursor = sqLiteDatabase.rawQuery(s, null);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    User user = new User();
                    user.setId(cursor.getInt(0));
                    user.setEmail(cursor.getString(1));

                    users.add(user);
                } while (cursor.moveToNext());
                cursor.close();
            } else {
                cursor.close();
                return new ArrayList<User>();
            }
        }
        return users;
    }

    public int updateData(Context context, User user){
        UserHelper userHelper=new UserHelper(context);
        SQLiteDatabase sqLiteDatabase=userHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(UserHelper.EMAIL,user.getEmail());
        int result=sqLiteDatabase.update(UserHelper.TABLENAME,values,UserHelper.ID+"=?",
                new String[]{String.valueOf(user.getId())});
        return result;

    }

    public void DeleteData(Context context,int id){
       UserHelper userHelper=new UserHelper(context);
       SQLiteDatabase sqLiteDatabase=userHelper.getWritableDatabase();
       sqLiteDatabase.delete(UserHelper.TABLENAME,
               UserHelper.ID+"=?",new String[]{id+""});

    }

}
