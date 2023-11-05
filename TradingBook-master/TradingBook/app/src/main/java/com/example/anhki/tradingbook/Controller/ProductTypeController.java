package com.example.anhki.tradingbook.Controller;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.anhki.tradingbook.Adapter.AdapterRecyclerProductType;
import com.example.anhki.tradingbook.Controller.Interface.ProductTypeInterface;
import com.example.anhki.tradingbook.Model.ProductTypeModel;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeController {
    ProductTypeModel productTypeModel;
    Context context;
    AdapterRecyclerProductType adapterRecyclerProductType;

    public ProductTypeController(Context context){
        this.context = context;
        productTypeModel = new ProductTypeModel();
    }

    public void getDanhSachLoaiSanPhamController(RecyclerView recyclerProductType){
        final List<ProductTypeModel> productTypeModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2);
        recyclerProductType.setLayoutManager(layoutManager);
        recyclerProductType.setLayoutManager(new GridLayoutManager(context,2));
        adapterRecyclerProductType = new AdapterRecyclerProductType(context, productTypeModelList);
        recyclerProductType.setAdapter(adapterRecyclerProductType);

        ProductTypeInterface productTypeInterface = new ProductTypeInterface() {
            @Override
            public void getDanhSachLoaiSanPhamModel(ProductTypeModel productTypeModel) {
                productTypeModelList.add(productTypeModel);
                adapterRecyclerProductType.notifyDataSetChanged();
            }
        };

        productTypeModel.getDanhSachLoaiSanPham(productTypeInterface);
    }
}
