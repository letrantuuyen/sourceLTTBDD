package com.example.anhki.tradingbook.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anhki.tradingbook.Adapter.AdapterRecyclerAccount;
import com.example.anhki.tradingbook.Adapter.AdapterRecyclerProductType;
import com.example.anhki.tradingbook.Controller.Interface.ProductTypeInterface;
import com.example.anhki.tradingbook.Controller.Interface.UserInterface;
import com.example.anhki.tradingbook.Model.ProductTypeModel;
import com.example.anhki.tradingbook.Model.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    UserModel userModel;
    Context context;
    AdapterRecyclerAccount adapterRecyclerAccount;

    public UserController(Context context){
        this.context = context;
        userModel = new UserModel();
    }

    public void getUserController(String id, final TextView txtName,final TextView txtPhone, final TextView txtAddress, final TextView txtPoint, final ImageView imageView){
        UserInterface userInterface = new UserInterface() {
            @Override
            public void getUserModel(UserModel userModel) {
                StorageReference storageImgProductType = FirebaseStorage.getInstance().getReference().child("User").child(userModel.getAvatar());
                long ONE_MEGABYTE = 1024 * 1024;
                storageImgProductType.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        imageView.setImageBitmap(bitmap);
                    }
                });

                txtName.setText(userModel.getNameUser());
                txtPhone.setText(userModel.getPhone());
                txtAddress.setText(userModel.getAddress());
                txtPoint.setText(String.valueOf(userModel.getPoint()));
            }
        };

        userModel.getInfoUser(userInterface, id);
    }

    public void getUserProController(String id, final TextView txtName, final TextView txtAddress){
        UserInterface userInterface = new UserInterface() {
            @Override
            public void getUserModel(UserModel userModel) {
                txtName.setText(userModel.getNameUser());
                txtAddress.setText(userModel.getAddress());
            }
        };
        userModel.getInfoUser(userInterface, id);
    }

    public void getDanhSachAccountController(RecyclerView recyclerAccount){
        final List<UserModel> userModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerAccount.setLayoutManager(layoutManager);
        recyclerAccount.setLayoutManager(new LinearLayoutManager(context));
        adapterRecyclerAccount = new AdapterRecyclerAccount(context, userModelList);
        recyclerAccount.setAdapter(adapterRecyclerAccount);

        UserInterface userInterface = new UserInterface() {
            @Override
            public void getUserModel(UserModel userModel) {
                userModelList.add(userModel);
                adapterRecyclerAccount.notifyDataSetChanged();
            }
        };

        userModel.getDanhSachAccount(userInterface);
    }
}
