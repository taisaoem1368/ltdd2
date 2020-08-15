package com.example.quanlidulich.Model;

public class TourModel {

    String maTour, loTrinh, hanhTrinh;

    Integer sttTour, giaTour;

    public TourModel(String maTour, String loTrinh, String hanhTrinh, Integer giaTour) {
        this.maTour = maTour;
        this.loTrinh = loTrinh;
        this.hanhTrinh = hanhTrinh;
        this.giaTour = giaTour;
    }

    public TourModel(Integer sttTour, String maTour, String loTrinh, String hanhTrinh, Integer giaTour) {
        this.sttTour = sttTour;
        this.maTour = maTour;
        this.loTrinh = loTrinh;
        this.hanhTrinh = hanhTrinh;
        this.giaTour = giaTour;
    }

    public TourModel() {

    }

    public Integer getSttTour() { return sttTour; }

    public void setSttTour(Integer sttTour) { this.sttTour = sttTour; }

    public String getMaTour() {
        return maTour;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }

    public String getLoTrinh() {
        return loTrinh;
    }

    public void setLoTrinh(String loTrinh) {
        this.loTrinh = loTrinh;
    }

    public String getHanhTrinh() {
        return hanhTrinh;
    }

    public void setHanhTrinh(String hanhTrinh) {
        this.hanhTrinh = hanhTrinh;
    }

    public Integer getGiaTour() {
        return giaTour;
    }

    public void setGiaTour(Integer giaTour) { this.giaTour = giaTour; }
}
