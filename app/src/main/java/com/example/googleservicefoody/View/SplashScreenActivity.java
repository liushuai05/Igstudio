package com.example.googleservicefoody.View;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.googleservicefoody.R;


public class SplashScreenActivity extends AppCompatActivity {


    TextView txtVersion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spashscreen);


        addControl();

        addEvent();

    }


    private void addEvent() {

    }

    private void addControl() {
        txtVersion = findViewById(R.id.txtVersion);
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(),0);
            txtVersion.setText("Version " + packageInfo.versionName);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent iLogin = new Intent(SplashScreenActivity.this,DangNhapActivity.class);
                    startActivity(iLogin);
                    finish();
                }
            },2000);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }


}
