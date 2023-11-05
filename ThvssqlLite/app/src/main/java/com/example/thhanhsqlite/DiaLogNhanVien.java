package com.example.thhanhsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DiaLogNhanVien extends Activity {
  EditText edmaNV,edtenNV,edageNV;
  Button xoaNhanVien,

    trove;
    private   SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialognhanvien);
        sqLiteHelper = new SQLiteHelper(this);
        Intent intent=getIntent();
        edmaNV=findViewById(R.id.edmaNV);
        edtenNV=findViewById(R.id.edtenNV);
        edageNV=findViewById(R.id.edageNV);
        xoaNhanVien=findViewById(R.id.xoaNhanVien);
        trove=findViewById(R.id.trove);
        String maNhanVien=intent.getStringExtra("maNhanVien");
        String tenNhanVien=intent.getStringExtra("tenNhanVien");
        int tuoiNhanVien = intent.getIntExtra("tuoiNhanVien", 0);
        edmaNV.setText(maNhanVien);
        edtenNV.setText(tenNhanVien);
        edageNV.setText(tuoiNhanVien+"");
        xoaNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             boolean rs=   sqLiteHelper.deleteDataNhanVien(edmaNV.getText().toString());
             if(rs){
                 Toast.makeText(DiaLogNhanVien.this, "xoaThanhCong", Toast.LENGTH_SHORT).show();
                 Intent intent=new Intent(DiaLogNhanVien.this, MainActivity.class);

                 intent.putExtra("maNhanVienXoa",edmaNV.getText().toString());
                 startActivity(intent);
             }
             else {
                 Toast.makeText(DiaLogNhanVien.this, "xoa that bai", Toast.LENGTH_SHORT).show();
             }}});



        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }
}