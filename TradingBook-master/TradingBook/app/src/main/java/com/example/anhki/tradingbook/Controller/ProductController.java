package com.example.anhki.tradingbook.Controller;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.anhki.tradingbook.Adapter.AdapterRecyclerInvoice;
import com.example.anhki.tradingbook.Adapter.AdapterRecyclerInvoicePro;
import com.example.anhki.tradingbook.Adapter.AdapterRecyclerProduct;
import com.example.anhki.tradingbook.Adapter.AdapterRecyclerProductCart;
import com.example.anhki.tradingbook.Controller.Interface.ProductChuaDuyetInterface;
import com.example.anhki.tradingbook.Controller.Interface.ProductDaBanInterface;
import com.example.anhki.tradingbook.Controller.Interface.ProductInterface;
import com.example.anhki.tradingbook.Controller.Interface.ProductInvoiceInterface;
import com.example.anhki.tradingbook.Controller.Interface.ProductSearchInterface;
import com.example.anhki.tradingbook.Controller.Interface.ProductTenInterface;
import com.example.anhki.tradingbook.Model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
    ProductModel productModel;
    Context context;
    AdapterRecyclerProduct adapterRecyclerProduct;
    AdapterRecyclerProductCart adapterRecyclerProductCart;
    AdapterRecyclerInvoicePro adapterRecyclerInvoicePro;

    public ProductController(Context context){
        this.context = context;
        productModel = new ProductModel();
    }

    public void getDanhSachSanPhamController(RecyclerView recyclerProduct){
        final List<ProductModel> productModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
        recyclerProduct.setLayoutManager(layoutManager);
        recyclerProduct.setLayoutManager(new GridLayoutManager(context,3));
        adapterRecyclerProduct = new AdapterRecyclerProduct(context, productModelList);
        recyclerProduct.setAdapter(adapterRecyclerProduct);

        ProductInterface productInterface = new ProductInterface() {
            @Override
            public void getDanhSachSanPhamModel(ProductModel productModel) {
                productModelList.add(productModel);
                adapterRecyclerProduct.notifyDataSetChanged();
            }
        };

        productModel.getDanhSachSanPham(productInterface);
    }

    public void getDanhSachSanPhamTheoNguoiBanController(String idUser, RecyclerView recyclerProduct){
        final List<ProductModel> productModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
        recyclerProduct.setLayoutManager(layoutManager);
        recyclerProduct.setLayoutManager(new GridLayoutManager(context,3));
        adapterRecyclerProduct = new AdapterRecyclerProduct(context, productModelList);
        recyclerProduct.setAdapter(adapterRecyclerProduct);

        ProductTenInterface producttenInterface = new ProductTenInterface() {
            @Override
            public void getDanhSachSanPhamTheoTenModel(ProductModel productModel) {
                productModelList.add(productModel);
                adapterRecyclerProduct.notifyDataSetChanged();
            }
        };
        productModel.getDanhSachSanPhamTheoNguoiBan(idUser, producttenInterface);
    }

    public void getDanhSachSanPhamChuaDuyetController(String userId,RecyclerView recyclerProduct){
        final List<ProductModel> productModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
        recyclerProduct.setLayoutManager(layoutManager);
        recyclerProduct.setLayoutManager(new GridLayoutManager(context,3));
        adapterRecyclerProduct = new AdapterRecyclerProduct(context, productModelList);
        recyclerProduct.setAdapter(adapterRecyclerProduct);

        ProductChuaDuyetInterface productChuaDuyetInterface = new ProductChuaDuyetInterface() {
            @Override
            public void getDanhSachSanPhamChuaDuyetModel(ProductModel productModel) {
                productModelList.add(productModel);
                adapterRecyclerProduct.notifyDataSetChanged();

            }
        };
        productModel.getDanhSachSanPhamChuaDuyet(userId,productChuaDuyetInterface);
    }

    public void getDanhSachSanPhamChuaDuyetAdminController(RecyclerView recyclerProduct){
        final List<ProductModel> productModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
        recyclerProduct.setLayoutManager(layoutManager);
        recyclerProduct.setLayoutManager(new GridLayoutManager(context,3));
        adapterRecyclerProduct = new AdapterRecyclerProduct(context, productModelList);
        recyclerProduct.setAdapter(adapterRecyclerProduct);

        ProductChuaDuyetInterface productChuaDuyetInterface = new ProductChuaDuyetInterface() {
            @Override
            public void getDanhSachSanPhamChuaDuyetModel(ProductModel productModel) {
                productModelList.add(productModel);
                adapterRecyclerProduct.notifyDataSetChanged();

            }
        };
        productModel.getDanhSachSanPhamChuaDuyetAdmin(productChuaDuyetInterface);
    }

    public void getDanhSachSanPhamDaBanController(String userId,RecyclerView recyclerProduct){
        final List<ProductModel> productModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
        recyclerProduct.setLayoutManager(layoutManager);
        recyclerProduct.setLayoutManager(new GridLayoutManager(context,3));
        adapterRecyclerProduct = new AdapterRecyclerProduct(context, productModelList);
        recyclerProduct.setAdapter(adapterRecyclerProduct);

        ProductDaBanInterface productDaBanInterface = new ProductDaBanInterface() {
            @Override
            public void getDanhSachSanPhamDaBanModel(ProductModel productModel) {
                productModelList.add(productModel);
                adapterRecyclerProduct.notifyDataSetChanged();
            }
        };
        productModel.getDanhSachSanPhamDaBan(userId,productDaBanInterface);
    }

    public void getDanhSachSanPhamCartController(String idUser, RecyclerView recyclerProduct){
        final List<ProductModel> productModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerProduct.setLayoutManager(layoutManager);
        recyclerProduct.setLayoutManager(new LinearLayoutManager(context));
        adapterRecyclerProductCart = new AdapterRecyclerProductCart(context, productModelList);
        recyclerProduct.setAdapter(adapterRecyclerProductCart);

        ProductInterface productInterface = new ProductInterface() {
            @Override
            public void getDanhSachSanPhamModel(ProductModel productModel) {
                productModelList.add(productModel);
                adapterRecyclerProductCart.notifyDataSetChanged();
            }
        };

        productModel.getDanhSachSanPhamCart(idUser,productInterface);
    }

    public void getDanhSachSanPhamSearchController(RecyclerView recyclerProduct, String s){
        final List<ProductModel> productModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
        recyclerProduct.setLayoutManager(layoutManager);
        recyclerProduct.setLayoutManager(new GridLayoutManager(context,3));
        adapterRecyclerProduct = new AdapterRecyclerProduct(context, productModelList);
        recyclerProduct.setAdapter(adapterRecyclerProduct);

        ProductSearchInterface productSearchInterface = new ProductSearchInterface() {
            @Override
            public void getDanhSachSanPhamSearchModel(ProductModel productModel) {
                productModelList.add(productModel);
                adapterRecyclerProduct.notifyDataSetChanged();
            }
        };
        productModel.getDanhSachSanPhamSearch(productSearchInterface, s);
    }

    public void getDanhSachSanPhamHoaDonController(String idInvoice, RecyclerView recyclerProduct){
        final List<ProductModel> productModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerProduct.setLayoutManager(layoutManager);
        recyclerProduct.setLayoutManager(new LinearLayoutManager(context));
        adapterRecyclerInvoicePro = new AdapterRecyclerInvoicePro(context, productModelList);
        recyclerProduct.setAdapter(adapterRecyclerInvoicePro);

        ProductInvoiceInterface productInvoiceInterface = new ProductInvoiceInterface() {
            @Override
            public void getDanhSachSanPhamHoaDonModel(ProductModel productModel) {
                productModelList.add(productModel);
                adapterRecyclerInvoicePro.notifyDataSetChanged();

            }
        };

        productModel.getDanhSachSanPhamHoaDon(idInvoice,productInvoiceInterface);
    }
}