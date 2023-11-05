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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhki.tradingbook.Model.ProductModel;
import com.example.anhki.tradingbook.R;
import com.example.anhki.tradingbook.View.Fragment.Image.FragmentImage1;
import com.example.anhki.tradingbook.View.ProductDetailActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class AdapterRecyclerProduct extends RecyclerView.Adapter<AdapterRecyclerProduct.ViewHolder> {

    List<ProductModel> productModelList;
    Context context;

    public AdapterRecyclerProduct(Context context, List<ProductModel> productModelList){
        this.productModelList = productModelList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitleProduct, tvPriceProduct, txtState, txtDescription,txtNameUser, txtAddress, txtId, txtIdUser,txtPriceTemp,
                txtChecked, txtType,txtBought,txtimg1, txtimg2, txtimg3, txtimg;
        ImageView imProduct;
        LinearLayout lncustom_product;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitleProduct = (TextView) itemView.findViewById(R.id.tvTitleProduct);
            tvPriceProduct = (TextView) itemView.findViewById(R.id.tvPriceProduct);
            txtState = (TextView) itemView.findViewById(R.id.txtStateC);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescriptionC);
            imProduct = (ImageView) itemView.findViewById(R.id.imProduct);
            lncustom_product = (LinearLayout) itemView.findViewById(R.id.lnitem_customproduct);
            txtNameUser = (TextView) itemView.findViewById(R.id.txtNameUser);
            txtAddress = (TextView) itemView.findViewById(R.id.txtAddress1);
            txtIdUser = (TextView) itemView.findViewById(R.id.txtIdUser);
            txtPriceTemp = (TextView) itemView.findViewById(R.id.txtPricetemp);
            txtId = (TextView) itemView.findViewById(R.id.txtId);
            txtChecked = (TextView) itemView.findViewById(R.id.txtChecked);
            txtType = (TextView) itemView.findViewById(R.id.txtType);
            txtBought = (TextView) itemView.findViewById(R.id.txtBought);
            txtimg1 = (TextView) itemView.findViewById(R.id.txtimg1);
            txtimg2 = (TextView) itemView.findViewById(R.id.txtimg2);
            txtimg3 = (TextView) itemView.findViewById(R.id.txtimg3);
            txtimg = (TextView) itemView.findViewById(R.id.txtimg);

            txtNameUser.setVisibility(View.INVISIBLE);
            txtAddress.setVisibility(View.INVISIBLE);
            txtState.setVisibility(View.INVISIBLE);
            txtDescription.setVisibility(View.INVISIBLE);
            txtIdUser.setVisibility(View.INVISIBLE);
            txtId.setVisibility(View.INVISIBLE);
            txtPriceTemp.setVisibility(View.INVISIBLE);
            txtChecked.setVisibility(View.INVISIBLE);
            txtType.setVisibility(View.INVISIBLE);
            txtBought.setVisibility(View.INVISIBLE);
            txtimg1.setVisibility(View.INVISIBLE);
            txtimg2.setVisibility(View.INVISIBLE);
            txtimg3.setVisibility(View.INVISIBLE);
            txtimg.setVisibility(View.INVISIBLE);
        }
    }

    @NonNull
    @Override
    public AdapterRecyclerProduct.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recyclerview_product, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.lncustom_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ProductDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", viewHolder.tvTitleProduct.getText().toString());
                bundle.putString("description",  viewHolder.txtDescription.getText().toString());
                bundle.putString("price", viewHolder.txtPriceTemp.getText().toString());
                bundle.putString("state", viewHolder.txtState.getText().toString());
                bundle.putString("id", viewHolder.txtId.getText().toString());
                Log.d("txt", viewHolder.txtState.getText().toString());
                bundle.putString("user", viewHolder.txtNameUser.getText().toString());
                bundle.putString("address", viewHolder.txtAddress.getText().toString());
                bundle.putString("iduser", viewHolder.txtIdUser.getText().toString());
                bundle.putString("type", viewHolder.txtType.getText().toString());
                bundle.putString("bought", viewHolder.txtBought.getText().toString());
                bundle.putString("checked", viewHolder.txtChecked.getText().toString());
                bundle.putString("picture1", viewHolder.txtimg.getText().toString());
                bundle.putString("picture2", viewHolder.txtimg1.getText().toString());
                bundle.putString("picture3", viewHolder.txtimg2.getText().toString());
                bundle.putString("picture4", viewHolder.txtimg3.getText().toString());
                intent.putExtra("bundle",bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerProduct.ViewHolder viewHolder, int i) {
        ProductModel productModel = productModelList.get(i);
        viewHolder.tvTitleProduct.setText(productModel.getNameproduct());
        viewHolder.tvPriceProduct.setText(String.valueOf(productModel.getPrice())+".000VNĐ");
        viewHolder.txtState.setText(productModel.getState());
        viewHolder.txtDescription.setText(productModel.getDescription());
        viewHolder.txtNameUser.setText(productModel.getNameUser());
        viewHolder.txtAddress.setText(productModel.getAddress());
        viewHolder.txtId.setText(productModel.getIdProduct());
        viewHolder.txtIdUser.setText(productModel.getIdAccount());
        viewHolder.txtPriceTemp.setText(String.valueOf(productModel.getPrice()));
        viewHolder.txtChecked.setText(String.valueOf(productModel.isChecked()));
        viewHolder.txtType.setText(String.valueOf(productModel.getProducttype()));
        viewHolder.txtBought.setText(String.valueOf(productModel.isBought()));
        viewHolder.txtimg.setText(productModel.getImageProduct().get(0));
        viewHolder.txtimg1.setText(productModel.getImageProduct().get(1));
        viewHolder.txtimg2.setText(productModel.getImageProduct().get(2));
        viewHolder.txtimg3.setText(productModel.getImageProduct().get(3));

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