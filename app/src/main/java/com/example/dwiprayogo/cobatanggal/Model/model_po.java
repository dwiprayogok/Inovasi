package com.example.dwiprayogo.cobatanggal.Model;

/**
 * Created by dwi.prayogo on 11/22/2017.
 */

public class model_po {
    String nama,nokontrak,appid,asset,status,paiddate;

    public model_po(){

    }

    public model_po(String nama,String nokontrak, String appid,String asset, String status, String paiddate){
        this.nama= nama;
        this.nokontrak = nokontrak;
        this.appid = appid;
        this.asset= asset;
        this.status = status;
        this.paiddate = paiddate;

    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setNokontrak(String nokontrak) {
        this.nokontrak = nokontrak;
    }

    public void setPaiddate(String paiddate) {
        this.paiddate = paiddate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }


    public String getNama() {
        return nama;
    }

    public String getAppid() {
        return appid;
    }

    public String getNokontrak() {
        return nokontrak;
    }

    public String getPaiddate() {
        return paiddate;
    }

    public String getStatus() {
        return status;
    }

    public String getAsset() {
        return asset;
    }

}
