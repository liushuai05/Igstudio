package com.example.googleservicefoody.Controller;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googleservicefoody.Adapter.AdapterRecycleDiaDiem;
import com.example.googleservicefoody.Controller.Interfaces.DiaDiemInterface;
import com.example.googleservicefoody.Model.QuanAnModel;
import com.example.googleservicefoody.R;

import java.util.ArrayList;

public class DiaDiemController {
    Context context;
    QuanAnModel quanAnModel;
    public DiaDiemController(Context context) {
        this.context = context;
        quanAnModel = new QuanAnModel();
    }

    public void getDanhSachQuanAncontroller(RecyclerView recyclerViewDiaDiem) {
        final ArrayList<QuanAnModel> arrQuanAn = new ArrayList<>();
        final AdapterRecycleDiaDiem adapterRecycleDiaDiem = new AdapterRecycleDiaDiem(arrQuanAn, R.layout.layout_custom_recycleview_diadiem);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewDiaDiem.setLayoutManager(layoutManager);
        recyclerViewDiaDiem.setAdapter(adapterRecycleDiaDiem);
        DiaDiemInterface diaDiemInterface = new DiaDiemInterface() {
            @Override
            public void getDanhSachQuanAnModel(QuanAnModel quanAnModel) {
                arrQuanAn.add(quanAnModel);
                adapterRecycleDiaDiem.notifyDataSetChanged();
            }
        };
        quanAnModel.getDanhSachQuanAn(diaDiemInterface);
    }
}
