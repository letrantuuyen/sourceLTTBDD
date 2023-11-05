package com.example.anhki.tradingbook.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhki.tradingbook.Adapter.ViewPagerAdapterInfoUser;
import com.example.anhki.tradingbook.Controller.UserController;
import com.example.anhki.tradingbook.Model.CommentModel;
import com.example.anhki.tradingbook.R;

import java.util.Calendar;

public class InfoUser extends AppCompatActivity {
    TabLayout tabLayout;
    EditText txtReason;
    ViewPager viewPager;
    TextView txtName, txtAddress, txtPhone, tvCart, tvPoint;
    ImageButton btnBack, btnCart;
    Button btnReport;
    RelativeLayout relativeLayout;
    ImageView imgUser;
    Bitmap bitmap = null;
    String path;
    Calendar calendar = Calendar.getInstance();
    float star;
    CommentModel commentModel = new CommentModel();
    UserController userController;
    String idUser;
    RatingBar rating;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info_user);

        tabLayout = (TabLayout) findViewById(R.id.tablayoutuser);
        txtReason =  (EditText) findViewById(R.id.txtReason);
        viewPager = (ViewPager) findViewById(R.id.viewpageruser);
        txtName = (TextView) findViewById(R.id.txtUserName);
        tvPoint = (TextView) findViewById(R.id.tvPoint);
        txtAddress = (TextView) findViewById(R.id.txtUserAddress);
        txtPhone = (TextView) findViewById(R.id.txtUserPhone);
        tvCart = (TextView) findViewById(R.id.tvCart);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnCart = (ImageButton) findViewById(R.id.btnCart);
        btnReport = (Button) findViewById(R.id.btnReport);
        relativeLayout = (RelativeLayout) findViewById((R.id.relativelayout));
        imgUser = (ImageView) findViewById(R.id.imgUser);
        rating = (RatingBar) findViewById(R.id.rating);

        tvPoint.setVisibility(View.INVISIBLE);

        ViewPagerAdapterInfoUser adapter = new ViewPagerAdapterInfoUser(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tvCart.setText(String.valueOf(SlashScreenActivity.countCart));

        txtPhone = (TextView) findViewById(R.id.txtUserPhone);
        Intent intent= getIntent();
        if (intent.getStringExtra("activity").equals("product")) {
            userController = new UserController(getApplicationContext());
            userController.getUserController(intent.getStringExtra("iduser"), txtName,txtPhone, txtAddress,tvPoint, imgUser);
            idUser = intent.getStringExtra("iduser");
        }
        else {
            txtName.setText(intent.getStringExtra("name"));
            txtAddress.setText(intent.getStringExtra("address"));
            txtPhone.setText(intent.getStringExtra("phone"));
            byte[] byteArray = intent.getByteArrayExtra("image");
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imgUser.setImageBitmap(bmp);
            idUser = LoginActivity.Current_IdUser;
        }
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                star = rating;
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH)+1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String date = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
                commentModel.createComment(idUser,txtReason.getText().toString(),date, LoginActivity.Current_IdUser, star);
                Toast.makeText(getApplicationContext(), "Nhận xét và đánh giá thành công!",Toast.LENGTH_LONG).show();
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

    public String getMyData() {
        return idUser;
    }
}
