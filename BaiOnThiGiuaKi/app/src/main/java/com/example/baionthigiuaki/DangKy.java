package com.example.baionthigiuaki;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DangKy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        EditText ed_UserDK = (EditText) findViewById(R.id.edNhapTen);
        EditText ed_PasswordDK = (EditText) findViewById(R.id.edNhapPass);
        Button btDK = (Button) findViewById(R.id.btDangKi);
        TextView tv_DaCoTK = (TextView) findViewById(R.id.tvDaCoTK);

        tv_DaCoTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKy.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = ed_UserDK.getText().toString();
                String passWord = ed_PasswordDK.getText().toString();

                SharedPreferences sharedPreference = getSharedPreferences("MyPr", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreference.edit();
                editor.putString("username",userName);
                editor.putString("password",passWord);
                editor.apply();
                Toast.makeText(DangKy.this,"Đăng ký thành công ", Toast.LENGTH_LONG).show();
            }
        });


    }
}