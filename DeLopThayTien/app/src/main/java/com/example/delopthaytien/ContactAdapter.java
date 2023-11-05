package com.example.delopthaytien;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private List<Contact> contactList;
    private LayoutInflater layoutInflater;
    private Context context;

    public ContactAdapter(List<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return contactList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view = layoutInflater.inflate(R.layout.activity_detail,null);
            viewHolder = new ViewHolder();
            viewHolder.imgView = (ImageView) view.findViewById(R.id.imgView);
            viewHolder.mobileTextView = (TextView) view.findViewById(R.id.tv_Mobile);
            viewHolder.workTextView = (TextView) view.findViewById(R.id.tv_Work);
            viewHolder.homeTextView = (TextView) view.findViewById(R.id.tv_home);
            viewHolder.emailTextView = (TextView) view.findViewById(R.id.tv_email);
            viewHolder.addressTextView = (TextView) view.findViewById(R.id.tv_address);

            view.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) view.getTag(i);
        }
        Contact c = this.contactList.get(i);
        viewHolder.mobileTextView.setText("Mobile : "+ c.getMobilePhone());
        viewHolder.workTextView.setText("Work : "+ c.getWorkPhone());
        viewHolder.homeTextView.setText("Home : "+ c.getHomePhone());
        viewHolder.emailTextView.setText("Email : "+ c.getEmail());
        viewHolder.addressTextView.setText("Address : "+ c.getAddress());
        int imgID = this

        return view;
    }
    public int getResId(String resName)
    {
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName,
                "drawable",pkgName);
        Log.i("CustomListView", "ResName : "+ resName+ "==> ResID = " + resID);
        return resID;
    }
    public static class ViewHolder{
        ImageView imgView;
        TextView mobileTextView;
        TextView workTextView;
        TextView homeTextView;
        TextView emailTextView;
        TextView addressTextView;

    }
}
