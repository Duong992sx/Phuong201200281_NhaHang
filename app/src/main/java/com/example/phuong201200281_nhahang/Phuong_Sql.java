package com.example.phuong201200281_nhahang;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Phuong_Sql extends SQLiteOpenHelper {
    //tên bảng
     public static  final String TableName = "NhaHang_Phuong";

    public  static final String Id = "Id";
    public  static final String Ten = "Ten";
    public  static final String Diachi = "Diachi";
        public  static final String Sophieu = "Sophieu";
    public  static final String Diem = "Diem";
    public Phuong_Sql(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlquery = "Create table if not exists " + TableName + "(" + Id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Ten + " Text, "
                + Diachi + " Text, "
                + Sophieu + " Text,"
                + Diem + " Text)";
        db.execSQL(sqlquery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TableName);
        onCreate(db);
    }
    public ArrayList<NhaHang> GetAllContact(){
        ArrayList<NhaHang> list = new ArrayList<>();
        String sql = "Select * from " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor != null){
            while(cursor.moveToNext()){
                NhaHang contact = new NhaHang(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getFloat(4));
                list.add(contact);
            }
        }
        return list;
    }
    public void addContact(NhaHang contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Ten,contact.getTennhahang());
        values.put(Diachi,contact.getDiachi());
        values.put(Sophieu,contact.getSophieu());
        values.put(Diem,contact.getdiemtrungbinh());
        db.insert(TableName,null,values);
        db.close();
    }
    public void UpdateContact(int id, NhaHang contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
         values.put(Id,contact.getId());
        values.put(Ten,contact.getTennhahang());
        values.put(Diachi,contact.getDiachi());
        values.put(Sophieu,contact.getSophieu());
        values.put(Diem,contact.getdiemtrungbinh());
        db.update(TableName,values, Id + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from " + TableName + " Where ID=" + id;
        db.execSQL(sql);
        db.close();
    }


}
