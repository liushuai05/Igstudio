package com.example.googleservicefoody.Controller;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googleservicefoody.Adapter.AdapterRecycleDiaDiem;
import com.example.googleservicefoody.Controller.Interfaces.DiaDiemInterface;
import com.example.googleservicefoody.Model.QuanAnModel;
import com.example.googleservicefoody.R;

import java.util.ArrayList;

public class DiaDiemController {
    ArrayList<QuanAnModel> arrQuanAn;
    AdapterRecycleDiaDiem adapterRecycleDiaDiem;
    Context context;
    QuanAnModel quanAnModel;

    public DiaDiemController(Context context) {
        this.context = context;
        quanAnModel = new QuanAnModel(); //khởi tạo
    }

    public void getDanhSachQuanAncontroller(RecyclerView recyclerViewDiaDiem) {
        arrQuanAn = new ArrayList<>();
        adapterRecycleDiaDiem = new AdapterRecycleDiaDiem(arrQuanAn, R.layout.layout_custom_recycleview_diadiem);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewDiaDiem.setLayoutManager(layoutManager);
        recyclerViewDiaDiem.setAdapter(adapterRecycleDiaDiem);
        DiaDiemInterface diaDiemInterface = new DiaDiemInterface() {
            @Override
            public void getDanhSachQuanAnModel(QuanAnModel quanAnModel) {
                Log.d("kiemtra", quanAnModel.toString());
                arrQuanAn.add(quanAnModel);
                adapterRecycleDiaDiem.notifyDataSetChanged();
            }
        };
        quanAnModel.getDanhSachQuanAn(diaDiemInterface);
    }
}
