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
import com.example.anhki.tradingbook.View.Cart;
import com.example.anhki.tradingbook.View.Fragment.Image.FragmentImage1;
import com.example.anhki.tradingbook.View.HomeActivity;
import com.example.anhki.tradingbook.View.LoginActivity;
import com.example.anhki.tradingbook.View.ProductDetailActivity;
import com.example.anhki.tradingbook.View.SlashScreenActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class AdapterRecyclerProductCart extends RecyclerView.Adapter<AdapterRecyclerProductCart.ViewHolder> {

    List<ProductModel> productModelList;
    Context context;

    public AdapterRecyclerProductCart(Context context, List<ProductModel> productModelList){
        this.productModelList = productModelList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitleProduct, tvPriceProduct, txtState, txtDescription,txtNameUser, txtAddress, txtId, txtIdUser,txtPriceTemp,
                txtChecked, txtType, txtBought, tvimg, tvimg1, tvimg2, tvimg3;
        ImageView imProduct;
        LinearLayout lncustom_product;
        ImageButton btnRemove;

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
            txtBought = (TextView) itemView.findViewById(R.id.txtBought);
            tvimg = (TextView) itemView.findViewById(R.id.tvimg);
            tvimg1 = (TextView) itemView.findViewById(R.id.tvimg1);
            tvimg2 = (TextView) itemView.findViewById(R.id.tvimg2);
            tvimg3 = (TextView) itemView.findViewById(R.id.tvimg3);
            txtType = (TextView) itemView.findViewById(R.id.txtType);
            btnRemove = (ImageButton) itemView.findViewById(R.id.btnRemove);

            txtAddress.setVisibility(View.INVISIBLE);
            txtState.setVisibility(View.INVISIBLE);
            txtDescription.setVisibility(View.INVISIBLE);
            txtIdUser.setVisibility(View.INVISIBLE);
            txtId.setVisibility(View.INVISIBLE);
            txtPriceTemp.setVisibility(View.INVISIBLE);
            txtChecked.setVisibility(View.INVISIBLE);
            txtType.setVisibility(View.INVISIBLE);
            txtBought.setVisibility(View.INVISIBLE);
            tvimg.setVisibility(View.INVISIBLE);
            tvimg1.setVisibility(View.INVISIBLE);
            tvimg2.setVisibility(View.INVISIBLE);
            tvimg3.setVisibility(View.INVISIBLE);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recyclerview_cart, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);

//        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ProductModel productModel = new ProductModel();
//                productModel.removeCart(viewHolder.txtId.getText().toString(),LoginActivity.Current_IdUser, ProductDetailActivity.IdProductCartLast, viewHolder.txtType.getText().toString(),viewHolder.txtNameUser.getText().toString());
//                Toast.makeText(context, "Xóa sản phẩm khỏi giỏ hàng thành công!", Toast.LENGTH_LONG).show();
//                SlashScreenActivity.SUM_PRICE -= Long.valueOf(viewHolder.txtPriceTemp.getText().toString());
//                Log.d("SumCart",String.valueOf(SlashScreenActivity.SUM_PRICE));
//            }
//        });

        viewHolder.tvTitleProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", viewHolder.tvTitleProduct.getText().toString());
                bundle.putString("description",  viewHolder.txtDescription.getText().toString());
                bundle.putString("price", viewHolder.txtPriceTemp.getText().toString());
                bundle.putString("state", viewHolder.txtState.getText().toString());
                bundle.putString("id", viewHolder.txtId.getText().toString());
                bundle.putString("user", viewHolder.txtNameUser.getText().toString());
                bundle.putString("address", viewHolder.txtAddress.getText().toString());
                bundle.putString("iduser", viewHolder.txtIdUser.getText().toString());
                bundle.putString("type", viewHolder.txtType.getText().toString());
                bundle.putString("checked", viewHolder.txtChecked.getText().toString());
                bundle.putString("bought", viewHolder.txtBought.getText().toString());
                bundle.putString("picture1", viewHolder.tvimg.getText().toString());
                bundle.putString("picture2", viewHolder.tvimg1.getText().toString());
                bundle.putString("picture3", viewHolder.tvimg2.getText().toString());
                bundle.putString("picture4", viewHolder.tvimg3.getText().toString());
                intent.putExtra("bundle",bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
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
        viewHolder.tvimg.setText(productModel.getImageProduct().get(0));
        viewHolder.tvimg1.setText(productModel.getImageProduct().get(1));
        viewHolder.tvimg2.setText(productModel.getImageProduct().get(2));
        viewHolder.tvimg3.setText(productModel.getImageProduct().get(3));


        StorageReference storageImgProductType = FirebaseStorage.getInstance().getReference().child("Product").child(productModel.getImageProduct().get(0));
        long ONE_MEGABYTE = 1024 * 1024;
        storageImgProductType.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                viewHolder.imProduct.setImageBitmap(bitmap);
            }
        });

        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductModel productModel = new ProductModel();
                productModel.removeCart(viewHolder.txtId.getText().toString(),LoginActivity.Current_IdUser, ProductDetailActivity.IdProductCartLast, viewHolder.txtType.getText().toString(),viewHolder.txtNameUser.getText().toString());
                Toast.makeText(context, "Xóa sản phẩm khỏi giỏ hàng thành công!", Toast.LENGTH_LONG).show();
                SlashScreenActivity.SUM_PRICE -= Long.valueOf(viewHolder.txtPriceTemp.getText().toString());
                SlashScreenActivity.countCart --;
                Log.d("SumCart",String.valueOf(SlashScreenActivity.SUM_PRICE));
                productModelList.remove(i);
                notifyDataSetChanged();
                if (SlashScreenActivity.SUM_PRICE == 0){
                    Cart.tvSumPrice.setText(String.valueOf(SlashScreenActivity.SUM_PRICE));
                    Cart.btnNext.setVisibility(View.INVISIBLE);
                    Cart.tvNotice.setVisibility(View.VISIBLE);
                }
                else
                    Cart.tvSumPrice.setText(String.valueOf(SlashScreenActivity.SUM_PRICE)+".000 VNĐ");
                HomeActivity.tvCart.setText(String.valueOf(SlashScreenActivity.countCart));
            }
        });
    }


    @Override
    public int getItemCount() {
        return productModelList.size();
    }
}