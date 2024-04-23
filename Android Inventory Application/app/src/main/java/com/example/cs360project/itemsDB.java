package com.example.cs360project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class itemsDB extends SQLiteOpenHelper {
    public itemsDB(Context context){
        super(context, "Itemdata", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        //CREATE DATABASE TABLE
        sqLiteDatabase.execSQL("create table Itemdata(name TEXT primary key, quantity TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        sqLiteDatabase.execSQL("drop table if exists Itemdata");
    }

    //CREATE NEW ITEM
    public Boolean saveitemdata(String name, String quantity){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("quantity", quantity);
        long result = sqLiteDatabase.insert("Itemdata", null, contentValues);
        if (result==-1){
            return false;
        }
        else {
            return true;
        }
    }

    //UPDATE ITEM
    public Boolean updateitemdata(String name, String quantity){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("quantity", quantity);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Itemdata where name = ?", new String[] {name});
        if(cursor.getCount()>0){
            long result = sqLiteDatabase.update("Itemdata",  contentValues, "name=?", new String[] {name});
            return result != -1;
        }
        else{
            return false;
        }

    }
    //DELETE ITEM
    public Boolean deleteitemdata(String name){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Itemdata where name = ?", new String[] {name});
        if(cursor.getCount()>0){
            long result = sqLiteDatabase.delete("Itemdata", "name=?", new String[] {name});
            return result!=-1;
        }
        else{
            return false;
        }

    }
    //READ ITEM
    public Cursor getIdata(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Itemdata", null);
        return cursor;
    }
}
