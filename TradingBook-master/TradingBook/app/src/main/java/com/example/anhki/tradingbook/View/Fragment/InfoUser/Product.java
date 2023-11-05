package com.example.anhki.tradingbook.View.Fragment.InfoUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anhki.tradingbook.Controller.ProductController;
import com.example.anhki.tradingbook.R;
import com.example.anhki.tradingbook.View.InfoUser;
import com.example.anhki.tradingbook.View.LoginActivity;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Product extends Fragment {

    ProductController productController;
    RecyclerView recyclerProduct;
    public static Product newInstance() {
        return new Product();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_info_user_product, container, false);
        recyclerProduct = (RecyclerView) view.findViewById(R.id.recyclerProduct);

        InfoUser activity = (InfoUser) getActivity();
        String idUser = activity.getMyData();
        productController = new ProductController(getApplicationContext());
        productController.getDanhSachSanPhamTheoNguoiBanController(idUser, recyclerProduct);

        return view;
    }
}
