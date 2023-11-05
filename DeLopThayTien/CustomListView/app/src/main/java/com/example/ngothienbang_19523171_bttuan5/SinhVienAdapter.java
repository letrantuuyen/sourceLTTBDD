package com.example.ngothienbang_19523171_bttuan5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter{
    private MainActivity mContext;
    private List<SinhVien> mSinhvien;
    private int layout;

    public SinhVienAdapter(MainActivity mContext, List<SinhVien> mSinhvien, int laypout) {
        this.mContext = mContext;
        this.mSinhvien = mSinhvien;
        this.layout = laypout;
    }

    @Override
    public int getCount() {
        return mSinhvien.size();
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);
        ImageView imghinh = (ImageView) convertView.findViewById(R.id.imghinh);
        TextView tvName= (TextView) convertView.findViewById(R.id.tvName);
        TextView tvyear= (TextView) convertView.findViewById(R.id.tvyear);
        TextView tvsex = (TextView) convertView.findViewById(R.id.tvsex);
        ImageButton imgSua = (ImageButton) convertView.findViewById(R.id.imgSua);
        ImageButton imgXoa = (ImageButton) convertView.findViewById(R.id.imgXoa);
        SinhVien sinhVien = mSinhvien.get(position);
        tvName.setText(sinhVien.getName());
        tvyear.setText(sinhVien.getYear());
        tvsex.setText(sinhVien.getSex());
        imghinh.setImageResource(sinhVien.getHinhanh());

        imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.Sua(position);
            }
        });
        imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.Xoa(position);
            }
        });
        return convertView;
    }
}
