package com.example.anhki.tradingbook.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.anhki.tradingbook.View.Fragment.InfoUser.Comment;
import com.example.anhki.tradingbook.View.Fragment.InfoUser.Product;

public class ViewPagerAdapterInfoUser extends FragmentPagerAdapter {
    public ViewPagerAdapterInfoUser(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Comment.newInstance();
            default:
                return Product.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Bình luận";
            default:
                return "Sản phẩm";
        }
    }
}
