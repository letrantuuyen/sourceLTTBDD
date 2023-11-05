package com.example.onthigk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ArrayList<NhanVien> nhanViens=new ArrayList<>();
    NhanVien nhanVien=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);
        EditText manv=findViewById(R.id.tv_ma);
        EditText tennv=findViewById(R.id.tv_ten);
        EditText gioitinhnv=findViewById(R.id.tv_gioitinh);
        EditText donvinv=findViewById(R.id.tv_donvi);


        String ma=getIntent().getExtras().getString("ma");
        String ten=getIntent().getExtras().getString("ten");
        String gioitinh=getIntent().getExtras().getString("gioitinh");
        String donvi=getIntent().getExtras().getString("donvi");


        manv.setText("Mã nhân viên: "+ma);
        tennv.setText("Tên nhân viên: "+ten);
        gioitinhnv.setText("Giới tính nhân viên: "+gioitinh);
        donvinv.setText("Đơn vị: "+donvi);
        Button btn_sua=findViewById(R.id.btn_sua);
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma=manv.getText().toString();
                String ten=tennv.getText().toString();
                String gioiTinh=gioitinhnv.getText().toString();
                String donvi=donvinv.getText().toString();
                nhanVien= new NhanVien();
                nhanVien.setMa(ma);
                nhanVien.setTen(ten);
                nhanVien.setGioitinh(gioiTinh);
                nhanVien.setDonvi(donvi);

            }
        });

        Button btn_quaylai=findViewById(R.id.btn_xong);
        btn_quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trave = new Intent();
                trave.putExtra("str", (Parcelable) nhanVien);
                setResult(RESULT_OK, trave);
                finish();
            }
        });
    }
}