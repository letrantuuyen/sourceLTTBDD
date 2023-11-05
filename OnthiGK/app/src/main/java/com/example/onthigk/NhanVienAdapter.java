package com.example.onthigk;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {

    Activity context=null;
    ArrayList<NhanVien> myArrayList=null;
    int layoutId;


    public NhanVienAdapter(Activity context, int textViewResourceId, ArrayList<NhanVien> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.layoutId = textViewResourceId;
        this.myArrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        convertView=inflater.inflate(layoutId,null);
        if(myArrayList.size()>0&&position>=0){
            ImageView img=convertView.findViewById(R.id.imageView);
            TextView thongtin=convertView.findViewById(R.id.thongtin);

            NhanVien nhanVien=myArrayList.get(position);
            if (nhanVien.getGioitinh().equals("Nữ")){
                img.setImageResource(R.drawable.nv_nu);
            }
            else {
                img.setImageResource(R.drawable.nv_nam);
            }
            thongtin.setText("Mã nhân viên: "+nhanVien.getMa()+"\n"
            +"Tên nhân viên: "+nhanVien.getTen()+"\n"
            +"Giới tính: "+nhanVien.getGioitinh()+"\n"
            +"Đơn vị: "+nhanVien.getDonvi());
        }
        return convertView;
    }
}
