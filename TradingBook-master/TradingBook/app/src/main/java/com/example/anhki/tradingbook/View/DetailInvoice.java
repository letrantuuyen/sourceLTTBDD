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

import com.example.anhki.tradingbook.Controller.ProductController;
import com.example.anhki.tradingbook.R;

public class DetailInvoice extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnBack, btnCart;
    Button btnSearch;
    Bundle bundle;
    String id;
    TextView tvCart;

    ProductController productController;
    RecyclerView recyclerProduct;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail_invoice);

        tvCart = (TextView) findViewById(R.id.tvCart);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnCart = (ImageButton) findViewById(R.id.btnCart);

        tvCart.setText(String.valueOf(SlashScreenActivity.countCart));
        btnBack.setOnClickListener(this);

        btnSearch.setOnClickListener(this);

        btnCart.setOnClickListener(this);

        if(getIntent().hasExtra("bundle")) {
            bundle = getIntent().getBundleExtra("bundle");
            id = bundle.getString("id");
            Log.d("IDINVOICE",id);
        }


        recyclerProduct = (RecyclerView) findViewById(R.id.recyclerInvoice);
        productController = new ProductController(getApplicationContext());
        productController.getDanhSachSanPhamHoaDonController(id,recyclerProduct);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnSearch:
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.btnCart:
                Intent intent2 = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent2);
                break;
        }
    }
}
