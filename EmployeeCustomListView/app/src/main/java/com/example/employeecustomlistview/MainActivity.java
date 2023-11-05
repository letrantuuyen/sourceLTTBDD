package com.example.employeecustomlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Employee> myList = new ArrayList<>();
        Employee e1 = new Employee("ms1","Truong Minh Tuan","Nam","IT");
        Employee e2 = new Employee("ms2","Tran Hoang Truong","Nam","IT");
        Employee e3 = new Employee("ms3","Tran The Vinh ","Nam","IT");

        myList.add(e1);
        myList.add(e2);
        myList.add(e3);
        final ListView listView = (ListView) findViewById(R.id.lvView);
        listView.setAdapter(new CustomListAdapter(myList,MainActivity.this));
    }
}