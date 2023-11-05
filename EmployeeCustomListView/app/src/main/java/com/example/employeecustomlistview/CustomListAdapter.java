package com.example.employeecustomlistview;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private List<Employee> listEm;
    private LayoutInflater layoutInflater;
    private Context context;



    @Override
    public int getCount() {
        return listEm.size();
    }

    @Override
    public Object getItem(int i) {

        com.example.employeecustomlistview.Employee employee = listEm.get(i);
        return employee;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if(view == null){
            view = layoutInflater.inflate(R.layout.custom_list_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.imgView = (ImageView) view.findViewById(R.id.idIM);
            viewHolder.msView = (TextView) view.findViewById(R.id.tvMS);
            viewHolder.nameView = (TextView) view.findViewById(R.id.tvHT);
            viewHolder.gtView = (TextView) view.findViewById(R.id.tvGT);
            viewHolder.dvView = (TextView) view.findViewById(R.id.tvDV);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Employee e;
        e = listEm.get(i);
        viewHolder.msView.setText("Mã số : " +e.getMaSo());
        viewHolder.nameView.setText("Tên " + e.getHoTen());
        viewHolder.gtView.setText("Giới tinh " +e.getGioiTinh());
        viewHolder.dvView.setText("Đơn vị "+ e.getDonVi());
        int imgID = this.getMipmapResId(e.getMaSo());
        viewHolder.imgView.setImageResource(imgID);
        return view;
    }

    public int getMipmapResId(String resName)
    {
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName,
                "mipmap",pkgName);
        Log.i("CustomListView", "ResName : "+ resName+ "==> ResID = " + resID);
        return resID;
    }

    public static class ViewHolder{
        ImageView imgView;
        TextView nameView;
        TextView msView;
        TextView gtView;
        TextView dvView;
    }
}

