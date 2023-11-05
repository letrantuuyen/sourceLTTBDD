package com.example.anhki.tradingbook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhki.tradingbook.Model.ProductTypeModel;
import com.example.anhki.tradingbook.R;
import com.example.anhki.tradingbook.View.ProductActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterRecyclerProductType extends RecyclerView.Adapter<AdapterRecyclerProductType.ViewHolder> {

    List<ProductTypeModel> productTypeModelList;
    Context context;
    public static boolean clickItem;
    public static String nameProductType;

    public AdapterRecyclerProductType(Context context, List<ProductTypeModel> productTypeModelList){
        this.productTypeModelList = productTypeModelList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitleProductType, tvIdProducttype;
        ImageView imageView;
        LinearLayout lnitem_customproducttype;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitleProductType = (TextView) itemView.findViewById(R.id.tvTitleProductType);
            tvIdProducttype = (TextView) itemView.findViewById(R.id.tvIdProducttype);
            imageView = (ImageView) itemView.findViewById(R.id.imProductType);
            lnitem_customproducttype = (LinearLayout) itemView.findViewById(R.id.lnitem_customproducttype);

            tvIdProducttype.setVisibility(View.INVISIBLE);
        }
    }

    @NonNull
    @Override
    public AdapterRecyclerProductType.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recyclerview_producttype, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.lnitem_customproducttype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItem = true;

                nameProductType = String.valueOf(viewHolder.tvIdProducttype.getText());

                Intent intent = new Intent(context, ProductActivity.class);
                context.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerProductType.ViewHolder viewHolder,int i) {
        ProductTypeModel productTypeModel = productTypeModelList.get(i);
        viewHolder.tvTitleProductType.setText(productTypeModel.getNameProductType());
        viewHolder.tvIdProducttype.setText(productTypeModel.getIdProductType());

        StorageReference storageImgProductType = FirebaseStorage.getInstance().getReference().child("ProductType").child(productTypeModel.getImageProductType().get(0));
        long ONE_MEGABYTE = 1024 * 1024;
        storageImgProductType.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                viewHolder.imageView.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productTypeModelList.size();
    }

}
