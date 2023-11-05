package com.example.ongk_customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<Employee> {
    private int selectedPosition = -1;

    public EmployeeAdapter(Context context, List<Employee> items) {

        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Employee item = getItem(position);

        ImageView imageView = itemView.findViewById(R.id.imageView);
        TextView textViewMaNV = itemView.findViewById(R.id.textView_id);
        TextView textViewTenNV = itemView.findViewById(R.id.textView_name);
        TextView textViewGioiTinh = itemView.findViewById(R.id.textView_gioitinh);
        TextView textViewDonVi = itemView.findViewById(R.id.textView_donvi);

        imageView.setImageURI(item.getImageUri());
        textViewMaNV.setText("Mã số: " + item.getMaNV());
        textViewTenNV.setText("Họ tên: " + item.getTenNV());
        textViewGioiTinh.setText("Giới tính: " + item.getGioiTinh());
        textViewDonVi.setText("Đơn vị: " + item.getDonVi());

        return itemView;
    }
}
