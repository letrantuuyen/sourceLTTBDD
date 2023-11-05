package com.example.baiintentngaysinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et_hoten = (EditText) findViewById(R.id.hoVaTen);
        EditText et_namsinh = (EditText) findViewById(R.id.namSinh);
        Button bt_submit = (Button) findViewById(R.id.subMit);

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cau noi bat mainactivity vs secondactivity
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                //chat do
                intent.putExtra("ht",et_hoten.getText().toString());
                intent.putExtra("ns",et_namsinh.getText().toString());
                //gui di
                startActivity(intent);
            }
        });
    }
}