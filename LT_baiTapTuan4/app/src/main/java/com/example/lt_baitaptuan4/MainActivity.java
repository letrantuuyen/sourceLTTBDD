package com.example.lt_baitaptuan4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> products = new ArrayList<>();
    EditText ed_ID = (EditText) findViewById(R.id.edId);
    EditText ed_Name = (EditText) findViewById(R.id.edName);
    EditText ed_Price = (EditText) findViewById(R.id.edPrice);
    ListView lstView = (ListView) findViewById(R.id.listView);


    Button bt_Them = (Button) findViewById(R.id.btnThem);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(ed_ID.getText().toString());
                String name = ed_Name.getText().toString();
                float price = Float.parseFloat(ed_Price.getText().toString());
                //hien thi listview
                ArrayList<String> lstItem = new ArrayList<String>();
                for (Product p: products)
                    lstItem.add(pro.toString());


               final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1, lstItem);
               adapter.notifyDataSetChanged();
                lstView.setAdapter(adapter);


            }
        });

        Button bt_Xoa = (Button) findViewById(R.id.btnXoa);

        Button bt_update = (Button) findViewById(R.id.btnluusua);
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    public boolean addProduct(Product p){
      int id =
        for (Product product :products) {
            if(product.getId() == p.getId())
                return false;
        }
        products.add(pro);
        return true;
    }
}