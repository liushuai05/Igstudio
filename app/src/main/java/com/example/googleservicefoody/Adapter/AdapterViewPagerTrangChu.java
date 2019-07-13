package com.example.googleservicefoody.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.googleservicefoody.View.Flagments.DiaDiemFragment;
import com.example.googleservicefoody.View.Flagments.SuggestionFragment;

public class AdapterViewPagerTrangChu extends FragmentStatePagerAdapter {

    SuggestionFragment suggestionFragment;
    DiaDiemFragment diaDiemFragment;

    public AdapterViewPagerTrangChu(FragmentManager fm) {
        super(fm);
        suggestionFragment = new SuggestionFragment();
        diaDiemFragment = new DiaDiemFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return diaDiemFragment;
            case 1:
                return suggestionFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
