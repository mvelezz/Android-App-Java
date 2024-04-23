package com.example.cs360project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLClientInfoException;


//DB FOR NEW USERS ITEMSDB CONTAINS CRUD
public class dbConnectLogin extends SQLiteOpenHelper {

    public dbConnectLogin(@Nullable Context context) {
        super(context, "Userdata", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Userdata(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Userdata");
    }

    public boolean saveuserdata(String username, String password){
        SQLiteDatabase sqLiteDatabasedb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long result = sqLiteDatabasedb.insert("Userdata", null, values);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }


    public Cursor getdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Userdata", null);
        return cursor;

    }

    //VERIFY USER IS IN DATABASE
    public boolean checkUser(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from Userdata where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }
}
