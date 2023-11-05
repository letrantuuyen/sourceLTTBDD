package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class Bai13 extends AppCompatActivity {
    EditText et1;
    Button btcover;
    TextView inputB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai13);
        et1=findViewById(R.id.inputA);
        btcover=findViewById(R.id.button2);
        inputB=findViewById(R.id.inputB);
        btcover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Integer nam=Integer.parseInt(et1.getText().toString());
        List<String> can = Arrays.asList("Canh", "Tan", "Nham", "Quy", "Giap", "At", "Binh", "Dinh", "Mau", "Ky");
       List<String> chi = Arrays.asList("Than", "Dau", "Tuat", "Hoi", "Ty", "Suu", "Dan", "Mao", "Thin", "Ty", "Ngo", "Mui");
        if(nam>=1900){
            int indexCan=nam%10;
            int indexchi=nam%12;
            inputB.setText(can.get(indexCan)+" "+chi.get(indexchi));

        }
        else{
            inputB.setError("Nam khong hop le");
        }
            }
        });

    }
}