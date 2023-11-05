package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class NguyenGiaHy20008341 extends AppCompatActivity {
    Button them,xem,quanly;
    EditText ma,ten;
    ArrayList<Ablum> dsab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguyen_gia_hy20008341);
        them=findViewById(R.id.them);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showdiaLog();

            }
        });
    }

}