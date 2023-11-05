package com.example.th_bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ed_SoA = (EditText) findViewById(R.id.edSoA);
        EditText ed_SoB = (EditText) findViewById(R.id.edSoB);

        Button bt_Tong = (Button) findViewById(R.id.bt_tong);
        Button bt_Hieu = (Button) findViewById(R.id.bt_hieu);
        Button bt_Tich = (Button) findViewById(R.id.bt_tich);
        Button bt_Thuong = (Button) findViewById(R.id.bt_thuong);
        Button bt_Thoat = (Button) findViewById(R.id.bt_thoat);
        Button bt_UC = (Button) findViewById(R.id.bt_ucln);

        TextView tv_ketQua = (TextView) findViewById(R.id.tv_kq);

        bt_Tong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hsa = ed_SoA.getText().toString();
                int a = Integer.parseInt(hsa);
                String hsb = ed_SoA.getText().toString();
                int b = Integer.parseInt(hsb);
                tv_ketQua.setText("" + (a+b));


            }
        });

        bt_Hieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hsa = ed_SoA.getText().toString();
                int a = Integer.parseInt(hsa);
                String hsb = ed_SoA.getText().toString();
                int b = Integer.parseInt(hsb);
                tv_ketQua.setText("" +(a-b));

            }
        });

        bt_Tich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hsa = ed_SoA.getText().toString();
                int a = Integer.parseInt(hsa);
                String hsb = ed_SoA.getText().toString();
                int b = Integer.parseInt(hsb);
                tv_ketQua.setText("" +(a*b));
            }
        });
        bt_Thuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hsa = ed_SoA.getText().toString();
                int a = Integer.parseInt(hsa);
                String hsb = ed_SoA.getText().toString();
                int b = Integer.parseInt(hsb);
                tv_ketQua.setText("" +(a/b));
            }
        });

        bt_UC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bt_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}