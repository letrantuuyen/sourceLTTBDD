package com.example.myapplication;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;

import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAddAlbum,btnDisplayAlbum,btnManagerSing;
    ArrayList<Album>albums = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapIdToView();

        btnAddAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAddDialogClicked();
            }
        });
        btnDisplayAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intentSendAlbum = new Intent(getApplicationContext(), DisplayAlbumActivity.class);
                bundle.putSerializable("LISTALBUM", albums);
                intentSendAlbum.putExtra("Bundle", bundle);
                startActivity(intentSendAlbum);

            }
        });

        btnManagerSing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, insert_album.class);
                startActivity(intent);
            }
        });
    }
    public void mapIdToView(){
        btnAddAlbum = findViewById(R.id.btnAddAlbum);
        btnDisplayAlbum = findViewById(R.id.btnDisplayAlbum);
        btnManagerSing = findViewById(R.id.btnManagerSing);
    }
    private void btnAddDialogClicked()  {
        CustomDialog.SaveAlbumListener listener = new CustomDialog.SaveAlbumListener() {
            @Override
            public void btnSaveEntered(String codeAlbum,String nameAlbum) {
                Album album = new Album(codeAlbum, nameAlbum);
                albums.add(album);
            }
        };
        final CustomDialog dialog = new CustomDialog(this, listener);

        dialog.show();
    }
}