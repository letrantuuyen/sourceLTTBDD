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

import com.example.anhki.tradingbook.Model.InvoiceModel;
import com.example.anhki.tradingbook.Model.ProductModel;
import com.example.anhki.tradingbook.R;
import com.example.anhki.tradingbook.View.DetailInvoice;
import com.example.anhki.tradingbook.View.Fragment.Admin.Invoice;
import com.example.anhki.tradingbook.View.Fragment.Image.FragmentImage1;
import com.example.anhki.tradingbook.View.LoginActivity;
import com.example.anhki.tradingbook.View.ProductDetailActivity;
import com.example.anhki.tradingbook.View.SlashScreenActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class AdapterRecyclerInvoice extends RecyclerView.Adapter<AdapterRecyclerInvoice.ViewHolder> {

    List<InvoiceModel> invoiceModelList;
    Context context;

    public AdapterRecyclerInvoice(Context context, List<InvoiceModel> invoiceModelList){
        this.invoiceModelList = invoiceModelList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUser,tvDate, tvTotal,tvIdInvoice;
        LinearLayout lnitem_custominvoice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = (TextView) itemView.findViewById(R.id.tvUser);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvTotal = (TextView) itemView.findViewById(R.id.tvTotal);
            tvIdInvoice = (TextView) itemView.findViewById(R.id.tvIdInvoice);
            lnitem_custominvoice = (LinearLayout) itemView.findViewById(R.id.lnitem_custominvoice);

            tvIdInvoice.setVisibility(View.INVISIBLE);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recyclerview_invoice, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.lnitem_custominvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailInvoice.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", viewHolder.tvIdInvoice.getText().toString());

                intent.putExtra("bundle",bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        InvoiceModel invoiceModel = invoiceModelList.get(i);
        viewHolder.tvUser.setText(invoiceModel.getNameUser());
        viewHolder.tvDate.setText(invoiceModel.getDate());
        viewHolder.tvTotal.setText(String.valueOf(invoiceModel.getTotal())+".000 VNĐ");
        viewHolder.tvIdInvoice.setText(invoiceModel.getIdInvoice());

    }


    @Override
    public int getItemCount() {
        return invoiceModelList.size();
    }
}