package com.example.myapplication;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DisplayAlbumActivity extends AppCompatActivity {
    ArrayList<Album> albums;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_album);
        setDataToView();
    }
    public void ReceiData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        albums = (ArrayList<Album>)(bundle.getSerializable("LISTALBUM"));
    }
    private void setDataToView(){
        ReceiData();
        ListView listViewAlbum = findViewById(R.id.lvAlbum);
        AlbumAdapter adapter = new AlbumAdapter(this,albums);
        adapter.notifyDataSetChanged();
        listViewAlbum.setAdapter(adapter);
        listViewAlbum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DisplayAlbumActivity.this, "Clicked to item at " + (position + 1), Toast.LENGTH_SHORT).show();
                itemAlbumClicked(view,position);
                adapter.notifyDataSetChanged();
            }
        });
        listViewAlbum.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final deleteAlbum dialog= new deleteAlbum();
                dialog.showAlerDialog(DisplayAlbumActivity.this,albums,position,adapter);
                //adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
    // goi den file editalbum
    private void itemAlbumClicked(View view,int positon){
        editAlbum.EditAlbumListener listener = new editAlbum.EditAlbumListener(){
            @Override
            public void btnUpdateEntered(String codeAlbum, String nameAlbum) {
//                TextView tvNameAlbum = view.findViewById(R.id.tvNameItemAlbum);
//                TextView tvCodeAlbum = view.findViewById(R.id.tvCodeItemAlbum);
//                tvNameAlbum.setText(nameAlbum);
//                tvCodeAlbum.setText(codeAlbum);
                albums.get(positon).setCodeAlbum(codeAlbum);
                albums.get(positon).setNameAlbum(nameAlbum);
            }
        };
        final editAlbum dialog = new editAlbum(this, listener);
        dialog.show();
    }
}