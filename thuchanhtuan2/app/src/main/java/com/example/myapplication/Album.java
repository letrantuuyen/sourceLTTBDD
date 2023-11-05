package com.example.myapplication;

public class Album {
    private String codeAlbum;
    private String nameAlbum;
    static  int number=0;
    private int stt ;
    public Album(String codeAlbum, String nameAlbum) {
        this.codeAlbum = codeAlbum;
        this.nameAlbum = nameAlbum;
        number +=1;
        stt = number;
    }

    public String getCodeAlbum() {
        return codeAlbum;
    }

    public void setCodeAlbum(String codeAlbum) {
        this.codeAlbum = codeAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }
    public int getStt() {
        return stt;
    }
}
