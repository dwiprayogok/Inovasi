package com.example.dwiprayogo.cobatanggal.Model;

/**
 * Created by dwi.prayogo on 11/22/2017.
 */

public class model_apptracj {

    String nama,nokontrak,appid,asset,appstep,nextstep;

    public model_apptracj(){

    }

    public model_apptracj(String nama,String nokontrak, String appid,String asset, String appstep, String nextstep){
        this.nama= nama;
        this.nokontrak = nokontrak;
        this.appid = appid;
        this.asset= asset;
        this.appstep = appstep;
        this.nextstep = nextstep;

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

    public void setAppstep(String appstep) {
        this.appstep = appstep;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public void setNextstep(String nextstep) {
        this.nextstep = nextstep;
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

    public String getAppstep() {
        return appstep;
    }

    public String getAsset() {
        return asset;
    }

    public String getNextstep() {
        return nextstep;
    }
}
