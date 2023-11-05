package com.example.anhki.tradingbook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhki.tradingbook.Model.ProductModel;
import com.example.anhki.tradingbook.R;
import com.example.anhki.tradingbook.View.Fragment.Image.FragmentImage1;
import com.example.anhki.tradingbook.View.LoginActivity;
import com.example.anhki.tradingbook.View.ProductDetailActivity;
import com.example.anhki.tradingbook.View.SlashScreenActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class AdapterRecyclerInvoicePro extends RecyclerView.Adapter<AdapterRecyclerInvoicePro.ViewHolder> {

    List<ProductModel> productModelList;
    Context context;

    public AdapterRecyclerInvoicePro(Context context, List<ProductModel> productModelList){
        this.productModelList = productModelList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitleProduct, tvPriceProduct, txtNameUser;
        ImageView imProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitleProduct = (TextView) itemView.findViewById(R.id.tvTitleProduct);
            tvPriceProduct = (TextView) itemView.findViewById(R.id.tvPriceProduct);
            imProduct = (ImageView) itemView.findViewById(R.id.imProduct);
            txtNameUser = (TextView) itemView.findViewById(R.id.txtNameUser);
        }
    }

    @NonNull
    @Override
    public AdapterRecyclerInvoicePro.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recyclerview_proinvoice, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        ProductModel productModel = productModelList.get(i);
        viewHolder.tvTitleProduct.setText(productModel.getNameproduct());
        viewHolder.tvPriceProduct.setText(String.valueOf(productModel.getPrice())+".000VNĐ");
        viewHolder.txtNameUser.setText(productModel.getNameUser());

        StorageReference storageImgProductType = FirebaseStorage.getInstance().getReference().child("Product").child(productModel.getImageProduct().get(0));
        long ONE_MEGABYTE = 1024 * 1024;
        storageImgProductType.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                viewHolder.imProduct.setImageBitmap(bitmap);
            }
        });

    }


    @Override
    public int getItemCount() {
        return productModelList.size();
    }
}