package com.example.lt_baitaptuan4;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ListProduct {
    ArrayList<Product> lstProduct;

    public ListProduct() {

        lstProduct = new ArrayList<Product>();
    }

    public boolean add(Product pro) {
        for (Product p : lstProduct)
            if (lstProduct.equals(pro.getId())) {
                return false;
            }
                lstProduct.add(pro);

        return true;
    }

    public boolean delete(int id) {
        for (Product product : lstProduct) {
            if (lstProduct.equals(id))
                lstProduct.remove(product);
            return true;
        }
        return false;
    }
}
