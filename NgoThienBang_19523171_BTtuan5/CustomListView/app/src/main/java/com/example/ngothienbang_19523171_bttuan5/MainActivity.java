package com.example.ngothienbang_19523171_bttuan5;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvSinhVien;
    EditText edtName,edtNamSinh;
    RadioButton rbNam, rbNu;
    Button btnThem;

    private List<SinhVien> mSinhVien;
    private SinhVienAdapter sinhVienAdapter;
    private SinhVien sinhVien;
    private String sex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UI_SETUP();

        mSinhVien = new ArrayList<>();
        sinhVienAdapter = new SinhVienAdapter(MainActivity.this, mSinhVien , R.layout.custom_sinh_vien);
        rbNam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbNu.setChecked(false);
            }
        });
        rbNu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbNam.setChecked(false);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenSinhVien = edtName.getText().toString();
                String namSinh = edtNamSinh.getText().toString();
                if (rbNam.isChecked()) {
                    sex = "Nam";
                    sinhVien = new SinhVien(tenSinhVien, namSinh, sex, R.drawable.man_student_icon);
                    mSinhVien.add(sinhVien);
                } else if (rbNu.isChecked()) {
                    sex = "Nữ";
                    sinhVien = new SinhVien(tenSinhVien, namSinh, sex, R.drawable.woman_student_icon);
                    mSinhVien.add(sinhVien);
                }
                lvSinhVien.setAdapter(sinhVienAdapter);
                sinhVienAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Đã thêm sinh viên mới", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void UI_SETUP() {
        lvSinhVien =(ListView) findViewById(R.id.lvSinhVien);
        edtNamSinh = (EditText) findViewById(R.id.edtNamSinh);
        edtName = (EditText) findViewById(R.id.edtSinhVien);
        rbNam =(RadioButton) findViewById(R.id.rbnam);
        rbNu=(RadioButton) findViewById(R.id.rbnu);
        btnThem =(Button) findViewById(R.id.btnThem);

    }
    public void Xoa(final int position){
        Toast.makeText(this,"Bạn chọn xóa sinh viên",Toast.LENGTH_SHORT).show();
        mSinhVien.remove(position);
        sinhVienAdapter.notifyDataSetChanged();
    }
    public void Sua(final int position){
        Toast.makeText(this,"Bạn đã chọn sửa sinh viên",Toast.LENGTH_SHORT).show();
        final SinhVien sinhVien = mSinhVien.get(position);
        View dialogSheetView= LayoutInflater.from(MainActivity.this).inflate(R.layout.edit_sinhvien,null);
        BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this);
        dialog.setContentView(dialogSheetView);

        EditText edtSinhVien = (EditText)dialogSheetView.findViewById(R.id.edtSinhVien);
        EditText edtNamSinh =(EditText)dialogSheetView.findViewById(R.id.edtNamSinh);
        RadioButton rbNam = (RadioButton) dialogSheetView.findViewById(R.id.rbnam);
        RadioButton rbNu = (RadioButton)dialogSheetView.findViewById(R.id.rbnu);
        Button btnCapNhat = (Button)dialogSheetView.findViewById(R.id.btnCapNhat);

        edtSinhVien.setText(mSinhVien.get(position).getName());
        edtNamSinh.setText(mSinhVien.get(position).getYear());
        if(mSinhVien.get(position).getSex().equals("Nam")){
            rbNam.setChecked(true);
        }else{
            rbNu.setChecked(true);
        }

        if(rbNam.isChecked()){
            sex="Nam";
            sinhVien.setHinhanh(R.drawable.man_student_icon);
            sinhVien.setSex(sex);

        }else {
            sex="Nữ";
            sinhVien.setHinhanh(R.drawable.woman_student_icon);
            sinhVien.setSex(sex);
        }

        rbNam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rbNu.setChecked(false);

            }
        });
        rbNu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbNam.setChecked(false);
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Ten = edtSinhVien.getText().toString().trim();
                final String namSinh = edtNamSinh.getText().toString().trim();

                sinhVien.setName(Ten);
                sinhVien.setYear(namSinh);

                lvSinhVien.setAdapter(sinhVienAdapter);

            }
        });



        dialog.show();


    }

}