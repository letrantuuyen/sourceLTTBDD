package com.example.anhki.tradingbook.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhki.tradingbook.Controller.UserController;
import com.example.anhki.tradingbook.Model.InvoiceModel;
import com.example.anhki.tradingbook.Model.UserModel;
import com.example.anhki.tradingbook.R;
import com.facebook.login.Login;

import java.util.Calendar;

public class Confirm_Info extends AppCompatActivity {

    TextView tvName, tvPhone, tvPoint, tvAddress, tvSumPrice, tvPointBonus;
    EditText teName, tePhone, teAddress;
    Button btnConfirm, btnEdit, btnSave;
    ImageView imgView;
    ImageButton btnBack;
    long sum;
    UserController userController;
    UserModel userModel = new UserModel();
    InvoiceModel invoiceModel = new InvoiceModel();
    Calendar calendar = Calendar.getInstance();

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_confirm_info);

        tvName = (TextView) findViewById(R.id.tvName);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvPoint = (TextView) findViewById(R.id.tvPoint);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvSumPrice = (TextView) findViewById(R.id.tvSumPrice);
        tvPointBonus = (TextView) findViewById(R.id.tvPointBonus);

        teName = (EditText) findViewById(R.id.teName);
        tePhone = (EditText) findViewById(R.id.tePhone);
        teAddress = (EditText) findViewById(R.id.teAddress);

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnBack = (ImageButton) findViewById(R.id.btnBack);

        imgView = (ImageView) findViewById(R.id.imgView);
        imgView.setVisibility(View.INVISIBLE);
        teName.setVisibility(View.INVISIBLE);
        tePhone.setVisibility(View.INVISIBLE);
        teAddress.setVisibility(View.INVISIBLE);
        btnSave.setVisibility(View.INVISIBLE);

        tvSumPrice.setText(String.valueOf(SlashScreenActivity.SUM_PRICE)+".000 VNĐ");
        sum =  SlashScreenActivity.SUM_PRICE / 100;
        sum = sum * 10;
        tvPointBonus.setText(String.valueOf(sum));

        userController = new UserController(getApplicationContext());
        userController.getUserController(LoginActivity.Current_IdUser, tvName,tvPhone, tvAddress,tvPoint, imgView);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teName.setVisibility(View.VISIBLE);
                tePhone.setVisibility(View.VISIBLE);
                teAddress.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.VISIBLE);

                teName.setText(tvName.getText());
                tePhone.setText(tvPhone.getText());
                teAddress.setText(tvAddress.getText());

                tvName.setVisibility(View.INVISIBLE);
                tvPhone.setVisibility(View.INVISIBLE);
                tvAddress.setVisibility(View.INVISIBLE);
                btnConfirm.setVisibility(View.INVISIBLE);
                btnEdit.setVisibility(View.INVISIBLE);

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teName.setVisibility(View.INVISIBLE);
                tePhone.setVisibility(View.INVISIBLE);
                teAddress.setVisibility(View.INVISIBLE);
                btnSave.setVisibility(View.INVISIBLE);

                tvName.setText(teName.getText());
                tvPhone.setText(tePhone.getText());
                tvAddress.setText(teAddress.getText());

                tvName.setVisibility(View.VISIBLE);
                tvPhone.setVisibility(View.VISIBLE);
                tvAddress.setVisibility(View.VISIBLE);
                btnConfirm.setVisibility(View.VISIBLE);
                btnEdit.setVisibility(View.VISIBLE);

                userModel.updateUser(LoginActivity.Current_IdUser, teName.getText().toString(), tvPhone.getText().toString(),
                        tvAddress.getText().toString());
                Toast.makeText(getApplicationContext(), "Thông tin đã cập nhật!", Toast.LENGTH_LONG).show();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String date = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
                invoiceModel.createInvoice(date, LoginActivity.Current_IdUser, sum,false,SlashScreenActivity.SUM_PRICE);
                Toast.makeText(getApplicationContext(), "Hoàn tất đơn hàng!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Thankyou.class );
                startActivity(intent);
            }
        });
    }
}
