package com.example.googleservicefoody.Model;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String ten;
    private Double tuoi;
    private boolean sex;

    public UserModel() {
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "ten='" + ten + '\'' +
                ", tuoi=" + tuoi +
                ", sex=" + sex +
                '}';
    }

    public UserModel(String ten, Double tuoi, boolean sex) {
        this.ten = ten;
        this.tuoi = tuoi;
        this.sex = sex;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Double getTuoi() {
        return tuoi;
    }

    public void setTuoi(Double tuoi) {
        this.tuoi = tuoi;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
