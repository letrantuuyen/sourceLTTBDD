package com.example.anhki.tradingbook.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ImageView;

import com.example.anhki.tradingbook.View.Fragment.Image.FragmentImage1;
import com.example.anhki.tradingbook.View.Fragment.Image.FragmentImage2;

import java.util.List;

public class ViewPagerAdapterImage extends FragmentPagerAdapter {
    List<Fragment> fragments;
    public ViewPagerAdapterImage(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        fragments = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
//        switch (position) {
//            case 0:
//                return FragmentImage1.newInstance();
//            default:
//                return FragmentImage2.newInstance();
//        }
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
