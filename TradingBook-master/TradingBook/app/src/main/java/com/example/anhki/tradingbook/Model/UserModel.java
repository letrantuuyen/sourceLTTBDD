package com.example.anhki.tradingbook.Model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.anhki.tradingbook.Adapter.AdapterRecyclerProductType;
import com.example.anhki.tradingbook.Controller.Interface.ProductInterface;
import com.example.anhki.tradingbook.Controller.Interface.ProductTypeInterface;
import com.example.anhki.tradingbook.Controller.Interface.UserInterface;
import com.example.anhki.tradingbook.View.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserModel {

    String accounttype;
    String address;
    String avatar;
    String nameUser;
    String phone;
    String idUser;
    String email;

    List<String> imageAccount;
    long point;

    DatabaseReference nodeRoot;
    Query nodeOne;

    String role;

    public UserModel(){
        nodeRoot = FirebaseDatabase.getInstance().getReference();
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public List<String> getImageAccount() {
        return imageAccount;
    }

    public void setImageAccount(List<String> imageAccount) {
        this.imageAccount = imageAccount;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public long getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void getInfoUser(final UserInterface userInterface, final String IdUser){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("accounts").child(IdUser);
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        UserModel userModel = dataSnapshot1.getValue(UserModel.class);
                        userInterface.getUserModel(userModel);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        nodeRoot.addListenerForSingleValueEvent(valueEventListener);
    }

    public void getDanhSachAccount(final UserInterface userInterface){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataSnapshotProductType = dataSnapshot.child("accounts");
                for (DataSnapshot valueProductType : dataSnapshotProductType.getChildren()){
                    UserModel userModel = valueProductType.getValue(UserModel.class);
                    userModel.setIdUser(valueProductType.getKey());
                    userInterface.getUserModel(userModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        nodeRoot.addListenerForSingleValueEvent(valueEventListener);
    }

    public void readDataRole (final FireBaseCallback fireBaseCallback,final String IdUser){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("accounts").child(IdUser).child("accounttype");
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        role = dataSnapshot1.getValue(String.class);
                        fireBaseCallback.onCallback(role);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        nodeRoot.addListenerForSingleValueEvent(valueEventListener);
    }

    public interface FireBaseCallback {
        void onCallback(String role);
    }

    public void updateUserRole(String id){
        nodeRoot.child("accounts").child(id).child("accounttype").setValue("Admin");
    }

    public void updateUser(String id, String name, String phone, String address){
        nodeRoot.child("accounts").child(id).child("nameUser").setValue(name);
        nodeRoot.child("accounts").child(id).child("phone").setValue(phone);
        nodeRoot.child("accounts").child(id).child("address").setValue(address);
    }

    public void createUser(String id, String email){
        nodeRoot.child("accounts").child(id).child("accounttype").setValue("Customer");
        nodeRoot.child("accounts").child(id).child("address").setValue("Chưa có thông tin");
        nodeRoot.child("accounts").child(id).child("avatar").setValue("user1.jpg");
        nodeRoot.child("accounts").child(id).child("email").setValue(email);
        nodeRoot.child("accounts").child(id).child("nameUser").setValue("Chưa có thông tin");
        nodeRoot.child("accounts").child(id).child("phone").setValue("Chưa có thông tin");
        nodeRoot.child("accounts").child(id).child("point").setValue(0);
    }
}