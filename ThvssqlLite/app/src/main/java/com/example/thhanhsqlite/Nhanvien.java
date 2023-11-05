package com.example.thhanhsqlite;

public class Nhanvien {
    private  String maNhanvien;
    private String tenNhanVien;
    private int age;

    public Nhanvien(String maNhanvien, String tenNhanVien, int age) {
        this.maNhanvien = maNhanvien;
        this.tenNhanVien = tenNhanVien;
        this.age = age;
    }

    public String getMaNhanvien() {
        return maNhanvien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public int getAge() {
        return age;
    }

    public void setMaNhanvien(String maNhanvien) {
        this.maNhanvien = maNhanvien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Nhanvien{" +
                "maNhanvien='" + maNhanvien + '\'' +
                ", tenNhanVien='" + tenNhanVien + '\'' +
                ", age=" + age +
                '}';
    }
}
