package com.example.googleservicefoody.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.googleservicefoody.Adapter.AdapterViewPagerTrangChu;
import com.example.googleservicefoody.R;

public class TrangChuActivity extends AppCompatActivity {

    ViewPager viewPagerTrangChu;
    AdapterViewPagerTrangChu adapterViewPagerTrangChu;
    RadioButton rdDiaDiem, rdSuggestion;
    RadioGroup rdGroupTrangChu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        addControl();

        addEvent();

    }

    private void addEvent() {
        viewPagerTrangChu.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rdDiaDiem.setChecked(true);
                        break;
                    case 1:
                        rdSuggestion.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rdGroupTrangChu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rdDiaDiem:
                        viewPagerTrangChu.setCurrentItem(0,true);
                        break;
                    case R.id.rdSuggestion:
                        viewPagerTrangChu.setCurrentItem(1,true);
                        break;
                }
            }
        });
    }

    private void addControl() {
        rdDiaDiem = findViewById(R.id.rdDiaDiem);
        rdSuggestion = findViewById(R.id.rdSuggestion);
        rdGroupTrangChu = findViewById(R.id.rdGroupTrangChu);
        viewPagerTrangChu = findViewById(R.id.viewPager_trangchu);
        adapterViewPagerTrangChu = new AdapterViewPagerTrangChu(getSupportFragmentManager());
        viewPagerTrangChu.setAdapter(adapterViewPagerTrangChu);

    }
}
