package com.example.lt_customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<Employee> employees;
    CustomList adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView listView = (ListView) findViewById(R.id.lvView);
        ImageButton imgButton = (ImageButton) findViewById(R.id.id_img);

        employees = new ArrayList<>();
        employees.add(new Employee("01","Tú Uyên",true,"It"));
        employees.add(new Employee("01","Tú Uyên",true,"It"));
        employees.add(new Employee("01","Tú Uyên",true,"It"));
        employees.add(new Employee("01","Tú Uyên",true,"It"));

        listView.setAdapter(new CustomList(employees,MainActivity.this));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}