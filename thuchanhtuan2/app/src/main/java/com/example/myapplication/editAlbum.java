package com.example.myapplication;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class editAlbum  extends Dialog {
    interface EditAlbumListener {
        void btnUpdateEntered(String codeAlbumUpdate, String nameAlbumUpdate);
    }
    public Context context;

    private Button btnDeleteUpdate,btnUpdate;
    private EditText etCodeAlbumUpdate,etNameAlbumUpdate;

    private EditAlbumListener listener;

    public editAlbum(DisplayAlbumActivity context, EditAlbumListener listener) {
        super(context);
        this.context = this.context;
        this.listener = this.listener;
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit_album);

        btnDeleteUpdate = findViewById(R.id.btnDeleteUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);
        etCodeAlbumUpdate = findViewById(R.id.etCodeAlbumUpdate);
        etNameAlbumUpdate = findViewById(R.id.etNameAlbumUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnUpadateClick();

            }
        });

        btnDeleteUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDeleteClick();

            }
        });
    }

    private void btnUpadateClick(){
        String codeAlbumUpdate = etCodeAlbumUpdate.getText().toString();
        String nameAlbumUpdate = etNameAlbumUpdate.getText().toString();
        if(codeAlbumUpdate == null || nameAlbumUpdate == null) {
            Toast.makeText(this.context, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }
        this.dismiss();
        if(this.listener != null){
            this.listener.btnUpdateEntered(codeAlbumUpdate,nameAlbumUpdate);
        }
    }
    private void btnDeleteClick(){
        etNameAlbumUpdate.setText("");
        etCodeAlbumUpdate.setText("");
    }
}