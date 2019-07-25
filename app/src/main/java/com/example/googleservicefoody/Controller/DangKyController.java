package com.example.googleservicefoody.Controller;

import com.example.googleservicefoody.Model.ThanhVienModel;

public class DangKyController {
    ThanhVienModel thanhVienModel;
    public DangKyController() {
        thanhVienModel = new ThanhVienModel();
    }

    public void ThemThongTinThanhVienController(ThanhVienModel t, String uid) {
        thanhVienModel.ThemThongTinThanhVien(t,uid);
    }
}
