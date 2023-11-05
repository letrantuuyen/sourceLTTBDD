package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class deleteAlbum {

    public static  void showAlerDialog(final Context context, ArrayList<Album> albums, int position, AlbumAdapter adapter){
        ArrayList<Album> album = albums;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete").setMessage("Ban có muốn xóa album ??");

        builder.setCancelable(true);


        // tao 2 nut nhan trong AlertDialog
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                album.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }
}