package com.example.anhki.tradingbook.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhki.tradingbook.Adapter.ViewPagerAdapterImage;
import com.example.anhki.tradingbook.Model.ProductModel;
import com.example.anhki.tradingbook.R;
import com.example.anhki.tradingbook.View.Fragment.Image.FragmentImage1;
import com.example.anhki.tradingbook.View.Fragment.Image.FragmentImage2;
import com.example.anhki.tradingbook.View.Fragment.Image.FragmentImage3;
import com.example.anhki.tradingbook.View.Fragment.Image.FragmentImage4;
import com.example.anhki.tradingbook.View.Fragment.InfoUser.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    String idProduct;
    TextView txtNameProduct, txtPrice, txtDescription, txtState,txtUser, txtAddress, tvCart;
    Bundle bundle;
    ImageButton btnBack, btnCart;
    Button btnSearch, btnDelete, btnBuy, btnChecked;
    ViewPager viewPager;
    List<Fragment> fragmentList;
    ProductModel productModel = new ProductModel();
    LinearLayout layoutDot;
    TextView[] txtDots;
    String idUser,checked,type, bought;
    public static String IdProductCartLast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail_product);

        layoutDot = (LinearLayout) findViewById(R.id.layoutdot);

        txtNameProduct = (TextView) findViewById(R.id.txtNameProduct);
        txtPrice = (TextView) findViewById(R.id.txtPrice);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtState = (TextView) findViewById(R.id.txtState);
        txtUser =(TextView) findViewById(R.id.txtNamePost);
        txtAddress = (TextView) findViewById(R.id.txtAddressPost);
        tvCart = (TextView) findViewById(R.id.tvCart);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnCart = (ImageButton) findViewById(R.id.btnCart);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnBuy = (Button) findViewById(R.id.btnBuy);
        btnChecked = (Button) findViewById(R.id.btnChecked);
        viewPager = (ViewPager) findViewById(R.id.viewpagerimage);

        tvCart.setText(String.valueOf(SlashScreenActivity.countCart));

        btnBack.setOnClickListener(this);

        btnSearch.setOnClickListener(this);

        btnChecked.setOnClickListener(this);

        btnBuy.setOnClickListener(this);

        btnCart.setOnClickListener(this);

        txtUser.setOnClickListener(this);

        if(getIntent().hasExtra("bundle")) {
            bundle = getIntent().getBundleExtra("bundle");
            int stt = bundle.getInt("stt");
            txtPrice.setText(bundle.getString("price"));
            txtNameProduct.setText(bundle.getString("name"));
            txtDescription.setText(bundle.getString("description"));
            txtState.setText(bundle.getString("state"));
            txtUser.setText(bundle.getString("user"));
            txtAddress.setText(bundle.getString("address"));
            idUser = bundle.getString("iduser");
            idProduct = bundle.getString("id");
            checked = bundle.getString("checked");
            type = bundle.getString("type");
            bought = bundle.getString("bought");
        }
        btnBuy.setVisibility(View.INVISIBLE);
        btnDelete.setVisibility(View.INVISIBLE);
        btnChecked.setVisibility(View.INVISIBLE);

        if (bought.equals("false")){
            if (idUser.equals(LoginActivity.Current_IdUser)){
                btnDelete.setVisibility(View.VISIBLE);
            } else
            if (checked.equals("true")){
                btnBuy.setVisibility(View.VISIBLE);
            } else
                btnChecked.setVisibility(View.VISIBLE);
        }

        if (LoginActivity.Current_IdUser.equals(idUser)){
            btnBuy.setVisibility(View.INVISIBLE);
        } else {
            btnDelete.setVisibility(View.INVISIBLE);
        }

        fragmentList = new ArrayList<>();
        FragmentImage1 fragmentImage1 = new FragmentImage1();
        fragmentList.add(fragmentImage1);
        FragmentImage2 fragmentImage2 = new FragmentImage2();
        fragmentList.add(fragmentImage2);
        FragmentImage3 fragmentImage3 = new FragmentImage3();
        fragmentList.add(fragmentImage3);
        FragmentImage4 fragmentImage4 = new FragmentImage4();
        fragmentList.add(fragmentImage4);

        ViewPagerAdapterImage adapterViewPagerSlider = new ViewPagerAdapterImage(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapterViewPagerSlider);
        adapterViewPagerSlider.notifyDataSetChanged();

        ThemDotSlider(0);
        viewPager.addOnPageChangeListener(this);
    }

    private void ThemDotSlider(int vitrihientai){
        txtDots = new TextView[fragmentList.size()];

        layoutDot.removeAllViews();
        for (int i=0 ; i<fragmentList.size(); i++){
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));
            txtDots[i].setTextSize(50);
            txtDots[i].setTextColor(getIdColor(R.color.colorGray));

            layoutDot.addView(txtDots[i]);
        }
        txtDots[vitrihientai].setTextColor(getIdColor(R.color.colorGreen));
    }

    private int getIdColor(int idcolor){
        int color =0;
        if(Build.VERSION.SDK_INT > 21){
            color = ContextCompat.getColor(this,idcolor);
        }else{
            color = getResources().getColor(idcolor);
        }
        return color;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        ThemDotSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnChecked:
                showAlertDialog();
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnSearch:
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.txtNamePost:
                Intent intent1 = new Intent(getApplicationContext(), InfoUser.class);
                intent1.putExtra("activity", "product");
                intent1.putExtra("iduser", idUser);
                startActivity(intent1);

                break;
            case R.id.btnBuy:
                ProductModel productModel = new ProductModel();
                productModel.addCart(idProduct,LoginActivity.Current_IdUser, type, txtUser.getText().toString());
                SlashScreenActivity.SUM_PRICE += Long.valueOf(txtPrice.getText().toString());
                SlashScreenActivity.countCart++;
                Log.d("SumCartt",String.valueOf(SlashScreenActivity.SUM_PRICE));
                IdProductCartLast = idProduct;
                Toast.makeText(getApplicationContext(),"Thêm vào giỏ hàng thành công",Toast.LENGTH_LONG).show();
                btnBuy.setVisibility(View.INVISIBLE);
                tvCart.setText(String.valueOf(SlashScreenActivity.countCart));
                HomeActivity.tvCart.setText(String.valueOf(SlashScreenActivity.countCart));
                ProductActivity.tvCart.setText(String.valueOf(SlashScreenActivity.countCart));
                break;
            case R.id.btnDelete:
                break;
            case R.id.btnCart:
                Intent intent2 = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent2);
                break;
        }
    }

    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có muốn duyệt sản phẩm này không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                productModel.updateSanPhamChecked(idProduct,idUser,type);
                Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_LONG).show();
                productModel.readDataWaitingAdmin(new ProductModel.FireBaseCallback() {
                    @Override
                    public void onCallback(long count) {
                        SlashScreenActivity.countWaitingAdmin = count;
                    }
                });
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
    }
}
