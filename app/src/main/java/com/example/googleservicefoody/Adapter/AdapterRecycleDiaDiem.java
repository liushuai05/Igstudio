package com.example.googleservicefoody.Adapter;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googleservicefoody.Model.BinhLuanModel;
import com.example.googleservicefoody.Model.ChiNhanhQuanAnModel;
import com.example.googleservicefoody.Model.QuanAnModel;
import com.example.googleservicefoody.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRecycleDiaDiem extends RecyclerView.Adapter<AdapterRecycleDiaDiem.ViewHolder> {

    ArrayList<QuanAnModel> arrayListQuanAn;
    int resource;

    public AdapterRecycleDiaDiem(ArrayList<QuanAnModel> arrayListQuanAn, int resource) {
        this.arrayListQuanAn = arrayListQuanAn;
        this.resource = resource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenQuanAnDiaDiem, txtDiaChiDiaDiem, txtTieuDe, txtTieuDe2, txtNoiDung, txtNoiDung2,
                txtDiemDanhGia, txtDiemDanhGia2, txtTongBinhLuan, txtTongHinhAnh, txtDiemTrungBinh,
                txtKhoangCach;
        Button btnDatHang;
        ImageView imgHinhQuanAn;
        CircleImageView cycleImageUser, cycleImageUser2;
        LinearLayout containerBinhLuan, containerBinhLuan2;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDiaChiDiaDiem = itemView.findViewById(R.id.txtDiaChiDiaDiem);
            txtTenQuanAnDiaDiem = itemView.findViewById(R.id.txtTenQuanAnDiaDiem);
            txtTieuDe = itemView.findViewById(R.id.txtTieuDe);
            txtTieuDe2 = itemView.findViewById(R.id.txtTieuDe2);
            txtNoiDung = itemView.findViewById(R.id.txtNoiDung);
            txtNoiDung2 = itemView.findViewById(R.id.txtNoiDung2);
            txtDiemDanhGia = itemView.findViewById(R.id.txtDiemDanhGia);
            txtDiemDanhGia2 = itemView.findViewById(R.id.txtDiemDanhGia2);
            txtTongHinhAnh = itemView.findViewById(R.id.txtTongHinhAnh);
            txtTongBinhLuan = itemView.findViewById(R.id.tongBL);
            btnDatHang = itemView.findViewById(R.id.btnDatHang);
            imgHinhQuanAn = itemView.findViewById(R.id.imgHinhQuanAn);
            cycleImageUser = itemView.findViewById(R.id.cycleImageUser);
            cycleImageUser2 = itemView.findViewById(R.id.cycleImageUser2);
            containerBinhLuan = itemView.findViewById(R.id.containerBinhLuan);
            containerBinhLuan2 = itemView.findViewById(R.id.containerBinhLuan2);
            txtDiemTrungBinh = itemView.findViewById(R.id.txtDiemTrungBinh);
            txtKhoangCach = itemView.findViewById(R.id.txtKhoangCach);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecycleDiaDiem.ViewHolder holder, int position) {
        QuanAnModel quanAnModel = arrayListQuanAn.get(position);
        holder.txtTenQuanAnDiaDiem.setText(quanAnModel.getTenquanan());

        //button giao hang
        if (quanAnModel.isGiaohang()) {
            holder.btnDatHang.setVisibility(View.VISIBLE);
        }

        //hinh anh quan an
        if (quanAnModel.getHinhAnhQuanAn().size() > 0) {
            StorageReference storageHinhAnh = FirebaseStorage.getInstance().getReference().child("hinhanh").child(quanAnModel.getHinhAnhQuanAn().get(0));
            long ONE_MEGABYTE = 1024 * 1024;
            storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    holder.imgHinhQuanAn.setImageBitmap(b);
                }
            });
        }

        //binh luan quan an
        if (quanAnModel.getBinhLuanList().size() > 0) {


            //Load Binh Luan 1
            BinhLuanModel b = quanAnModel.getBinhLuanList().get(0);
            holder.txtTieuDe.setText(b.getTieude());
            holder.txtNoiDung.setText(b.getNoidung());
            holder.txtDiemDanhGia.setText(b.getChamdiem() + "");

            //laydstongbinhluan
            holder.txtTongBinhLuan.setText((quanAnModel.getBinhLuanList().size() - 2) + "");

            //Load Binh Luan 2
            BinhLuanModel b2 = quanAnModel.getBinhLuanList().get(1);
            holder.txtTieuDe2.setText(b2.getTieude());
            holder.txtNoiDung2.setText(b2.getNoidung());
            holder.txtDiemDanhGia2.setText(b2.getChamdiem() + "");


            //set hinh anh dai dien
//            setHinhAnhBinhLuan(holder.cycleImageUser2, b2.getTv().getHinhanh());
//            setHinhAnhBinhLuan(holder.cycleImageUser, b.getTv().getHinhanh());
            int tongsoHinhAnh = 0;
            double tmpDiem = 0d;
            for (BinhLuanModel binhLuanModel1 : quanAnModel.getBinhLuanList()) {
                tongsoHinhAnh += binhLuanModel1.getHinhanh().size();
                tmpDiem += binhLuanModel1.getChamdiem();
            }
            double tmpDiemTrungBinh = tmpDiem / quanAnModel.getBinhLuanList().size();
            holder.txtTongBinhLuan.setText(tongsoHinhAnh + "");
            holder.txtDiemTrungBinh.setText(String.format("%.2f", tmpDiemTrungBinh));
        } else {
            holder.containerBinhLuan.setVisibility(View.GONE);
            holder.containerBinhLuan2.setVisibility(View.GONE);
            holder.txtTongBinhLuan.setText("0");
            holder.txtTongHinhAnh.setText("0");
        }

        //lay chi nhanh
        if (quanAnModel.getChiNhanhQuanAnList().size() > 0) {
            ChiNhanhQuanAnModel chiNhanhQuanAnModeltmp = quanAnModel.getChiNhanhQuanAnList().get(0);
            for (ChiNhanhQuanAnModel c : quanAnModel.getChiNhanhQuanAnList()) {
                if (chiNhanhQuanAnModeltmp.getKhoangcach() > c.getKhoangcach()) {
                    chiNhanhQuanAnModeltmp = c;
                }
            }
            holder.txtDiaChiDiaDiem.setText(chiNhanhQuanAnModeltmp.getDiachi());
            holder.txtKhoangCach.setText(String.format("%.1f",chiNhanhQuanAnModeltmp.getKhoangcach()) + " km");
        }
    }

    private void setHinhAnhBinhLuan(final CircleImageView c, String path) {
        StorageReference storageHinhUser = FirebaseStorage.getInstance().getReference().child("thanhvien").child(path);
        long ONE_MEGABYTE = 1024 * 1024;
        storageHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                c.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListQuanAn.size();
    }


}
