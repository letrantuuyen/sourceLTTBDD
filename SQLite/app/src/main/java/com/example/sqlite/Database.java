package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context , "BD.sqlite",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Author("+
                " "+
                "id_author integer primary key , "+
                "name text, "+
                "address text, " +
                "email text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop table if exists Author");
        onCreate(db);
    }

    // them - xoa - sua - truy van
    public int insertAuthor(Author author){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_author",author.getId_author());
        contentValues.put("name",author.getName());
        contentValues.put("address",author.getAddress());
        contentValues.put("email",author.getEmail());
        int result = (int)database.insert("Author",null,contentValues);
        database.close();
        return result;
    }
    public ArrayList<Author> getAllAuthor(){
        ArrayList<Author> listAu = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select *from Author", null);
        if(cursor != null)
            cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            listAu.add(new Author(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return listAu;
    }
    public Author getAuthorById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select *from Author where id = "+id, null);
        if(cursor != null && cursor.moveToFirst())

             id = cursor.getInt(1);
            String authorName = cursor.getString(2);
            String authorAddress = cursor.getString(3);
            String authorEmail = cursor.getString(4);
            Author author = new Author(id,authorName,authorAddress,authorEmail);
            cursor.close();

        return author;
    }
    public Author deleteAuthorById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("deletee Author where id = "+id, null);
        if(cursor != null && cursor.moveToFirst())

            id = cursor.getInt(1);
        String authorName = cursor.getString(2);
        String authorAddress = cursor.getString(3);
        String authorEmail = cursor.getString(4);
        Author author = new Author(id,authorName,authorAddress,authorEmail);
        cursor.close();

        return author;
    }
}
