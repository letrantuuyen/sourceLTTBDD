package com.example.anhki.tradingbook.Model;

import android.support.annotation.NonNull;

import com.example.anhki.tradingbook.Controller.Interface.ProductTypeInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeModel {

    String idProductType,nameProductType;
    List<String> imageProductType;
    DatabaseReference nodeRoot;
    Query nodeOne;

    public List<String> getImageProductType() {
        return imageProductType;
    }

    public void setImageProductType(List<String> imageProductType) {
        this.imageProductType = imageProductType;
    }

    public ProductTypeModel(){
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        nodeRoot = FirebaseDatabase.getInstance().getReference();
    }

    public String getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(String idProductType) {
        this.idProductType = idProductType;
    }

    public String getNameProductType() {
        return nameProductType;
    }

    public void setNameProductType(String nameProductType) {
        this.nameProductType = nameProductType;
    }

    public void getDanhSachLoaiSanPham(final ProductTypeInterface productTypeInterface){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataSnapshotProductType = dataSnapshot.child("producttypes");
                for (DataSnapshot valueProductType : dataSnapshotProductType.getChildren()){
                    ProductTypeModel productTypeModel = valueProductType.getValue(ProductTypeModel.class);
                    productTypeModel.setIdProductType(valueProductType.getKey());

                    DataSnapshot dataSnapshotImage = dataSnapshot.child("imageproducttype").child(valueProductType.getKey());

                    List<String> imProductTypeList = new ArrayList<>();

                    for (DataSnapshot valueImage : dataSnapshotImage.getChildren()){
                        imProductTypeList.add(valueImage.getValue(String.class));
                    }

                    productTypeModel.setImageProductType(imProductTypeList);
                    productTypeInterface.getDanhSachLoaiSanPhamModel(productTypeModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        nodeRoot.addListenerForSingleValueEvent(valueEventListener);
    }

    public interface FireBaseCallback {
        void onCallback(String flag);
    }
    public void readDataFlag (final FireBaseCallback fireBaseCallback){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("producttypes").child("IdProductType1").child("nameProductType");
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        String flag = dataSnapshot1.getValue(String.class);
                        fireBaseCallback.onCallback(flag);
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
}
