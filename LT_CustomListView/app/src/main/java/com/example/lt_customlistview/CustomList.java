package com.example.lt_customlistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class CustomList extends BaseAdapter {
    private List<Employee> employees;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomList(List<Employee> employees, Context context) {
        this.employees = employees;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int i) {
        return employees.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.custom_list_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.imgView = (ImageView) convertView.findViewById(R.id.id_img);
            viewHolder.maView = (TextView) convertView.findViewById(R.id.ed_maNV);
            viewHolder.tenView = (TextView) convertView.findViewById(R.id.ed_ten);
            viewHolder.gtView = (TextView) convertView.findViewById(R.id.id_gioitinh);
            viewHolder.dvView = (Spinner) convertView.findViewById(R.id.spDonVi);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Employee e = this.employees.get(i);
        viewHolder.maView.setText("Mã số : "+e.getMaNhanVien());
        viewHolder.tenView.setText("Tên " + e.getTenNhanVien());
        viewHolder.gtView.setText("Giới tinh " +e.isGioiTinh());
        viewHolder.dvView.setTag("Đơn vị "+ e.getDonvi());
        int imgID = this.getMipmapResId(e.getMaNhanVien());
        viewHolder.imgView.setImageResource(imgID);

        return convertView;
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
        TextView maView;
        TextView tenView;
        TextView gtView;
        Spinner dvView;

    }
}
