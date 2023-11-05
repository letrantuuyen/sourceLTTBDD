package com.example.ngothienbang_19523171_bttuan5;

public class SinhVien {
    private String name;
    private String year;
    private String sex;
    private int hinhanh;

    public SinhVien() {
    }

    public SinhVien(String name, String year, String sex, int hinhanh) {
        this.name = name;
        this.year = year;
        this.sex = sex;
        this.hinhanh = hinhanh;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getSex() {
        return sex;
    }

    public int getHinhanh() {
        return hinhanh;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setHinhanh(int hinhanh) {
        this.hinhanh = hinhanh;
    }
}
