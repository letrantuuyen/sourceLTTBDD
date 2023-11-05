package com.example.lt_demolistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    int vitri;
    String [] listItem; /
    Spinner sp_traicay;

    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_chon = (Button) findViewById(R.id.button_chon);

        bt_chon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // listview
            }
                });
                //spinner
        /*
            listView = (ListView) findViewById(R.id.listview);
            listItem = getResources().getStringArray(R.array.traicay_array);
            final ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1, listItem);
            listView.setAdapter(adapter);

            //listview xu ly
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String value = adapter.getItem(position);
                        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_LONG).show();
                    }
                    });
*/
                    //GridView
            gridView = (GridView) findViewById(R.id.grid_view);
        listItem = getResources().getStringArray(R.array.traicay_array);
            ArrayAdapter<String> gv_adapter = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1,listItem);
            gridView.setAdapter(gv_adapter);
           gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   String value = gv_adapter.getItem(position);
                   Toast.makeText(getApplicationContext(), value, Toast.LENGTH_LONG).show();
               }
           });
            /*
                //spinner
                    sp_traicay = (Spinner) findViewById(R.id.sp_traicay);
                    listItem = getResources().getStringArray(R.array.traicay_array);
                    ArrayAdapter<String> sp_adapter = new ArrayAdapter<String>(MainActivity.this,
                            android.R.layout.simple_list_item_1,listItem);
                    sp_traicay.setAdapter(sp_adapter);
                    sp_traicay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            String value = listItem[position];
                            Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


             */
            }
        }