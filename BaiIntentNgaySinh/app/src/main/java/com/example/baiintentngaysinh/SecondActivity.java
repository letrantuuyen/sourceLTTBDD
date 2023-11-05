package com.example.baiintentngaysinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EditText et_hoten = (EditText) findViewById(R.id.hoVaTen);
        EditText et_namsinh = (EditText) findViewById(R.id.namSinh);
        Button bt_submit = (Button) findViewById(R.id.subMit);

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.putExtra("ht",et_hoten.getText().toString());
                it.putExtra("ns",et_namsinh.getText().toString());
                setResult(RESULT_OK,it);
                finish();
            }
        });
    }
}