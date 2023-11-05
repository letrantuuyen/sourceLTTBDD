package com.example.anhki.tradingbook.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anhki.tradingbook.R;
import com.example.anhki.tradingbook.View.Fragment.Admin.AccAdmin;
import com.example.anhki.tradingbook.View.Fragment.Admin.CheckProduct;
import com.example.anhki.tradingbook.View.Fragment.Admin.Invoice;
import com.example.anhki.tradingbook.View.SlashScreenActivity;

public class FragmentAdmin extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home_admin, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.subtablayoutadmin);
        viewPager = (ViewPager) view.findViewById(R.id.subviewpageradmin);

        FragmentAdmin.SectionsPagerAdapter mSectionsPagerAdapter = new FragmentAdmin.SectionsPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mSectionsPagerAdapter);

        return view;
    }
    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return CheckProduct.newInstance(1);
                case 1:
                    return Invoice.newInstance(2);
                default:
                    return AccAdmin.newInstance(3);
            }

        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "Chưa xử lý (" + String.valueOf(SlashScreenActivity.countWaitingAdmin) + ")" ;
                case 1:
                    return "Hóa đơn";
                default:
                    return "Tài khoản";
            }
        }
    }

}
