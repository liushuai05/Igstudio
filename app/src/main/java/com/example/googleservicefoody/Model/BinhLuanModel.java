package com.example.googleservicefoody.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class BinhLuanModel implements Serializable {
    private long luotthich, chamdiem,mauser;
    ThanhVienModel tv;
    private String noidung,tieude;

    public String getMabinhluan() {
        return mabinhluan;
    }

    public void setMabinhluan(String mabinhluan) {
        this.mabinhluan = mabinhluan;
    }

    String mabinhluan;

    public ArrayList<String> getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(ArrayList<String> hinhanh) {
        this.hinhanh = hinhanh;
    }

    ArrayList<String> hinhanh;


    public BinhLuanModel() {
    }

    public BinhLuanModel(long luotthich, long chamdiem, ThanhVienModel tv, String noidung, String tieude, long mauser) {
        super();
        this.luotthich = luotthich;
        this.chamdiem = chamdiem;
        this.tv = tv;
        this.noidung = noidung;
        this.tieude = tieude;
        this.mauser = mauser;
    }

    public long getLuotthich() {
        return luotthich;
    }

    public long getMauser(){
        return mauser;
    }

    public void  setMauser(long mauser){
        this.mauser = mauser;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public long getChamdiem() {
        return chamdiem;
    }

    public void setChamdiem(long chamdiem) {
        this.chamdiem = chamdiem;
    }

    public ThanhVienModel getTv() {
        return tv;
    }

    public void setTv(ThanhVienModel tv) {
        this.tv = tv;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }


}
