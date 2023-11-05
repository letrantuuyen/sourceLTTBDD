package com.example.anhki.tradingbook.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anhki.tradingbook.Controller.ProductTypeController;
import com.example.anhki.tradingbook.R;

public class FragmentProduct extends Fragment{

    RecyclerView recyclerProductType, recyclerProduct;
    ProductTypeController productTypeController;

    public static FragmentProduct newInstance() {
        return new FragmentProduct();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home_product, container, false);

        recyclerProductType = (RecyclerView) view.findViewById(R.id.recyclerProductType);
        recyclerProduct = (RecyclerView) view.findViewById(R.id.recyclerProductDetail);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        productTypeController = new ProductTypeController(getContext());
        productTypeController.getDanhSachLoaiSanPhamController(recyclerProductType);
    }
}