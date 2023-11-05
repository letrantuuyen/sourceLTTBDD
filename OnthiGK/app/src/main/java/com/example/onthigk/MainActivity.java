package com.example.onthigk;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ListMenuItemView;

import static android.R.layout.simple_list_item_1;

import android.content.ClipData;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    ArrayList<NhanVien> nhanViens=new ArrayList<>();
    String[] dv_list;
    String donvi;
    EditText ed_ma,ed_ten;
    RadioButton nam,nu;
    RadioGroup gioiTinhGroup;
    Spinner sp_donvi;
    ListView lv_nhanvien;
    private String fileName="nhanvien.txt";
    private String filePath="raw";
    File myInternalFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_ma=findViewById(R.id.ed_ma);
        ed_ten=findViewById(R.id.ed_ten);
        lv_nhanvien=findViewById(R.id.lv_danhSach);
        nam=findViewById(R.id.nam);
        nu=findViewById(R.id.nu);
        gioiTinhGroup=findViewById(R.id.gioitinh);
        Button btn_them=findViewById(R.id.btn_them);
        Button btn_doc=findViewById(R.id.btn_doc);
        Button btn_ghi=findViewById(R.id.btn_ghi);

        ContextWrapper contextWrapper = new ContextWrapper(
                getApplicationContext());
        //mở file
        File directory=contextWrapper.getDir(filePath,Context.MODE_PRIVATE);
        myInternalFile = new File(directory, fileName);

        sp_donvi=findViewById(R.id.donvi);

        dv_list=getResources().getStringArray(R.array.donvi);
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, simple_list_item_1,dv_list);
        sp_donvi.setAdapter(adapter);

        sp_donvi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                donvi=dv_list[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma=ed_ma.getText().toString();
                String ten=ed_ten.getText().toString();
                String gioiTinh=((RadioButton)findViewById(gioiTinhGroup.getCheckedRadioButtonId())).getText().toString();

                NhanVien nhanVien= new NhanVien();
                nhanVien.setMa(ma);
                nhanVien.setTen(ten);
                nhanVien.setGioitinh(gioiTinh);
                nhanVien.setDonvi(donvi);
                nhanViens.add(nhanVien);

                NhanVienAdapter nhanVienAdapter= new NhanVienAdapter(MainActivity.this,R.layout.custom_listview,nhanViens);
                lv_nhanvien.setAdapter(nhanVienAdapter);

                ed_ma.setText("");
                ed_ten.setText("");
                ed_ma.requestFocus();
            }
        });
        lv_nhanvien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NhanVien nv= nhanViens.get(i);
                ed_ma.setText(nv.getMa()+"");
                ed_ten.setText(nv.getTen());
//                gioi tinh
                if(nv.getGioitinh().equals("Nam"))
                    nam.setChecked(true);
                else nu.setChecked(true);
//                xu li don vi
                for (int j=0; j<dv_list.length; j++)
                    if(dv_list[j].equals(nv.getDonvi()))
                        sp_donvi.setSelection(j);
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("ma",ed_ma.getText().toString());
                intent.putExtra("ten",ed_ten.getText().toString());
                intent.putExtra("gioitinh",nv.getGioitinh().toString());
                intent.putExtra("donvi",nv.getDonvi().toString());
                startActivity(intent);

            }
        });
        btn_ghi.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                try {
                    //Mở file
                    FileOutputStream fos= new FileOutputStream(myInternalFile);
                    //Ghi file
                    fos.write(lv_nhanvien.toString().getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this,"Ghi thanh cong",Toast.LENGTH_LONG).show();

            }
        });
        btn_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myData = "";
                try {

                    FileInputStream fis = new FileInputStream(myInternalFile);
                    DataInputStream in = new DataInputStream(fis);
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(in));
                    String strLine;
                    //Đọc từng dòng
                    while ((strLine = br.readLine()) != null) {
                        myData = myData + strLine;
                    }

                    in.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(MainActivity.this,"Doc thanh cong",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_xemdanhsach:
                Toast.makeText(this,"Xem danh sách",Toast.LENGTH_LONG).show();
                return true;
            case R.id.mnxemdsSV:
                Toast.makeText(this,"Xem danh sách sinh viên",Toast.LENGTH_LONG).show();
                return true;
            case R.id.mnxemdsLH:
                Toast.makeText(this,"Xem danh sách lớp học",Toast.LENGTH_LONG).show();
                return true;
            case R.id.suadanhsach:
                return true;
            case R.id.xoadanhsach:
                return true;
            case R.id.indanhsach:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}