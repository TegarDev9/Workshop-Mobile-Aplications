package com.rtegar.laundryku.Model;

public class DataModel {

    private int id;
    private String nama, deskripsi, tahun;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return deskripsi;
    }

    public void setAlamat(String alamat) {
        this.deskripsi = alamat;
    }

    public String getTelepon() {
        return tahun;
    }

    public void setTelepon(String tahun) { this.tahun = tahun;
    }
}
