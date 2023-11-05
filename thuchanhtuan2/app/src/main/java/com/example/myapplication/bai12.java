package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class bai12 extends AppCompatActivity {
    EditText et1,et2;
    CheckBox ck1;
    Button btDn,btThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai12);
        et1=findViewById(R.id.editTextTextPersonName);
        et2=findViewById(R.id.editTextTextPersonName2);
        ck1=findViewById(R.id.checkBox);
        btDn=findViewById(R.id.button2);
        btThoat=findViewById(R.id.button3);

        ck1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                Toast.makeText(this, "Toast thông báo là “chào mừng \n" +
                        "bạn đăng nhập hệ thống, bạn đã lưu thông tin", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "mừng bạn đăng nhập hệ thống, bạn không lưu thông tin", Toast.LENGTH_SHORT).show();
            }
        });
        btThoat.setOnClickListener(view -> showDiaLog());


    }

    private void showDiaLog() {
        AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setTitle("Question")
                .setMessage("Are you sure you want to exit?")
                .setIcon(R.drawable.ic_launcher_background)
                .setPositiveButton("No", (dialog, which) -> {
                })
                .setNegativeButton("Yes", (dialog, which) -> {
                    finish();
                })
                .create();
        alertDialog.show();
    }
}