package com.example.anhki.tradingbook.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.anhki.tradingbook.Model.ProductModel;
import com.example.anhki.tradingbook.Model.ProductTypeModel;
import com.example.anhki.tradingbook.Model.UserModel;
import com.example.anhki.tradingbook.R;

import java.util.List;

public class SlashScreenActivity extends AppCompatActivity {

    public static long countSelling = 0;
    public static long countWaiting = 0;
    public static long countSold = 0;
    public static long countWaitingAdmin = 0;
    public static long countCart = 0;
    public static String ROLE = "customer";
    public static String Flag;
    public static long SUM_PRICE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_slashscreen);

        ProductModel productModel = new ProductModel();
        productModel.readDataSelling(new ProductModel.FireBaseCallback() {
            @Override
            public void onCallback(long count) {
                countSelling = count;
            }
        });
        productModel.readDataWaiting(LoginActivity.Current_IdUser,new ProductModel.FireBaseCallback() {
            @Override
            public void onCallback(long count) {
                countWaiting = count;
            }
        });
        productModel.readDataSold(new ProductModel.FireBaseCallback() {
            @Override
            public void onCallback(long count) {
                countSold = count;
            }
        });
        productModel.readDataWaitingAdmin(new ProductModel.FireBaseCallback() {
            @Override
            public void onCallback(long count) {
                countWaitingAdmin = count;
            }
        });

        productModel.readDataCart(LoginActivity.Current_IdUser, new ProductModel.FireBaseCallback() {
            @Override
            public void onCallback(long count) {
                countCart = count;
            }
        });

        productModel.getSumCart(LoginActivity.Current_IdUser, new ProductModel.FireBaseCallback() {
            @Override
            public void onCallback(long count) {
                SUM_PRICE = count;
            }
        });

        UserModel userModel = new UserModel();
        userModel.readDataRole(new UserModel.FireBaseCallback() {
            @Override
            public void onCallback(String role) {
                ROLE = role;
            }
        }, LoginActivity.Current_IdUser);

        ProductTypeModel productTypeModel = new ProductTypeModel();
        productTypeModel.readDataFlag(new ProductTypeModel.FireBaseCallback() {
            @Override
            public void onCallback(String flag) {
                Flag = flag;
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {

                } finally {
                    Intent intent = new Intent(SlashScreenActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
        thread.start();

    }
}
