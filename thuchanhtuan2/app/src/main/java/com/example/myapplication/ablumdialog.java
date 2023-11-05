package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ablumdialog extends AppCompatActivity {
    EditText ma ,name;
    Button XOA;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ablumdialog);
        ma=findViewById(R.id.maAblum);
        name=findViewById(R.id.tenAblum);
        XOA=findViewById(R.id.xoa);
        XOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ma.setText("");
                name.setText("");
                System.out.println("134");
            }
        });


    }
}