package com.example.uts.jagadita.models;

public class Pengguna {
    private int id;
    private String username;
    private String password;
    private String nama;
    private String telp;
    private String alamat;
    private String pekerjaan;
    private String tanggal_lahir;
    private String jenis_kelamin;

    public Pengguna(int id, String username, String password, String nama, String telp, String alamat, String pekerjaan, String tanggal_lahir, String jenis_kelamin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.telp = telp;
        this.alamat = alamat;
        this.pekerjaan = pekerjaan;
        this.tanggal_lahir = tanggal_lahir;
        this.jenis_kelamin = jenis_kelamin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

}
