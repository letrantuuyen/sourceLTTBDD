package com.example.thhanhsqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(@Nullable Context context) {
        super(context, "/data/data/com.example.thhanhsqlite/files/quanlyNhanVien.db", null, 1);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
       return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

//       sqLiteHelper.insertBook(tenSach,ngayXuatBan);


  //  deleteDataTacGia(tacGiaArrayList.get(position).getMaTacGia())
    public boolean deleteDataNhanVien(String maTG){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM NhanVien WHERE MaNhanVien = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,maTG);
        statement.executeUpdateDelete();
        return true;
    }
}
