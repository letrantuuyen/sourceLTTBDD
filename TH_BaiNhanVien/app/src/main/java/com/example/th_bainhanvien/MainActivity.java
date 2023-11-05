package com.example.th_bainhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt_Thoat = (Button) findViewById(R.id.btn_Thoat);
        EditText ed_Ma = (EditText) findViewById(R.id.ed_maNV);
        EditText ed_Ten = (EditText) findViewById(R.id.ed_ten);
        RadioButton rd_Nu = (RadioButton) findViewById(R.id.rd_Nu);
        RadioButton rd_Nam = (RadioButton) findViewById(R.id.rd_Nam);
        Spinner sp_DonVi = (Spinner) findViewById(R.id.spDonVi);
        Button btn_Them = (Button) findViewById(R.id.btnThem);
        Button btn_Xoa = (Button) findViewById(R.id.btnXoa);
        Button btn_sua = (Button) findViewById(R.id.btnSua);
        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}