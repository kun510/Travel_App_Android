package com.hqsoft.esales.doanapptravel;

public class HotelModel {
    private String img;
    private String name_hotel;
    private String gia_hotel;
    private String addr_hotel;

    public HotelModel(String img, String name_hotel, String gia_hotel, String addr_hotel) {
        this.img = img;
        this.name_hotel = name_hotel;
        this.gia_hotel = gia_hotel;
        this.addr_hotel = addr_hotel;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName_hotel() {
        return name_hotel;
    }

    public void setName_hotel(String name_hotel) {
        this.name_hotel = name_hotel;
    }

    public String getGia_hotel() {
        return gia_hotel;
    }

    public void setGia_hotel(String gia_hotel) {
        this.gia_hotel = gia_hotel;
    }

    public String getAddr_hotel() {
        return addr_hotel;
    }

    public void setAddr_hotel(String addr_hotel) {
        this.addr_hotel = addr_hotel;
    }
}
