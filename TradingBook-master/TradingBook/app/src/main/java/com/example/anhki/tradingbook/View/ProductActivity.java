package com.example.anhki.tradingbook.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.anhki.tradingbook.Adapter.AdapterRecyclerProductType;
import com.example.anhki.tradingbook.Controller.ProductController;
import com.example.anhki.tradingbook.R;

public class ProductActivity extends AppCompatActivity {

    ProductController productController;
    ImageButton btnBack, btnCart;
    Button btnSearch;
    RecyclerView recyclerProduct;
    public static TextView tvCart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product_detail);

        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnCart = (ImageButton) findViewById(R.id.btnCart);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        tvCart = (TextView) findViewById(R.id.tvCart);

        recyclerProduct = (RecyclerView) findViewById(R.id.recyclerProductDetail);

        tvCart.setText(String.valueOf(SlashScreenActivity.countCart));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdapterRecyclerProductType.clickItem = false;
                finish();
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        if (AdapterRecyclerProductType.clickItem != false){
            productController = new ProductController(getApplicationContext());
            productController.getDanhSachSanPhamController(recyclerProduct);
        }
    }
}
