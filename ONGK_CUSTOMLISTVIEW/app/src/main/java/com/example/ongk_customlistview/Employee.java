package com.example.ongk_customlistview;

import android.net.Uri;

public class Employee {
    private int maNV;
    private String tenNV;
    private String gioiTinh;
    private String donVi;
    private Uri imageUri;

    public Employee(int maNV, String tenNV, String gioiTinh, String donVi,Uri imageUri) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.donVi = donVi;
        this.imageUri = imageUri;

    }


    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "maNV=" + maNV +
                ", tenNV='" + tenNV + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", donVi='" + donVi + '\'' +
                ", imageUri=" + imageUri +
                '}';
    }
}
