package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomDialog extends Dialog {
    interface SaveAlbumListener {
        public void btnSaveEntered(String codeAlbum,String nameAlbum);
    }

    public Context context;

    private Button btnDelete,btnSave;
    private EditText etCodeAlbum,etNameAlbum;

    private SaveAlbumListener listener;

    public CustomDialog(Context context, SaveAlbumListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_album_dialog);

        btnDelete = findViewById(R.id.btnDeleteUpdate);
        btnSave = findViewById(R.id.btnUpdate);
//        etCodeAlbum = findViewById(R.id.etCodeAlbum);
//        etNameAlbum = findViewById(R.id.etNameAlbum);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSaveClick();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDeleteClick();
            }
        });


    }

    private void btnSaveClick()  {
        String codeAlbum = etCodeAlbum.getText().toString();
        String nameAlbum = etNameAlbum.getText().toString();

        if(codeAlbum== null || nameAlbum==null)  {
            Toast.makeText(this.context, "Please enter your name", Toast.LENGTH_LONG).show();
            return;
        }
        this.dismiss();

        if(this.listener!= null)  {
            this.listener.btnSaveEntered(codeAlbum,nameAlbum);
        }
    }

    private void btnDeleteClick()  {
        etNameAlbum.setText("");
        etCodeAlbum.setText("");

    }
}