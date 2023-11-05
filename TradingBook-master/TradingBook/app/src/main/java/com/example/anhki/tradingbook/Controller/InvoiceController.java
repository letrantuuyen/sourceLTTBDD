package com.example.anhki.tradingbook.Controller;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.anhki.tradingbook.Adapter.AdapterRecyclerAccount;
import com.example.anhki.tradingbook.Adapter.AdapterRecyclerInvoice;
import com.example.anhki.tradingbook.Controller.Interface.InvoiceInterface;
import com.example.anhki.tradingbook.Model.InvoiceModel;
import com.example.anhki.tradingbook.Model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class InvoiceController {

    InvoiceModel invoiceModel;
    Context context;
    AdapterRecyclerInvoice adapterRecyclerInvoice;

    public InvoiceController(Context context){
        this.context = context;
        invoiceModel = new InvoiceModel();
    }

    public void getDanhSachInvoiceController(RecyclerView recyclerInvoice){
        final List<InvoiceModel> invoiceModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerInvoice.setLayoutManager(layoutManager);
        recyclerInvoice.setLayoutManager(new LinearLayoutManager(context));
        adapterRecyclerInvoice = new AdapterRecyclerInvoice(context, invoiceModelList);
        recyclerInvoice.setAdapter(adapterRecyclerInvoice);

        InvoiceInterface invoiceInterface = new InvoiceInterface() {
            @Override
            public void getDanhSachInvoiceModel(InvoiceModel invoiceModel) {
                invoiceModelList.add(invoiceModel);
                adapterRecyclerInvoice.notifyDataSetChanged();
            }
        };

        invoiceModel.getDanhSachInvoice(invoiceInterface);
    }
}
