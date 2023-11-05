package com.example.thhanhsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listviewDanhSachNhanVien;
    private SQLiteHelper sqLiteHelper;
    List<Nhanvien> NhanVienList;
    NhanVienApdapter nhanVienApdapter;
    int vitri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listviewDanhSachNhanVien=findViewById(R.id.listviewDanhSachNhanVien);

        sqLiteHelper = new SQLiteHelper(MainActivity.this);
        NhanVienList=new ArrayList<>();
        Cursor cursor=sqLiteHelper.getData("SELECT * FROM NhanVien");
        while (cursor.moveToNext()){
            String maNV = cursor.getString(0);
            String tenNV = cursor.getString(1);
            int age=cursor.getInt(2);
            Nhanvien nhanvienEntity = new Nhanvien(maNV,tenNV,age);
            NhanVienList.add(nhanvienEntity);
        }
        nhanVienApdapter = new NhanVienApdapter(this,R.layout.itemnamnhanvien,NhanVienList);
        listviewDanhSachNhanVien.setAdapter(nhanVienApdapter);
        nhanVienApdapter.notifyDataSetChanged();




        listviewDanhSachNhanVien.setOnItemLongClickListener((parent, view, position, id) -> {
           Nhanvien nv=NhanVienList.get(position);
            vitri=position;
            Intent intent=new Intent(MainActivity.this, DiaLogNhanVien.class);
            intent.putExtra("maNhanVien",nv.getMaNhanvien());
            intent.putExtra("tenNhanVien",nv.getTenNhanVien());
            intent.putExtra("tuoiNhanVien",nv.getAge());
            startActivity(intent);
            return true;
        });







        Intent itenxoanhanvien=getIntent();
        String maNhanvienXoa=itenxoanhanvien.getStringExtra("maNhanVienXoa");
//        if(maNhanvienXoa!=null){
//            NhanVienList.remove(vitri);
//
//        }

//        listviewDanhSachNhanVien.setOnItemLongClickListener((parent, view, position, id) -> {
//            listviewDanhSachNhanVien.get(position)
//
//
//
//
//        );
//




    }
}