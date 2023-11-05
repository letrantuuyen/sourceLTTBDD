package com.example.lt_bai1;

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

        EditText et_heSoA = (EditText) findViewById(R.id.heSoA);
        EditText et_heSoB = (EditText) findViewById(R.id.heSoB);
        Button bt_tong = (Button) findViewById(R.id.button_cong);
        TextView tv_kq = (TextView) findViewById(R.id.ketqua);

        bt_tong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hsa = et_heSoA.getText().toString();
                int a = Integer.parseInt(hsa);
                String hsb = et_heSoB.getText().toString();
                int b =Integer.parseInt(hsb);
                tv_kq.setText("Ket qua "+(a+b));

            }
        });
    }
}