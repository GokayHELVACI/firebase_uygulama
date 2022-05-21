package com.example.firebase_uygulama;

public class shop_info {

    private String isim;
    private String bilgi;
    private String url;

    public shop_info(String isim, String bilgi, String url) {
        this.isim = isim;
        this.bilgi = bilgi;
        this.url = url;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String get_bilgi() {
        return bilgi;
    }

    public void set_bilgi(String bilgi) {
        this.bilgi = bilgi;
    }

    public String get_url() {
        return url;
    }

    public void set_url(String url) {
        this.url = url;
    }




}
