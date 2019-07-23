package com.example.googleservicefoody.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googleservicefoody.Model.QuanAnModel;
import com.example.googleservicefoody.R;

import java.util.ArrayList;

public class AdapterRecycleDiaDiem extends RecyclerView.Adapter<AdapterRecycleDiaDiem.ViewHolder> {

    ArrayList<QuanAnModel> arrayListQuanAn;
    int resource;

    public AdapterRecycleDiaDiem(ArrayList<QuanAnModel> arrayListQuanAn, int resource) {
        this.arrayListQuanAn = arrayListQuanAn;
        this.resource = resource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenQuanAnDiaDiem,txtDiaChiDiaDiem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDiaChiDiaDiem = itemView.findViewById(R.id.txtDiaChiDiaDiem);
            txtTenQuanAnDiaDiem = itemView.findViewById(R.id.txtTenQuanAnDiaDiem);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycleDiaDiem.ViewHolder holder, int position) {
        QuanAnModel quanAnModel = arrayListQuanAn.get(position);
        holder.txtTenQuanAnDiaDiem.setText(quanAnModel.getTenquanan());
    }

    @Override
    public int getItemCount() {
        return arrayListQuanAn.size();
    }


}
