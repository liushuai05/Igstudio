package com.example.googleservicefoody.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class ThanhVienModel implements Serializable {
    private DatabaseReference dataNodeThanhVien;
    String hoten, hinhanh, mathanhvien;

    public ThanhVienModel() {
        dataNodeThanhVien = FirebaseDatabase.getInstance().getReference().child("thanhviens");
    }

    public String getHoten() {
        return hoten;
    }

    public String getMathanhvien(){
        return mathanhvien;
    }

    public void setMathanhvien(String mathanhvien) {
        this.mathanhvien = mathanhvien;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public ThanhVienModel(String hoten, String hinhanh) {
        this.hoten = hoten;
        this.hinhanh = hinhanh;
    }

    public void ThemThongTinThanhVien(ThanhVienModel thanhVienModel, String uid) {
        dataNodeThanhVien.child(uid).setValue(thanhVienModel);
    }
}
