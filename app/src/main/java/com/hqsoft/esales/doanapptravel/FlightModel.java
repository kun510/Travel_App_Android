package com.hqsoft.esales.doanapptravel;

public class FlightModel {

    String diemkhoihanh;
    String diemden;
    String ngaydi;
    String sohanhkhach;
    String loaighe;
    String gia;

    public FlightModel(String diemkhoihanh, String diemden, String ngaydi, String sohanhkhach, String loaighe, String gia) {
        this.diemkhoihanh = diemkhoihanh;
        this.diemden = diemden;
        this.ngaydi = ngaydi;
        this.sohanhkhach = sohanhkhach;
        this.loaighe = loaighe;
        this.gia = gia;
    }

    public String getDiemkhoihanh() {
        return diemkhoihanh;
    }

    public void setDiemkhoihanh(String diemkhoihanh) {
        this.diemkhoihanh = diemkhoihanh;
    }

    public String getDiemden() {
        return diemden;
    }

    public void setDiemden(String diemden) {
        this.diemden = diemden;
    }

    public String getNgaydi() {
        return ngaydi;
    }

    public void setNgaydi(String ngaydi) {
        this.ngaydi = ngaydi;
    }

    public String getSohanhkhach() {
        return sohanhkhach;
    }

    public void setSohanhkhach(String sohanhkhach) {
        this.sohanhkhach = sohanhkhach;
    }

    public String getLoaighe() {
        return loaighe;
    }

    public void setLoaighe(String loaighe) {
        this.loaighe = loaighe;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
