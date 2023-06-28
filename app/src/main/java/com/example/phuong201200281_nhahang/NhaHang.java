package com.example.phuong201200281_nhahang;

public class NhaHang {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTennhahang() {
        return tennhahang;
    }

    public void setTennhahang(String tennhahang) {
        this.tennhahang = tennhahang;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getSophieu() {
        return sophieu;
    }

    public void setSophieu(int sophieu) {
        this.sophieu = sophieu;
    }

    public float getdiemtrungbinh() {
        return diemtrungbinh;
    }

    public void setdiemtrungbinh(float diemtrungbinh) {
        this.diemtrungbinh = diemtrungbinh;
    }

    private int id;
    private String tennhahang;
    private String diachi;
    private int sophieu;
    private float diemtrungbinh;

    public NhaHang(int id, String tennhahang, String diachi, int sophieu, float diemtrungbinh) {
        this.id = id;
        this.tennhahang = tennhahang;
        this.diachi = diachi;
        this.sophieu = sophieu;
        this.diemtrungbinh = diemtrungbinh;
    }
    public NhaHang( String tennhahang, String diachi, int sophieu, float diemtrungbinh) {

        this.tennhahang = tennhahang;
        this.diachi = diachi;
        this.sophieu = sophieu;
        this.diemtrungbinh = diemtrungbinh;
    }
}
