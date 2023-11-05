package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AlbumAdapter extends ArrayAdapter<Album> {
    public static int stt=0;
    ArrayList<Album> albums;
    public AlbumAdapter(@NonNull Context context, ArrayList<Album> albums) {
        super(context, R.layout.item_album_layout2, albums);
        this.albums = albums;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null) {
            //nạp từ layout bằng inflate
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            view = layoutInflater.inflate(R.layout.item_album_layout2, null);
        } else {
            view = convertView;
        }

        Album item = albums.get(position);
        //gán dữ liệu ở vị trí possition vào View và trả về đối tượng View này
        setDataToView(item, view);
        return view;
    }


    private void setDataToView(Album item, View view){
        TextView tvStt = view.findViewById(R.id.tvStt);
        tvStt.setText(String.valueOf(stt));
        TextView tvCodeAlbumItem = view.findViewById(R.id.tvCodeItemAlbum);
        tvCodeAlbumItem.setText(item.getCodeAlbum());
        TextView tvNameItemAlbum = view.findViewById(R.id.tvNameItemAlbum);
        tvNameItemAlbum.setText(item.getNameAlbum());
    }
}