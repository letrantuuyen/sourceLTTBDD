package com.example.anhki.tradingbook.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhki.tradingbook.Model.ProductModel;
import com.example.anhki.tradingbook.Model.ProductTypeModel;
import com.example.anhki.tradingbook.Model.UserModel;
import com.example.anhki.tradingbook.R;
import com.example.anhki.tradingbook.View.ProductActivity;
import com.example.anhki.tradingbook.View.SlashScreenActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterRecyclerAccount extends RecyclerView.Adapter<AdapterRecyclerAccount.ViewHolder> {
    List<UserModel> userModelList;
    Context context;

    public AdapterRecyclerAccount(Context context, List<UserModel> userModelList){
        this.userModelList = userModelList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName,txtRole, txtEmail, txtId;
        ImageView imageView;
        ImageButton btnAdmin;
        LinearLayout lnitem_customaccount;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtNameAcoount);
            txtRole = (TextView) itemView.findViewById(R.id.txtRole);
            txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
            txtId = (TextView) itemView.findViewById(R.id.txtId);
            imageView = (ImageView) itemView.findViewById(R.id.imAva);
            btnAdmin = (ImageButton) itemView.findViewById(R.id.btnAdmin);
            lnitem_customaccount = (LinearLayout) itemView.findViewById(R.id.lnitem_customaccount);

            txtId.setVisibility(View.INVISIBLE);


        }
    }

    @NonNull
    @Override
    public AdapterRecyclerAccount.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recyclerview_account, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn có muốn chuyển tài khoản này thành tài khoản admin?");
                builder.setCancelable(false);
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserModel userModel = new UserModel();
                        userModel.updateUserRole(viewHolder.txtId.getText().toString());
                        Toast.makeText(context, "Thực hiện thành công", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerAccount.ViewHolder viewHolder, int i) {
        UserModel userModel = userModelList.get(i);
        viewHolder.txtName.setText(userModel.getNameUser());
        viewHolder.txtRole.setText(userModel.getAccounttype());
        viewHolder.txtEmail.setText(userModel.getEmail());
        viewHolder.txtId.setText(userModel.getIdUser());
        if (viewHolder.txtRole.getText().equals("Admin")){
            viewHolder.btnAdmin.setVisibility(View.INVISIBLE);
        }

        StorageReference storageImgProductType = FirebaseStorage.getInstance().getReference().child("User").child(userModel.getAvatar());
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
        return userModelList.size();
    }
}
