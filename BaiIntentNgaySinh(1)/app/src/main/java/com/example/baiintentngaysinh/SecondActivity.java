package com.example.baiintentngaysinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tv_view = (TextView) findViewById(R.id.textView_View);
        String hoten = getIntent().getExtras().getString("ht");
        String namsinh = getIntent().getExtras().getString("ns");
        int a = Integer.parseInt(namsinh);
        Calendar calendar = Calendar.getInstance();
        int tuoi = calendar.get(Calendar.YEAR);
       int  Tinhtuoi = (tuoi - a);
        String st = "Ho ten " + hoten + "\n" + "Nam sinh " + namsinh +  "\n"+ "Tuoi " + Tinhtuoi ;
        tv_view.setText(st);

        Button bt_e = (Button) findViewById(R.id.button_exist);
        bt_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}