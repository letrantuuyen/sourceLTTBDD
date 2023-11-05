package com.example.anhki.tradingbook.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.anhki.tradingbook.Model.ProductModel;
import com.example.anhki.tradingbook.Model.UserModel;
import com.example.anhki.tradingbook.R;

import java.util.List;

public class Thankyou extends AppCompatActivity {
    Button btnHome;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thankyou);

        progressDialog = new ProgressDialog(this);
        btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                SlashScreenActivity.SUM_PRICE = 0;
                SlashScreenActivity.countCart = 0;
            }
        });
    }
}
