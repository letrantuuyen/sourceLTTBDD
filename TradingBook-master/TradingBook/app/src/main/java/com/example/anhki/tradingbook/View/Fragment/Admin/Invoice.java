package com.example.anhki.tradingbook.View.Fragment.Admin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anhki.tradingbook.Controller.InvoiceController;
import com.example.anhki.tradingbook.Controller.ProductController;
import com.example.anhki.tradingbook.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Invoice extends Fragment {
    InvoiceController invoiceController;
    RecyclerView recyclerInvoice;

    public static Invoice newInstance(int i) {
        return new Invoice();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_admin_invoice, container, false);
        recyclerInvoice = (RecyclerView) view.findViewById(R.id.recyclerInvoice);

        invoiceController = new InvoiceController(getApplicationContext());
        invoiceController.getDanhSachInvoiceController(recyclerInvoice);
        return view;
    }
}
