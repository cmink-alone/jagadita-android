package com.example.uts.jagadita.models;

public class Transaksi {
    private int id_pengguna;
    private int id_perusahaan;
    private String nama_pembeli;
    private String nama_perusahaan;
    private int total_beli;
    private String tanggal;
    private String status;
    private String midtrans_transaction_id;

    public Transaksi(String nama_pembeli, String nama_perusahaan, int total_beli, String tanggal, String status) {
        this.nama_pembeli = nama_pembeli;
        this.nama_perusahaan = nama_perusahaan;
        this.total_beli = total_beli;
        this.tanggal = tanggal;
        this.status = status;
    }


    public Transaksi(int id_pengguna, int id_perusahaan, int total_beli, String status, String midtrans_transaction_id) {
        this.id_pengguna = id_pengguna;
        this.id_perusahaan = id_perusahaan;
        this.total_beli = total_beli;
        this.status = status;
        this.midtrans_transaction_id = midtrans_transaction_id;
    }

    public int getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(int id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    public int getId_perusahaan() {
        return id_perusahaan;
    }

    public void setId_perusahaan(int id_perusahaan) {
        this.id_perusahaan = id_perusahaan;
    }

    public String getNama_pembeli() {
        return nama_pembeli;
    }

    public void setNama_pembeli(String nama_pembeli) {
        this.nama_pembeli = nama_pembeli;
    }

    public int getTotal_beli() {
        return total_beli;
    }

    public void setTotal_beli(int total_beli) {
        this.total_beli = total_beli;
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
