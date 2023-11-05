package com.example.anhki.tradingbook.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.anhki.tradingbook.Controller.ProductController;
import com.example.anhki.tradingbook.R;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView recyclerProductSearch;
    ProductController productController;
    SearchView searchView;
    ImageButton btnBack, btnCart;
    TextView tvCart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timkiem);

        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnCart = (ImageButton) findViewById(R.id.btnCart);
        recyclerProductSearch = (RecyclerView) findViewById(R.id.recyclerSearch);
        searchView = (SearchView) findViewById(R.id.txtSearch);
        tvCart = (TextView) findViewById(R.id.tvCart);

        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorWhite));
        searchEditText.setHintTextColor(getResources().getColor(R.color.colorWhite));
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);

        tvCart.setText(String.valueOf(SlashScreenActivity.countCart));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        productController = new ProductController(getApplicationContext());
        productController.getDanhSachSanPhamSearchController(recyclerProductSearch, s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
