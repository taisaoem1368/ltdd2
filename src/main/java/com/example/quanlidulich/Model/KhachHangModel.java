package com.example.quanlidulich.Model;

public class KhachHangModel {
    Integer sttKH;
    String maKH;
    String tenKH;
    String diaChi;

    public KhachHangModel(Integer sttKH, String maKH, String tenKH, String diaChi) {
        this.sttKH = sttKH;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
    }

    public KhachHangModel(String maKH, String tenKH, String diaChi) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
    }

    public KhachHangModel() {
    }

    public String getMaKH() {
        return maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Integer getSttKH() { return sttKH; }

    public void setSttKH(Integer sttKH) { this.sttKH = sttKH; }

}
