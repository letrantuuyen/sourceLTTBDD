package com.example.anhki.tradingbook.View;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhki.tradingbook.Model.UserModel;
import com.example.anhki.tradingbook.R;
import com.example.anhki.tradingbook.Adapter.ViewPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    Button btnSearch;
    ImageButton btnCart;
    public static TextView tvCart;
    public static boolean logout = false;

    private int[] tabIcons = {
            R.drawable.ic_baseline_home_white_18dp,
            R.drawable.ic_baseline_home_black_18dp,
            R.drawable.ic_baseline_face_white_18dp,
            R.drawable.ic_baseline_face_black_18dp,
            R.drawable.ic_outline_more_horiz_white_18dp,
            R.drawable.ic_outline_more_horiz_black_18dp
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        tvCart = (TextView) findViewById(R.id.tvCart);
        btnCart = (ImageButton) findViewById(R.id.btnCart);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        if (SlashScreenActivity.countCart < 0)
            tvCart.setText(String.valueOf(0));
        else
            tvCart.setText(String.valueOf(SlashScreenActivity.countCart));

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            switch (i){
                case 0:
                    tabLayout.getTabAt(i).setIcon(tabIcons[0]);
                    break;
                case 1:
                    tabLayout.getTabAt(i).setIcon(tabIcons[3]);
                    break;
                case 2:
                    tabLayout.getTabAt(i).setIcon(tabIcons[5]);
                    break;
            }
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    if (tabLayout.getTabAt(i).isSelected())
                    {
                        switch (i){
                            case 0:
                                tabLayout.getTabAt(i).setIcon(tabIcons[0]);
                                break;
                            case 1:
                                tabLayout.getTabAt(i).setIcon(tabIcons[2]);
                                break;
                            case 2:
                                tabLayout.getTabAt(i).setIcon(tabIcons[4]);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    if (tabLayout.getTabAt(i).isSelected() == false)
                    {
                        switch (i){
                            case 0:
                                tabLayout.getTabAt(i).setIcon(tabIcons[1]);
                                break;
                            case 1:
                                tabLayout.getTabAt(i).setIcon(tabIcons[3]);
                                break;
                            case 2:
                                tabLayout.getTabAt(i).setIcon(tabIcons[5]);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    if (tabLayout.getTabAt(i).isSelected())
                    {
                        switch (i){
                            case 0:
                                tabLayout.getTabAt(i).setIcon(tabIcons[0]);
                                break;
                            case 1:
                                tabLayout.getTabAt(i).setIcon(tabIcons[2]);
                                break;
                            case 2:
                                tabLayout.getTabAt(i).setIcon(tabIcons[4]);
                                break;
                        }
                    }
                }
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn có muốn đăng xuất không?");
                builder.setCancelable(false);
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                        FirebaseAuth.getInstance().signOut();
                        SlashScreenActivity.SUM_PRICE = 0;
                        SlashScreenActivity.countCart = 0;
                        SlashScreenActivity.countSelling = 0;
                        SlashScreenActivity.countSold = 0;
                        SlashScreenActivity.countWaiting = 0;
                        SlashScreenActivity.countWaitingAdmin = 0;
                        finish();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return true;
            case R.id.menuCreate:
                Intent intent1 = new Intent(HomeActivity.this, ProductCreate.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
