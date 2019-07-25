package com.example.googleservicefoody.View;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.googleservicefoody.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;


public class SplashScreenActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    TextView txtVersion;
    GoogleApiClient googleApiClient;
    public final int REQUEST_PERMISSION_LOCATION = 1;
    SharedPreferences sharedPreferences;

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
        sharedPreferences = getSharedPreferences("toado", MODE_PRIVATE);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        int checkPermissionCoarseLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (checkPermissionCoarseLocation != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_LOCATION);
        } else {
            googleApiClient.connect();
        }
        Location vitriHienTai = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    googleApiClient.connect();
                }
                break;

        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        @SuppressLint("MissingPermission") Location vitriHienTai = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (vitriHienTai != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("latitude", String.valueOf(vitriHienTai.getLatitude()));
            editor.putString("longitude", String.valueOf(vitriHienTai.getLongitude()));
            editor.commit();
            Log.d("toadosplashscreen", vitriHienTai.getLatitude() + "");
            try {
                PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                txtVersion.setText("Version " + packageInfo.versionName);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent iLogin = new Intent(SplashScreenActivity.this, DangNhapActivity.class);
                        startActivity(iLogin);
                        finish();
                    }
                }, 2000);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(this,"Vui lòng bật tính năng GPS",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}
