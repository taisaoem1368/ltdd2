package com.example.quanlidulich.Model;

public class PhieuDangKyModel {
    String soPhieu, maTour, maKH, ngayDK;
    Integer sttSoPhieuDK, soNguoi;

    public PhieuDangKyModel() {
    }

    public PhieuDangKyModel(String soPhieu, String maKH, String maTour , String ngayDK, Integer soNguoi) {
        this.soPhieu = soPhieu;
        this.maKH = maKH;
        this.maTour = maTour;
        this.ngayDK = ngayDK;
        this.soNguoi = soNguoi;
    }

    public PhieuDangKyModel(Integer sttSoPhieuDK, String soPhieu, String maKH, String maTour, String ngayDK,Integer soNguoi) {
        this.sttSoPhieuDK = sttSoPhieuDK;
        this.soPhieu = soPhieu;
        this.maKH = maKH;
        this.maTour = maTour;
        this.ngayDK = ngayDK;
        this.soNguoi = soNguoi;
    }

    public String getSoPhieu() { return soPhieu; }

    public void setSoPhieu(String soPhieu) { this.soPhieu = soPhieu; }

    public String getMaTour() { return maTour; }

    public void setMaTour(String maTour) { this.maTour = maTour; }

    public String getMaKH() { return maKH; }

    public void setMaKH(String maKH) { this.maKH = maKH; }

    public Integer getSoNguoi() { return soNguoi; }

    public void setSoNguoi(Integer soNguoi) { this.soNguoi = soNguoi; }

    public String getNgayDK() { return ngayDK; }

    public void setNgayDK(String ngayDK) { this.ngayDK = ngayDK; }

    public Integer getSttSoPhieuDK() { return sttSoPhieuDK; }

    public void setSttSoPhieuDK(Integer sttSoPhieuDK) { this.sttSoPhieuDK = sttSoPhieuDK; }
}
