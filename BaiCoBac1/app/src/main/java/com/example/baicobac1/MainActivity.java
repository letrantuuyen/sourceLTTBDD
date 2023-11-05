package com.example.baicobac1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int manghinhbai[] = {R.drawable.atco,R.drawable.haico,R.drawable.baco


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv1 = (ImageView) findViewById(R.id.imageView1);
        ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
        ImageView iv3 = (ImageView) findViewById(R.id.imageView3);

        TextView tv = (TextView) findViewById(R.id.tvKQ);

        Button btChon = (Button) findViewById(R.id.btRut);
        btChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value[] = new int[3];
                value = layBaSoNgauNhien(0,5);

                iv1.setImageResource(manghinhbai[value[0]]);
                iv1.setImageResource(manghinhbai[value[1]]);
                iv1.setImageResource(manghinhbai[value[2]]);
                tv.setText(tinhKetQua(value));
            }
        });

    }

    public boolean kiemTraTrung(int k, int a[]){
        for(int i=0;i<a.length;i++)
            if(a[i]== k)
                return true;
        return false;
    }

    private int tinhSoTay(int a[]){
        int k =0;
        for(int i =0;i<a.length;i++)
            if((a[i] %13 >=10) && (a[i] % 13 <13))
                k++;
        return k;
    }

    private String tinhKetQua(int[] value){
        String ketqua = " ";
        if(tinhSoTay(value) == 3){
            ketqua = "ket qua 3 tay";
        }else{
            int tong =0;
            for (int i=0;i<value.length;i++)
                if(value[i] % 13 <10)
                    tong+= value[i] %13 +1;
            if(tong %10 ==0) {
                ketqua = "ket qua bu, trong do co" + tinhSoTay(value) + "tay";
            }
            else
                ketqua = "ket qua la" + (tong %10)+ "nut, trong do co"+ tinhSoTay(value)+ "tay";

        }
        return ketqua;
    }

    public int random(int min, int max){
        return min + (int) (Math.random() * (max - min) +1);
    }
    private int[] layBaSoNgauNhien(int min, int max){
        int baso[] = new int[3];
        int i=0;
        baso[i++] = random(min,max);

        do{
            int k= random(min,max);
            if(!kiemTraTrung(k,baso))
                baso[i++] = k;
        }while(i<3);
        return baso;
    }

}