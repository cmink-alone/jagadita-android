package com.example.uts.jagadita.models;

public class Donasi {
    private int id_donatur;
    private int id_perusahaan;
    private String nama_donatur;
    private String nama_perusahaan;
    private int total_donasi;
    private String tanggal;
    private String status;
    private String midtrans_transaction_id;

    public Donasi(String nama_donatur, String nama_perusahaan, int total_donasi, String tanggal, String status) {
        this.nama_donatur = nama_donatur;
        this.nama_perusahaan = nama_perusahaan;
        this.total_donasi = total_donasi;
        this.tanggal = tanggal;
        this.status = status;
    }


    public Donasi(int id_donatur, int id_perusahaan, int total_donasi, String status, String midtrans_transaction_id) {
        this.id_donatur = id_donatur;
        this.id_perusahaan = id_perusahaan;
        this.total_donasi = total_donasi;
        this.status = status;
        this.midtrans_transaction_id = midtrans_transaction_id;
    }

    public int getId_pengguna() {
        return id_donatur;
    }

    public void setId_pengguna(int id_donatur) {
        this.id_donatur = id_donatur;
    }

    public int getId_perusahaan() {
        return id_perusahaan;
    }

    public void setId_perusahaan(int id_perusahaan) {
        this.id_perusahaan = id_perusahaan;
    }

    public String getNama_donatur() {
        return nama_donatur;
    }

    public void setNama_donatur(String nama_donatur) {
        this.nama_donatur = nama_donatur;
    }

    public int getTotal_beli() {
        return total_donasi;
    }

    public void setTotal_beli(int total_donasi) {
        this.total_donasi = total_donasi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMidtrans_transaction_id() {
        return midtrans_transaction_id;
    }

    public void setMidtrans_transaction_id(String midtrans_transaction_id) {
        this.midtrans_transaction_id = midtrans_transaction_id;
    }

    public String getNama_perusahaan() {
        return nama_perusahaan;
    }

    public void setNama_perusahaan(String nama_perusahaan) {
        this.nama_perusahaan = nama_perusahaan;
    }
}
