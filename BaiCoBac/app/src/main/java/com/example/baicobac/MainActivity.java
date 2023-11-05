package com.example.baicobac;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    long startTime;
    int value[] = new int[3];
    int value1[] = new int[3];
    int manghinhbai[] = {
            R.drawable.atco, R.drawable.haico, R.drawable.baco, R.drawable.bonco, R.drawable.namco, R.drawable.sauco, R.drawable.bayco,
            R.drawable.tamco, R.drawable.chinco, R.drawable.muoico, R.drawable.jco, R.drawable.qco, R.drawable.kco,

            R.drawable.atro, R.drawable.hairo, R.drawable.baro, R.drawable.bonro, R.drawable.namro,R.drawable.sauro, R.drawable.bayro, R.drawable.tamro,
            R.drawable.chinro, R.drawable.muoiro, R.drawable.jro, R.drawable.qro, R.drawable.kro,

            R.drawable.atchuong, R.drawable.haichuong, R.drawable.bachuong, R.drawable.bonchuong, R.drawable.namchuong, R.drawable.sauchuon,
            R.drawable.baychuon, R.drawable.tamchuon, R.drawable.chinchuon, R.drawable.muoichuon, R.drawable.jchuon, R.drawable.qchuon, R.drawable.kchuon,

            R.drawable.atbich, R.drawable.hainich, R.drawable.babich, R.drawable.bonbich, R.drawable.nambich, R.drawable.saubich, R.drawable.baybich,
            R.drawable.tambich, R.drawable.chinbich, R.drawable.muoibich, R.drawable.jbich, R.drawable.qbich, R.drawable.kbich


    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv1 = (ImageView) findViewById(R.id.imageView1);
        ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
        ImageView iv3 = (ImageView) findViewById(R.id.imageView3);

        //may2
        ImageView iv4 = (ImageView) findViewById(R.id.imageView4);
        ImageView iv5 = (ImageView) findViewById(R.id.imageView5);
        ImageView iv6 = (ImageView) findViewById(R.id.imageView6);

        TextView tv = (TextView) findViewById(R.id.tvKQ);

        TextView tv1 = (TextView) findViewById(R.id.tvKQ1);


        EditText ed1 = (EditText) findViewById(R.id.edText);
        TextView tvThoiGian = findViewById(R.id.tvThoiGian);
        Button btChon = (Button) findViewById(R.id.btRut);
        TextView tvTong1 = (TextView) findViewById(R.id.tvTong1);
        TextView tvTong = (TextView) findViewById(R.id.tvTong);
        TextView textViewChienThang = (TextView) findViewById(R.id.tvchienThang);

        btChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edt1 = ed1.getText().toString();
                int ed = Integer.parseInt(edt1) * 60; // Đổi giây thành phút
                //tính thời gian chênh lệch giữa thời điểm hiện tại và thời điểm bắt đầu
                startTime = System.currentTimeMillis();
                countDownTimer = new CountDownTimer(ed * 1000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        //chuyển từ mili giây --> giây
                        long elapsedTime = (millisUntilFinished / 1000);
                        //tính số phút và giây còn lại
                        int minutes = (int) (elapsedTime / 60);
                        int seconds = (int) (elapsedTime % 60);
                        tvThoiGian.setText(String.format("%02d:%02d", minutes, seconds));

                        value = layBaSoNgauNhien(0, manghinhbai.length - 1);
                        iv1.setImageResource(manghinhbai[value[0]]);
                        iv2.setImageResource(manghinhbai[value[1]]);
                        iv3.setImageResource(manghinhbai[value[2]]);
                        tvTong.setText(inKetQua(calculateTotalPoints(value), value));

                        value1 = layBaSoNgauNhien(0, manghinhbai.length - 1);
                        iv4.setImageResource(manghinhbai[value1[0]]);
                        iv5.setImageResource(manghinhbai[value1[1]]);
                        iv6.setImageResource(manghinhbai[value1[2]]);
                        tvTong1.setText(inKetQua(calculateTotalPoints(value1), value1));
                    }
                    public void onFinish() {
                        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
                        int minutes = (int) (elapsedTime / 60);
                        int seconds = (int) (elapsedTime % 60);
                        tvThoiGian.setText(String.format("%02d:%02d", minutes, seconds));

                        tvTong.setText(inKetQua(calculateTotalPoints(value), value));
                        tvTong1.setText(inKetQua(calculateTotalPoints(value1), value1));
                        textViewChienThang.setText(inLanChienThang());
                    }
                }.start();
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
/*
    private int tinhKetQua(int[] value) {
        int ketqua;
        int sotay = tinhSoTay(value);
        if (sotay == 3) {
            ketqua = 0;
        } else {
            int tong = 0;
            for (int i = 0; i < value.length; i++)
                if (value[i] % 13 < 10)
                    tong += value[i] % 13 + 1;
            ketqua = (tong % 10);
        }
        return ketqua;
    }
*/
    private String inKetQua(int kq, int[] value) {
        int soTay = tinhSoTay(value);
        return kq == 100 ? "3 lá tây" : kq + " nút, trong đó có " + soTay + " tây";
    }


    private int calculateTotalPoints(int[] value) {
        int totalPoints = 0;
        for(int i=0;i<value.length;i++){
            int cardValue = value[i] % 13;
            if(cardValue >=10){
                totalPoints += 0;

            }
            else{
                totalPoints += cardValue + 1;

            }
        } return totalPoints %10;
    }
    private String inLanChienThang() {
        int count1 = 0;
        int count2 = 0;
        int totalPointsValue = calculateTotalPoints(value);
        int totalPointsValue1 = calculateTotalPoints(value1);
        String ketqua = "";
        int rounds = random(1,100);
        for (int round = 1; round <= rounds; round++) {
            if (totalPointsValue > totalPointsValue1) {
                count1++;
            } else {
                count2++;
            }
        }
        for(int i = 0; i<= rounds;i++) {
            if (count1 > count2) {
                ketqua = "Máy 1 chiến thắng " + count1 + " lần.";
            } else if (count1 < count2) {
                ketqua = "Máy 2 chiến thắng " + count2 + " lần.";
            } else {
                ketqua = "Cả hai máy bằng nhau ";
            }
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