package com.example.anhki.tradingbook.View.Fragment.User;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anhki.tradingbook.Controller.ProductController;
import com.example.anhki.tradingbook.Model.CountModel;
import com.example.anhki.tradingbook.R;
import com.example.anhki.tradingbook.View.LoginActivity;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Sold extends Fragment {

    ProductController productController;
    RecyclerView recyclerProduct;

    public static Sold newInstance(int i) {
        return new Sold();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_user_sold, container, false);
        recyclerProduct = (RecyclerView) view.findViewById(R.id.recyclerSold);

        productController = new ProductController(getApplicationContext());
        productController.getDanhSachSanPhamDaBanController(LoginActivity.Current_IdUser,recyclerProduct);

        return view;
    }
}
