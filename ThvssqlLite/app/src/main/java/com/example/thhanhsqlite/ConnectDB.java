package com.example.thhanhsqlite;

import android.database.sqlite.SQLiteDatabase;

public class ConnectDB {
    SQLiteDatabase database = ConnectDB.getInstace().getSqLiteDatabase();
    private static ConnectDB instace;
    private SQLiteDatabase sqLiteDatabase;
    private final String SCRIPT_CREATE_EMPLOYEES_TABLE = "CREATE TABLE IF NOT EXISTS NhanVien" +
            "(MaNhanVien TEXT PRIMARY KEY, " +
            "TenNhanVien TEXT, " +
            "tuoiNhanVien INTEGER)";

    ConnectDB(){
        String path ="/data/data/com.example.thhanhsqlite/files/quanlyNhanVien.db";
        sqLiteDatabase = SQLiteDatabase.openDatabase(path, null , SQLiteDatabase.CREATE_IF_NECESSARY);
        if (sqLiteDatabase != null){
            sqLiteDatabase.execSQL(SCRIPT_CREATE_EMPLOYEES_TABLE);
            insertData();
        }

    }

    public static ConnectDB getInstace() {
        if (instace == null)
            instace = new ConnectDB();
        return instace;
    }

    public void close(){
        if (sqLiteDatabase != null)
            sqLiteDatabase.close();
    }

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }

    private void insertData(){
        final String TABLE_NAME = "NhanVien";
        String sql = "INSERT INTO \"NhanVien\" (\"MaNhanVien\", \"TenNhanVien\", \"tuoiNhanVien\")\n" +
                "VALUES \n" +
                "(\"NV-113\", \"Nguyễn Đại Nhân\", 25),\n" +
                "(\"NV-114\", \"Trần Đại Nghĩa \", 30),\n" +
                "(\"NV-115\", \"Nguyễn Gia Hy \", 28),\n" +
                "(\"NV-116\", \"Nguyễn GIA Khang \", 27),\n" +
                "(\"NV-117\", \"Nguyễn Gia Han  E\", 32)\n" ;


        sqLiteDatabase.execSQL(sql);
    }
}

