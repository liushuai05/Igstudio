package com.example.googleservicefoody.View.Flagments;


import android.os.Bundle;
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
        recyclerView.setNestedScrollingEnabled(false);
        diaDiemController = new DiaDiemController(getContext());
        diaDiemController.getDanhSachQuanAncontroller(recyclerView,p);
    }
}
