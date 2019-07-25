package com.example.googleservicefoody.View.Flagments;


import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googleservicefoody.Controller.DiaDiemController;
import com.example.googleservicefoody.R;

public class DiaDiemFragment extends Fragment {
    DiaDiemController diaDiemController;
    RecyclerView recyclerView;
    ProgressBar p;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_diadiem, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        p = view.findViewById(R.id.progressbarLoadData);
        recyclerView.setNestedScrollingEnabled(false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        sharedPreferences = getContext().getSharedPreferences("toado", Context.MODE_PRIVATE);

        Location vitriHienTai = new Location("");
        vitriHienTai.setLatitude(Double.parseDouble(sharedPreferences.getString("latitude",null)));
        vitriHienTai.setLongitude(Double.parseDouble(sharedPreferences.getString("longitude",null)));

        Log.d("kiemtratoado",sharedPreferences.getString("latitude",null) + "");
        recyclerView.setNestedScrollingEnabled(false);
        diaDiemController = new DiaDiemController(getContext());
        diaDiemController.getDanhSachQuanAncontroller(recyclerView,p,vitriHienTai);
    }
}
