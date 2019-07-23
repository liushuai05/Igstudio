package com.example.googleservicefoody.Model;

import androidx.annotation.NonNull;

import com.example.googleservicefoody.Controller.Interfaces.DiaDiemInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class QuanAnModel implements Serializable {

    private boolean giaohang;
    private String giodongcua, giomocua, tenquanan, videogioithieu, maquanan;
    private ArrayList<String> tienich;
    private long luotthich;

    DatabaseReference nodeRoot;

    @Override
    public String toString() {
        return "QuanAnModel{" +
                "giaohang=" + giaohang +
                ", giodongcua='" + giodongcua + '\'' +
                ", giomocua='" + giomocua + '\'' +
                ", tenquanan='" + tenquanan + '\'' +
                ", videogioithieu='" + videogioithieu + '\'' +
                ", maquanan='" + maquanan + '\'' +
                ", tienich=" + tienich +
                ", luotthich=" + luotthich +
                '}';
    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public QuanAnModel() {
        nodeRoot = FirebaseDatabase.getInstance().getReference();
    }

    public QuanAnModel(boolean giaohang, String giodongcua, String giomocua, String tenquanan, String videogioithieu, String maquanan, ArrayList<String> tienich) {
        this.giaohang = giaohang;
        this.giodongcua = giodongcua;
        this.giomocua = giomocua;
        this.tenquanan = tenquanan;
        this.videogioithieu = videogioithieu;
        this.maquanan = maquanan;
        this.tienich = tienich;
    }

    public boolean isGiaohang() {
        return giaohang;
    }

    public void setGiaohang(boolean giaohang) {
        this.giaohang = giaohang;
    }

    public String getGiodongcua() {
        return giodongcua;
    }

    public void setGiodongcua(String giodongcua) {
        this.giodongcua = giodongcua;
    }

    public String getGiomocua() {
        return giomocua;
    }

    public void setGiomocua(String giomocua) {
        this.giomocua = giomocua;
    }

    public String getTenquanan() {
        return tenquanan;
    }

    public void setTenquanan(String tenquanan) {
        this.tenquanan = tenquanan;
    }

    public String getVideogioithieu() {
        return videogioithieu;
    }

    public void setVideogioithieu(String videogioithieu) {
        this.videogioithieu = videogioithieu;
    }

    public String getMaquanan() {
        return maquanan;
    }

    public void setMaquanan(String maquanan) {
        this.maquanan = maquanan;
    }

    public ArrayList<String> getTienich() {
        return tienich;
    }

    public void setTienich(ArrayList<String> tienich) {
        this.tienich = tienich;
    }

    public void getDanhSachQuanAn(final DiaDiemInterface diaDiemInterface) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataQuanAn = dataSnapshot.child("quanans");
                for (DataSnapshot data : dataQuanAn.getChildren()) {
                    QuanAnModel quanAnModel = data.getValue(QuanAnModel.class);
                    diaDiemInterface.getDanhSachQuanAnModel(quanAnModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        nodeRoot.addListenerForSingleValueEvent(valueEventListener);
    }
}
