package com.example.onthigk;

import android.text.Editable;

public class NhanVien {
    private String ma;
    private String ten;
    private String gioitinh;
    private String donvi;
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }



    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi() {
        this.donvi = donvi;
    }

    public NhanVien(String ma, String ten, String gioitinh, String donvi) {
        this.ma = ma;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.donvi = donvi;
    }

    public NhanVien() {
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                ", gioitinh=" + gioitinh +
                ", donvi='" + donvi + '\'' +
                '}';
    }
}
