package com.example.lt_pt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import com.example.lt_pt.PhuongTrinhBacNhat;

public class BacNhatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac_nhat);

        TextView tv_V = (TextView) findViewById(R.id.bn_tv);
        EditText ed_NhapA = (EditText) findViewById(R.id.bn_NhapA);
        EditText ed_NhapB = (EditText) findViewById(R.id.bn_NhapB);
        String st = "aaaa";
        tv_V.setText(st);
    }
}