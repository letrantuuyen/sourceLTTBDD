package com.example.myapplicationteim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImageListAdapter extends ArrayAdapter<ImageItem> {
    private int selectedPosition = -1;

    public ImageListAdapter(Context context, List<ImageItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ImageItem item = getItem(position);

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
        // Kiểm tra xem vị trí hiện tại có phải là selectedPosition không
        if (position == selectedPosition) {
            itemView.setBackgroundResource(R.drawable.list_item_selector);
        } else {
            // Nếu item không được chọn, sử dụng màu nền mặc định hoặc màu nền khác theo mong muốn
            itemView.setBackgroundResource(com.google.android.material.R.color.design_default_color_background);
        }

        return itemView;
    }
}