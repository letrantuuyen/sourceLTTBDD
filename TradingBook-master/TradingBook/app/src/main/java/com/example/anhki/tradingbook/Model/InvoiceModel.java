package com.example.anhki.tradingbook.Model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.anhki.tradingbook.Controller.Interface.InvoiceInterface;
import com.example.anhki.tradingbook.Controller.Interface.UserInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InvoiceModel {

    String idInvoice;
    String nameUser;
    String date;
    String idAccount;
    long pointinvoice, total;
    boolean shipped;

    DatabaseReference nodeRoot;
    Query nodeOne,nodeTwo;

    String id;
    public InvoiceModel(){
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        nodeRoot = FirebaseDatabase.getInstance().getReference();
    }

    public InvoiceModel(String date, String idAccount, long pointinvoice, boolean shipped, long total){
        this.date = date;
        this.idAccount = idAccount;
        this.pointinvoice = pointinvoice;
        this.shipped = shipped;
        this.total = total;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(String idInvoice) {
        this.idInvoice = idInvoice;
    }

    public long getPointinvoice() {
        return pointinvoice;
    }

    public void setPointinvoice(long pointinvoice) {
        this.pointinvoice = pointinvoice;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public boolean isShipped() {
        return shipped;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
    }

    public void createInvoice ( final String date,final String idAccount, final long pointinvoice,final boolean shipped, final long total){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                nodeOne = nodeRoot.child("invoices");
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot1) {
                        long count = dataSnapshot1.getChildrenCount();
                        InvoiceModel invoice = new InvoiceModel(date,idAccount, pointinvoice, shipped, total);
                        id = "IdInvoice" + String.valueOf(count + 1);
                        Log.d("IdInvoice", id);
                        nodeRoot.child("invoices").child(id).setValue(invoice);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                nodeTwo = nodeRoot.child("carts").child(idAccount);
                nodeTwo.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot2) {
                        if (dataSnapshot2.exists()) {
                            final List<String> idProductList = new ArrayList<>();
                            for (DataSnapshot valueProduct : dataSnapshot2.getChildren()) {
                                idProductList.add(valueProduct.getValue(String.class));
                            }
                            idProductList.remove("temp");
                            int i = 0;
                            for (String s:idProductList){
                                nodeRoot.child("detailinvoices").child(id).child(String.valueOf(i)).setValue(s);
                                nodeRoot.child("carts").child(idAccount).child(String.valueOf(i)).removeValue();
                                i++;
                            }
                        }
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

    public void getDanhSachInvoice(final InvoiceInterface invoiceInterface){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                nodeOne = nodeRoot.child("invoices");
                nodeOne.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot1) {
                        for (DataSnapshot valueInvoice : dataSnapshot1.getChildren()) {
                            InvoiceModel invoiceModel = valueInvoice.getValue(InvoiceModel.class);
                            invoiceModel.setIdInvoice(valueInvoice.getKey());

                            DataSnapshot dataSnapshotName = dataSnapshot.child("accounts").child(invoiceModel.idAccount).child("nameUser");
                            invoiceModel.setNameUser(dataSnapshotName.getValue().toString());
                            invoiceInterface.getDanhSachInvoiceModel(invoiceModel);
                        }
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
}