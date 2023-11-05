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
import com.example.anhki.tradingbook.View.Fragment.User.Account;
import com.example.anhki.tradingbook.View.Fragment.User.Selling;
import com.example.anhki.tradingbook.View.Fragment.User.Sold;
import com.example.anhki.tradingbook.View.Fragment.User.Waiting;
import com.example.anhki.tradingbook.View.SlashScreenActivity;

public class FragmentUser extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    String b;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home_user, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.subtablayoutuser);
        viewPager = (ViewPager) view.findViewById(R.id.subviewpageruser);

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
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
                    return Selling.newInstance(1);
                case 1:
                    return Waiting.newInstance(2);
                case 2:
                    return Sold.newInstance(3);
                default:
                    return Account.newInstance(4);
            }

        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {


            switch (position) {
                case 0:
                    b = "Đang bán (" + String.valueOf(SlashScreenActivity.countSelling) + ")" ;
                    return b;
                case 1:
                    return "Đang chờ (" + String.valueOf(SlashScreenActivity.countWaiting) + ")" ;
                case 2:
                    return "Đã bán (" + String.valueOf(SlashScreenActivity.countSold) + ")" ;
                default:
                    return "Tài khoản";
            }
        }
    }
}