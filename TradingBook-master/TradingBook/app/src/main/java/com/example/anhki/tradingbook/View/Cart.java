package com.example.anhki.tradingbook.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.anhki.tradingbook.Adapter.AdapterRecyclerProductCart;
import com.example.anhki.tradingbook.Adapter.AdapterRecyclerProductType;
import com.example.anhki.tradingbook.Controller.ProductController;
import com.example.anhki.tradingbook.R;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerProduct;
    ProductController productController;
    ImageButton btnBack;
    public static  ImageButton btnNext;
    public static TextView tvSumPrice,tvNotice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cart);

        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        recyclerProduct = (RecyclerView) findViewById(R.id.recyclerCart);
        tvSumPrice = (TextView) findViewById(R.id.tvSumPrice);
        tvNotice = (TextView) findViewById(R.id.tvNotice);
        tvNotice.setVisibility(View.INVISIBLE);

        productController = new ProductController(this);
        productController.getDanhSachSanPhamCartController(LoginActivity.Current_IdUser, recyclerProduct);

        if (SlashScreenActivity.SUM_PRICE == 0) {
            tvSumPrice.setText(String.valueOf(SlashScreenActivity.SUM_PRICE));
            btnNext.setVisibility(View.INVISIBLE);
            tvNotice.setVisibility(View.VISIBLE);
        }
        else
            tvSumPrice.setText(String.valueOf(SlashScreenActivity.SUM_PRICE)+".000VNĐ");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Confirm_Info.class);
                startActivity(intent);
            }
        });
    }
}
