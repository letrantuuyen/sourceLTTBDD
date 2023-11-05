package com.example.lt_pt;

public class PhuongTrinhBacNhat {
    int a;
    int b;
    public PhuongTrinhBacNhat(){

    }
    public PhuongTrinhBacNhat(int A, int B){
        this.a = A;
        this.b = B;
    }
    public String nghiemPhuongTrinh(){
        if(a==0)
            if(b==0)
                return "Phuong trinh vo so nghiem";
            else
                return "Phuong trinh vo nghiem";
            else
                return "Nghiem cua phuong trinh = " + (-b/a);
    }

}
