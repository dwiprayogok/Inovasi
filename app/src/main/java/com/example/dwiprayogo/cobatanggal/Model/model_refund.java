package com.example.dwiprayogo.cobatanggal.Model;

/**
 * Created by dwi.prayogo on 11/15/2017.
 */

public class model_refund {

    String nama,posisi,tanggal,status;

    public model_refund(){

    }

    public model_refund(String nama,String posisi, String tanggal, String status){
        this.nama= nama;
        this.posisi = posisi;
        this.tanggal = tanggal;
        this.status = status;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public String getPosisi() {
        return posisi;
    }

    public String getStatus() {
        return status;
    }

    public String getTanggal() {
        return tanggal;
    }
}
