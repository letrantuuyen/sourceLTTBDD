package com.example.anhki.tradingbook.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.anhki.tradingbook.Model.ProductModel;
import com.example.anhki.tradingbook.Model.UserModel;
import com.example.anhki.tradingbook.View.Fragment.FragmentUser;
import com.example.anhki.tradingbook.View.Fragment.FragmentAdmin;
import com.example.anhki.tradingbook.View.Fragment.FragmentProduct;
import com.example.anhki.tradingbook.View.LoginActivity;
import com.example.anhki.tradingbook.View.SlashScreenActivity;
import com.facebook.login.Login;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (SlashScreenActivity.ROLE != null){
            if (SlashScreenActivity.ROLE.equals("Admin"))
                switch (position) {
                    case 0:
                        return FragmentProduct.newInstance();
                    case 1:
                        return new FragmentUser();
                    default:
                        return new FragmentAdmin();
                }
            else
                switch (position) {
                    case 0:
                        return FragmentProduct.newInstance();
                    default:
                        return new FragmentUser();
                }
        } else
            if (SlashScreenActivity.Flag != null) {
                UserModel userModel = new UserModel();
                userModel.createUser(LoginActivity.Current_IdUser, LoginActivity.Current_Email);
                ProductModel productModel = new ProductModel();
                productModel.createCart(LoginActivity.Current_IdUser);
                switch (position) {
                    case 0:
                        return FragmentProduct.newInstance();
                    default:
                        return new FragmentUser();
                }
            }
            else
                switch (position) {
                    case 0:
                        return FragmentProduct.newInstance();
                    default:
                        return new FragmentUser();
                }

    }

    @Override
    public int getCount() {
        if (SlashScreenActivity.ROLE != null) {
            if (SlashScreenActivity.ROLE.equals("Admin"))
                return 3;
            else
                return 2;
        }
        else
            return 2;

    }
}
