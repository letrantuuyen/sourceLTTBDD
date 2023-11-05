package com.example.myapplicationteim;


import android.net.Uri;

public class ImageItem {
    private int maNV;
    private String tenNV;
    private String gioiTinh;
    private String donVi;

    private Uri imageUri;

    public ImageItem(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public Uri getImageUri() {
        return imageUri;
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

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
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

    public ImageItem(int maNV, String tenNV, Uri imageUri) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.imageUri = imageUri;
    }

    public ImageItem(int maNV, String tenNV, String gioiTinh, String donVi, Uri imageUri) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.donVi = donVi;
        this.imageUri = imageUri;
    }

    @Override
    public String toString() {
        return "ImageItem{" +
                "maNV=" + maNV + '\'' +
                ", tenNV='" + tenNV + '\'' +
                ", imageUri=" + imageUri +
                '}';
    }
}