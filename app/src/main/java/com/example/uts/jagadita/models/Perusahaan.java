package com.example.uts.jagadita.models;

import android.widget.EditText;

public class Perusahaan {
    private int id;
    private int id_pemilik;
    private String nama_pemilik;
    private String nama_perusahaan;
    private String jenis_usaha;
    private String alamat;
    private String telp;
    private String deskripsi;
    private String kota;
    private int total_saham;
    private int harga;

    public Perusahaan(int id, int id_pemilik, String nama_pemilik, String nama_perusahaan, String jenis_usaha, String alamat, String telp, String deskripsi, String kota, int total_saham, int harga) {
        this.id = id;
        this.id_pemilik = id_pemilik;
        this.nama_pemilik = nama_pemilik;
        this.nama_perusahaan = nama_perusahaan;
        this.jenis_usaha = jenis_usaha;
        this.alamat = alamat;
        this.telp = telp;
        this.deskripsi = deskripsi;
        this.kota = kota;
        this.total_saham = total_saham;
        this.harga = harga;
    }

    public String getNama_pemilik() {
        return nama_pemilik;
    }

    public void setNama_pemilik(String nama_pemilik) {
        this.nama_pemilik = nama_pemilik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pemilik() {
        return id_pemilik;
    }

    public void setId_pemilik(int id_pemilik) {
        this.id_pemilik = id_pemilik;
    }

    public String getNama_perusahaan() {
        return nama_perusahaan;
    }

    public void setNama_perusahaan(String nama_perusahaan) {
        this.nama_perusahaan = nama_perusahaan;
    }

    public String getJenis_usaha() {
        return jenis_usaha;
    }

    public void setJenis_usaha(String jenis_usaha) {
        this.jenis_usaha = jenis_usaha;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public int getTotal_saham() {
        return total_saham;
    }

    public void setTotal_saham(int total_saham) {
        this.total_saham = total_saham;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
