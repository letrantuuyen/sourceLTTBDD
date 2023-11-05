package com.example.thhanhsqlite;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NhanVienApdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Nhanvien> list;

    public NhanVienApdapter(Context context, int layout, List<Nhanvien> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // tham so dau tien layout tham so thu 2 view group
        //buc 1
        view=layoutInflater.inflate(layout,null);
        TextView name=view.findViewById(R.id.idNameNhanVien);
        Nhanvien nv=list.get(i);
        name.setText(nv.getTenNhanVien());
        return view;
    }
}
