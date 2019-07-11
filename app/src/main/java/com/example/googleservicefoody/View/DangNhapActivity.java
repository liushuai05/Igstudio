package com.example.googleservicefoody.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.googleservicefoody.R;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class DangNhapActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, FirebaseAuth.AuthStateListener {


    TextView txtDangKy;
    Button btnGoogle;
    GoogleApiClient googleApiClient;
    FirebaseAuth firebaseAuth;

    public static int KIEMTRA_PROVIDER_LOGIN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        addControl();

        addEvent();
    }

    private void addControl() {
        txtDangKy = findViewById(R.id.txtDangKy);
        btnGoogle = findViewById(R.id.btnGoogle);
        firebaseAuth = FirebaseAuth.getInstance();
        taoClientDangNhapGoogle();
    }

    private void taoClientDangNhapGoogle() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    private void addEvent() {
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(view);
            }
        });

        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenDangKy = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intenDangKy);
            }
        });

    }


    private void signIn(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnGoogle:
                dangNhapGoogle(googleApiClient);
                break;
        }
    }


    private void dangNhapGoogle(GoogleApiClient googleApiClient) {
        KIEMTRA_PROVIDER_LOGIN = 1;
        Intent iDangNhapGoogle = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(iDangNhapGoogle, 20);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20 && resultCode == RESULT_OK) {
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();
            String tokenID = googleSignInAccount.getIdToken();
            chungThucDangNhap(tokenID);
        }
    }


    private void chungThucDangNhap(String tokenID) {
        if (KIEMTRA_PROVIDER_LOGIN == 1) {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(tokenID, null);
            firebaseAuth.signInWithCredential(authCredential);
        }
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            Toast.makeText(this,
                    "Dang nhap thanh cong " + firebaseUser,
                    Toast.LENGTH_LONG).show();
        } else Toast.makeText(this,
                "Dang nhap that bai",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
