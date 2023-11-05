package com.example.anhki.tradingbook.Model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.anhki.tradingbook.Adapter.AdapterRecyclerProductType;
import com.example.anhki.tradingbook.Controller.Interface.ProductChuaDuyetInterface;
import com.example.anhki.tradingbook.Controller.Interface.ProductDaBanInterface;
import com.example.anhki.tradingbook.Controller.Interface.ProductInterface;
import com.example.anhki.tradingbook.Controller.Interface.ProductInvoiceInterface;
import com.example.anhki.tradingbook.Controller.Interface.ProductSearchInterface;
import com.example.anhki.tradingbook.Controller.Interface.ProductTenInterface;
import com.example.anhki.tradingbook.View.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductModel {

    String idProduct;
    String description;
    String nameproduct;
    String state;
    String idProductType;
    String idAccount;
    String nameUser;
    String address;
    String idAccount_checked;
    String producttype;
    String producttype_checked_bought;
    String idAccount_bought;
    List<String> imageProduct;
    long price;
    boolean checked;
    boolean bought;
    DatabaseReference nodeRoot, nodeCart;
    Query nodeOne, nodeProduct;


    public ProductModel(){
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        nodeRoot = FirebaseDatabase.getInstance().getReference();
    }

    public ProductModel(boolean bought, boolean checked, String description, String idAccount,String idAccount_checked,
                        String nameproduct,int price, String producttype,String producttype_checked_bought, String state){
        this.bought = bought;
        this.checked = checked;
        this.description = description;
        this.idAccount = idAccount;
        this.idAccount_checked = idAccount_checked;
        this.nameproduct = nameproduct;
        this.price = price;
        this.producttype = producttype;
        this.producttype_checked_bought = producttype_checked_bought;
        this.state = state;
    }

    public String getProducttype_checked_bought() {
        return producttype_checked_bought;
    }

    public void setProducttype_checked_bought(String producttype_checked_bought) {
        this.producttype_checked_bought = producttype_checked_bought;
    }


    public String getIdAccount_checked() {
        return idAccount_checked;
    }

    public void setIdAccount_checked(String idAccount_checked) {
        this.idAccount_checked = idAccount_checked;
    }


    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public String getIdAccount_bought() {
        return idAccount_bought;
    }

    public void setIdAccount_bought(String idAccount_bought) {
        this.idAccount_bought = idAccount_bought;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public List<String> getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(List<String> imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(String idProductType) {
        this.idProductType = idProductType;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void updateSanPham(String id, String state, long price, String description, String nameproduct){
        nodeRoot.child("products").child(id).child("state").setValue(state);
        nodeRoot.child("products").child(id).child("price").setValue(price);
        nodeRoot.child("products").child(id).child("nameproduct").setValue(nameproduct);
        nodeRoot.child("products").child(id).child("description").setValue(description);
    };

    public void updateSanPhamChecked(String id, String idUser, String producttype){
        nodeRoot.child("products").child(id).child("checked").setValue(true);
        nodeRoot.child("products").child(id).child("idAccount_checked").setValue(idUser + "_true");
        nodeRoot.child("products").child(id).child("producttype_checked_bought").setValue(producttype + "_true_false");
    };

    public void getDanhSachSanPham(final ProductInterface productInterface){

            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                    nodeOne = nodeRoot.child("products").orderByChild("producttype_checked_bought").equalTo(AdapterRecyclerProductType.nameProductType+"_true_false");
                    nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                            if (dataSnapshot1.exists()) {
                                for (DataSnapshot valueProduct : dataSnapshot1.getChildren()) {
                                    ProductModel productModel = valueProduct.getValue(ProductModel.class);
                                    productModel.setIdProduct(valueProduct.getKey());

                                    DataSnapshot dataSnapshotImage = dataSnapshot.child("imageproducts").child(valueProduct.getKey());
                                    List<String> imProductList = new ArrayList<>();

                                    for (DataSnapshot valueImage : dataSnapshotImage.getChildren()){
                                        imProductList.add(valueImage.getValue(String.class));
                                    }
                                    productModel.setImageProduct(imProductList);

                                    DataSnapshot dataSnapUser = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("nameUser");
                                    productModel.setNameUser(dataSnapUser.getValue().toString());

                                    DataSnapshot dataSnapAddress = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("address");
                                    productModel.setAddress(dataSnapAddress.getValue().toString());

                                    productInterface.getDanhSachSanPhamModel(productModel);
                                }
                            }
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

    public void getDanhSachSanPhamChuaDuyet(final String idUser, final ProductChuaDuyetInterface productChuaDuyetInterface){
        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("products").orderByChild("idAccount_checked").equalTo(idUser + "_false");
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        CountModel countModel = new CountModel();
                        countModel.setCount_ProductWaiting(dataSnapshot1.getChildrenCount());
                        if (dataSnapshot1.exists()) {
                            for (DataSnapshot valueProduct : dataSnapshot1.getChildren()) {
                                ProductModel productModel = valueProduct.getValue(ProductModel.class);
                                productModel.setIdProduct(valueProduct.getKey());

                                DataSnapshot dataSnapshotImage = dataSnapshot.child("imageproducts").child(valueProduct.getKey());
                                List<String> imProductList = new ArrayList<>();

                                for (DataSnapshot valueImage : dataSnapshotImage.getChildren()){
                                    imProductList.add(valueImage.getValue(String.class));
                                }
                                productModel.setImageProduct(imProductList);

                                DataSnapshot dataSnapUser = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("nameUser");
                                productModel.setNameUser(dataSnapUser.getValue().toString());

                                DataSnapshot dataSnapAddress = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("address");
                                productModel.setAddress(dataSnapAddress.getValue().toString());

                                productChuaDuyetInterface.getDanhSachSanPhamChuaDuyetModel(productModel);
                            }
                        }
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

    public void getDanhSachSanPhamChuaDuyetAdmin(final ProductChuaDuyetInterface productChuaDuyetInterface){
        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("products").orderByChild("checked").equalTo(false);
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        CountModel countModel = new CountModel();
                        countModel.setCount_ProductWaiting(dataSnapshot1.getChildrenCount());
                        if (dataSnapshot1.exists()) {
                            for (DataSnapshot valueProduct : dataSnapshot1.getChildren()) {
                                ProductModel productModel = valueProduct.getValue(ProductModel.class);
                                productModel.setIdProduct(valueProduct.getKey());

                                DataSnapshot dataSnapshotImage = dataSnapshot.child("imageproducts").child(valueProduct.getKey());
                                List<String> imProductList = new ArrayList<>();

                                for (DataSnapshot valueImage : dataSnapshotImage.getChildren()){
                                    imProductList.add(valueImage.getValue(String.class));
                                }
                                productModel.setImageProduct(imProductList);

                                DataSnapshot dataSnapUser = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("nameUser");
                                productModel.setNameUser(dataSnapUser.getValue().toString());

                                DataSnapshot dataSnapAddress = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("address");
                                productModel.setAddress(dataSnapAddress.getValue().toString());

                                productChuaDuyetInterface.getDanhSachSanPhamChuaDuyetModel(productModel);
                            }
                        }
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

    public void getDanhSachSanPhamTheoNguoiBan(final String idUser, final ProductTenInterface productTenInterface){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("products").orderByChild("idAccount_checked").equalTo(idUser+"_true").limitToFirst(20);
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        CountModel countModel = new CountModel();
                        countModel.setCount_ProductSelling(dataSnapshot1.getChildrenCount());
                        if (dataSnapshot1.exists()) {
                            for (DataSnapshot valueProduct : dataSnapshot1.getChildren()) {
                                ProductModel productModel = valueProduct.getValue(ProductModel.class);
                                productModel.setIdProduct(valueProduct.getKey());

                                DataSnapshot dataSnapshotImage = dataSnapshot.child("imageproducts").child(valueProduct.getKey());
                                List<String> imProductList = new ArrayList<>();

                                for (DataSnapshot valueImage : dataSnapshotImage.getChildren()){
                                    imProductList.add(valueImage.getValue(String.class));
                                }
                                productModel.setImageProduct(imProductList);

                                DataSnapshot dataSnapUser = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("nameUser");
                                productModel.setNameUser(dataSnapUser.getValue().toString());

                                DataSnapshot dataSnapAddress = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("address");
                                productModel.setAddress(dataSnapAddress.getValue().toString());

                                productTenInterface.getDanhSachSanPhamTheoTenModel(productModel);
                            }
                        }
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

    public void getDanhSachSanPhamDaBan(final String idUser, final ProductDaBanInterface productDaBanInterface){
        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("products").orderByChild("idAccount_bought").equalTo(idUser+"_true");
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        CountModel countModel = new CountModel();
                        countModel.setCount_ProductWaiting(dataSnapshot1.getChildrenCount());
                        if (dataSnapshot1.exists()) {
                            for (DataSnapshot valueProduct : dataSnapshot1.getChildren()) {
                                ProductModel productModel = valueProduct.getValue(ProductModel.class);
                                productModel.setIdProduct(valueProduct.getKey());

                                DataSnapshot dataSnapshotImage = dataSnapshot.child("imageproducts").child(valueProduct.getKey());
                                List<String> imProductList = new ArrayList<>();

                                for (DataSnapshot valueImage : dataSnapshotImage.getChildren()){
                                    imProductList.add(valueImage.getValue(String.class));
                                }
                                productModel.setImageProduct(imProductList);

                                DataSnapshot dataSnapUser = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("nameUser");
                                productModel.setNameUser(dataSnapUser.getValue().toString());

                                DataSnapshot dataSnapAddress = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("address");
                                productModel.setAddress(dataSnapAddress.getValue().toString());

                                productDaBanInterface.getDanhSachSanPhamDaBanModel(productModel);
                            }
                        }
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

    public void getDanhSachSanPhamSearch(final ProductSearchInterface productSearchInterface,final String name){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("products").orderByChild("nameproduct").startAt(name).endAt(name + "\uf8ff").limitToFirst(12);

                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        if (dataSnapshot1.exists()) {
                            for (DataSnapshot valueProduct : dataSnapshot1.getChildren()) {
                                ProductModel productModel = valueProduct.getValue(ProductModel.class);
                                productModel.setIdProduct(valueProduct.getKey());

                                DataSnapshot dataSnapshotImage = dataSnapshot.child("imageproducts").child(valueProduct.getKey());
                                List<String> imProductList = new ArrayList<>();

                                for (DataSnapshot valueImage : dataSnapshotImage.getChildren()){
                                    imProductList.add(valueImage.getValue(String.class));
                                }
                                productModel.setImageProduct(imProductList);

                                DataSnapshot dataSnapUser = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("nameUser");
                                productModel.setNameUser(dataSnapUser.getValue().toString());

                                DataSnapshot dataSnapAddress = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("address");
                                productModel.setAddress(dataSnapAddress.getValue().toString());

                                productSearchInterface.getDanhSachSanPhamSearchModel(productModel);
                            }
                        }
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

    public void readDataWaiting (final String idUser,final FireBaseCallback fireBaseCallback){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("products").orderByChild("idAccount_checked").equalTo(idUser + "_false");
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        long count1 = dataSnapshot1.getChildrenCount();
                        fireBaseCallback.onCallback(count1);
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

    public void readDataWaitingAdmin (final FireBaseCallback fireBaseCallback){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("products").orderByChild("checked").equalTo(false);
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        long count1 = dataSnapshot1.getChildrenCount();
                        fireBaseCallback.onCallback(count1);
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

    public void readDataSelling (final FireBaseCallback fireBaseCallback){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("products").orderByChild("idAccount_checked").equalTo(LoginActivity.Current_IdUser+"_true");
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        long count = dataSnapshot1.getChildrenCount();
                        fireBaseCallback.onCallback(count);
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

    public void readDataSold (final FireBaseCallback fireBaseCallback){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("products").orderByChild("idAccount_bought").equalTo(LoginActivity.Current_IdUser+"_true");
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        long count = dataSnapshot1.getChildrenCount();
                        fireBaseCallback.onCallback(count);
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
        void onCallback(long count);
    }

    public void createProduct (final boolean bought, final boolean checked,final String description, final String idAccount,
                               final String idAccount_checked, final String nameproduct,final int price,final String producttype,
                               final String producttype_checked_bought, final String state){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("products");
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot1) {
                        long countProduct = dataSnapshot1.getChildrenCount();
                        ProductModel product = new ProductModel(bought,checked,description, idAccount, idAccount_checked,
                                nameproduct, price, producttype, producttype_checked_bought, state);
                        String id = "IdProduct" + String.valueOf(countProduct + 1);
                        Log.d("IdProduct", id);
                        nodeRoot.child("products").child(id).setValue(product);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        nodeRoot.addListenerForSingleValueEvent(valueEventListener);
    }

    public void createProductImage (final String img1, final String img2, final String img3, final String img4){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("imageproducts");
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot1) {
                        long countProduct = dataSnapshot1.getChildrenCount();
                        String id = "IdProduct" + String.valueOf(countProduct + 1);
                        Log.d("IdProduct", id);
                        nodeRoot.child("imageproducts").child(id).child("idpic1").setValue(img1);
                        nodeRoot.child("imageproducts").child(id).child("idpic2").setValue(img2);
                        nodeRoot.child("imageproducts").child(id).child("idpic3").setValue(img3);
                        nodeRoot.child("imageproducts").child(id).child("idpic4").setValue(img4);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        nodeRoot.addListenerForSingleValueEvent(valueEventListener);
    }

    public void readDataCart (final String idUserCurrent, final FireBaseCallback fireBaseCallback){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("carts").child(idUserCurrent);
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        long count = dataSnapshot1.getChildrenCount();
                        count = count -1;
                        fireBaseCallback.onCallback(count);
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

    public void getDanhSachSanPhamCart(final String idUserCurrent, final ProductInterface productInterface){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("carts").child(idUserCurrent);
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        if (dataSnapshot1.exists()) {
                            final List<String> idProductList = new ArrayList<>();
                            for (DataSnapshot valueProduct : dataSnapshot1.getChildren()) {
                                idProductList.add(valueProduct.getValue(String.class));
                            }
                            idProductList.remove("temp");

                            nodeProduct = nodeRoot.child("products");
                            nodeProduct.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                    if (dataSnapshot1.exists()) {
                                        for (DataSnapshot valueProduct : dataSnapshot1.getChildren()) {
                                            for (String s:idProductList){
                                                if (valueProduct.getKey().equals(s)){
                                                    ProductModel productModel = valueProduct.getValue(ProductModel.class);
                                                    productModel.setIdProduct(valueProduct.getKey());

                                                    DataSnapshot dataSnapshotImage = dataSnapshot.child("imageproducts").child(valueProduct.getKey());
                                                    List<String> imProductList = new ArrayList<>();

                                                    for (DataSnapshot valueImage : dataSnapshotImage.getChildren()){
                                                        imProductList.add(valueImage.getValue(String.class));
                                                    }
                                                    productModel.setImageProduct(imProductList);

                                                    DataSnapshot dataSnapUser = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("nameUser");
                                                    productModel.setNameUser(dataSnapUser.getValue().toString());

                                                    DataSnapshot dataSnapAddress = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("address");
                                                    productModel.setAddress(dataSnapAddress.getValue().toString());

                                                    productInterface.getDanhSachSanPhamModel(productModel);
                                                }
                                            }

                                        }
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
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

    public void addCart(final String idProduct, final String idUser, final String producttype, final String idUserProduct){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("carts").child(idUser);
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot1) {
                        long countProduct = dataSnapshot1.getChildrenCount();
                        nodeRoot.child("carts").child(idUser).child(String.valueOf(countProduct-1)).setValue(idProduct);
                        nodeRoot.child("products").child(idProduct).child("bought").setValue(true);
                        nodeRoot.child("products").child(idProduct).child("producttype_checked_bought").setValue(producttype+"_true_true");
                        nodeRoot.child("products").child(idProduct).child("idAccount_bought").setValue(idUserProduct+"_true");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        nodeRoot.addListenerForSingleValueEvent(valueEventListener);
    }

    public void removeCart(final String id, final String idUser, final String idLastProduct, final String producttype, final String idUserProduct){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("carts").child(idUser);
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        if (dataSnapshot1.exists()) {
                            long countProduct = dataSnapshot1.getChildrenCount();
                            for (DataSnapshot valueProduct : dataSnapshot1.getChildren()) {
                                if (valueProduct.getValue().equals(id) && valueProduct.getKey().equals(String.valueOf(countProduct -2))){
                                    nodeRoot.child("carts").child(idUser).child(valueProduct.getKey()).removeValue();
                                    nodeRoot.child("products").child(id).child("bought").setValue(false);
                                    nodeRoot.child("products").child(id).child("producttype_checked_bought").setValue(producttype+"_true_false");
                                    nodeRoot.child("products").child(id).child("idAccount_bought").setValue(idUserProduct+"_false");
                                }
                                else
                                    if (valueProduct.getValue().equals(id)){
                                        nodeRoot.child("carts").child(idUser).child(String.valueOf(countProduct -2)).removeValue();
                                        nodeRoot.child("carts").child(idUser).child(valueProduct.getKey()).setValue(idLastProduct);
                                        nodeRoot.child("products").child(id).child("bought").setValue(false);
                                        nodeRoot.child("products").child(id).child("producttype_checked_bought").setValue(producttype+"_true_false");
                                        nodeRoot.child("products").child(id).child("idAccount_bought").setValue(idUserProduct+"_false");
                                    }
                            }
                        }
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

    public void getSumCart(final String idUserCurrent,final FireBaseCallback fireBaseCallback){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("carts").child(idUserCurrent);
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        if (dataSnapshot1.exists()) {
                            final List<String> idProductList = new ArrayList<>();
                            for (DataSnapshot valueProduct : dataSnapshot1.getChildren()) {
                                idProductList.add(valueProduct.getValue(String.class));
                            }
                            idProductList.remove("temp");

                            nodeProduct = nodeRoot.child("products");
                            nodeProduct.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                    long sum = 0;
                                    if (dataSnapshot1.exists()) {
                                        for (DataSnapshot valueProduct : dataSnapshot1.getChildren()) {
                                            for (String s:idProductList){
                                                if (valueProduct.getKey().equals(s)){
                                                    ProductModel productModel = valueProduct.getValue(ProductModel.class);
                                                    productModel.setIdProduct(valueProduct.getKey());

                                                    DataSnapshot dataSnapshotImage = dataSnapshot.child("imageproducts").child(valueProduct.getKey());
                                                    List<String> imProductList = new ArrayList<>();

                                                    for (DataSnapshot valueImage : dataSnapshotImage.getChildren()){
                                                        imProductList.add(valueImage.getValue(String.class));
                                                    }
                                                    productModel.setImageProduct(imProductList);

                                                    DataSnapshot dataSnapUser = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("nameUser");
                                                    productModel.setNameUser(dataSnapUser.getValue().toString());

                                                    DataSnapshot dataSnapAddress = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("address");
                                                    productModel.setAddress(dataSnapAddress.getValue().toString());

                                                    sum = sum + productModel.getPrice();
                                                }
                                            }
                                        }
                                    }
                                    fireBaseCallback.onCallback(sum);
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
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

    public void getDanhSachSanPhamHoaDon(final String idInvoice, final ProductInvoiceInterface productInvoiceInterface){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("detailinvoices").child(idInvoice);
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        if (dataSnapshot1.exists()) {
                            final List<String> idProductList = new ArrayList<>();
                            for (DataSnapshot valueProduct : dataSnapshot1.getChildren()) {
                                idProductList.add(valueProduct.getValue(String.class));
                            }

                            nodeProduct = nodeRoot.child("products");
                            nodeProduct.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                    if (dataSnapshot1.exists()) {
                                        for (DataSnapshot valueProduct : dataSnapshot1.getChildren()) {
                                            for (String s:idProductList){
                                                if (valueProduct.getKey().equals(s)){
                                                    ProductModel productModel = valueProduct.getValue(ProductModel.class);
                                                    productModel.setIdProduct(valueProduct.getKey());

                                                    DataSnapshot dataSnapshotImage = dataSnapshot.child("imageproducts").child(valueProduct.getKey());
                                                    List<String> imProductList = new ArrayList<>();

                                                    for (DataSnapshot valueImage : dataSnapshotImage.getChildren()){
                                                        imProductList.add(valueImage.getValue(String.class));
                                                    }
                                                    productModel.setImageProduct(imProductList);

                                                    DataSnapshot dataSnapUser = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("nameUser");
                                                    productModel.setNameUser(dataSnapUser.getValue().toString());

                                                    DataSnapshot dataSnapAddress = dataSnapshot.child("accounts").child(productModel.getIdAccount()).child("address");
                                                    productModel.setAddress(dataSnapAddress.getValue().toString());

                                                    productInvoiceInterface.getDanhSachSanPhamHoaDonModel(productModel);
                                                }
                                            }

                                        }
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
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

    public void createCart(String id){
        nodeRoot.child("carts").child(id).child("temp").setValue("temp");
    };
}