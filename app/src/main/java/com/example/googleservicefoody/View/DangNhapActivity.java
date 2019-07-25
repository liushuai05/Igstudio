package com.example.googleservicefoody.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.googleservicefoody.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;
import java.util.List;


public class DangNhapActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, FirebaseAuth.AuthStateListener {

    //view controller
    private TextView txtDangKy,txtQuenMatKhau;
    private Button btnGoogle, btnDangNhap;
    private LoginButton btnFacebook;
    EditText txtEmailUser, txtPassUser;



    //firebase api
    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private GoogleApiClient apiClient;
    SharedPreferences sharedPreferences;
    LoginManager loginManager;
    public static int REQUESTCODE_DANGNHAP_GOOGLE = 99;
    public static int KIEMTRA_PROVIDER_DANGNHAP = 0;

    private String TAG = "You are Loginning Facebook Account";
    private String email, password;
    List<String> permissionFacebook = Arrays.asList("email","public_profile");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_dangnhap);
        addControl();
        addEvent();
    }

    private void addControl() {
        txtDangKy = findViewById(R.id.txtDangKy);
        txtQuenMatKhau = findViewById(R.id.txtQuenMatKhau);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnFacebook = findViewById(R.id.btnFacebook);
        txtEmailUser = findViewById(R.id.txtEmailUser);
        txtPassUser = findViewById(R.id.txtPassUser);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();

        callbackManager = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();
    }

    private void addEvent() {
        btnGoogle.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
        btnFacebook.setOnClickListener(this);
        txtDangKy.setOnClickListener(this);
        txtQuenMatKhau.setOnClickListener(this);
        sharedPreferences = getSharedPreferences("luudangnhap",MODE_PRIVATE);
        TaoClientDangNhapGoogle();
    }

    private void TaoClientDangNhapGoogle() {
        //Yeu cau lay email va token cua user
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //Ketnoi google api client
        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
    }

    //Mở form đăng nhập bằng google
    private void DangNhapGoogle(GoogleApiClient apiClient){
        KIEMTRA_PROVIDER_DANGNHAP = 1;
        Intent iDNGoogle = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(iDNGoogle,REQUESTCODE_DANGNHAP_GOOGLE);
    }

    private void DangNhapFacebook(){
        loginManager.logInWithReadPermissions(this,permissionFacebook);
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                KIEMTRA_PROVIDER_DANGNHAP = 2;
                String tokenID = loginResult.getAccessToken().getToken();
                ChungThucDangNhapFireBase(tokenID);
            }

            @Override
            public void onCancel() {
                Toast.makeText(DangNhapActivity.this, "Bạn vừa hủy, vui lòng đăng nhập bằng facebook lại", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(DangNhapActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void ChungThucDangNhapFireBase(String tokenID) {
        if(KIEMTRA_PROVIDER_DANGNHAP == 1){
            AuthCredential authCredential = GoogleAuthProvider.getCredential(tokenID,null);
            firebaseAuth.signInWithCredential(authCredential);
        }else if(KIEMTRA_PROVIDER_DANGNHAP == 2){
            AuthCredential authCredential = FacebookAuthProvider.getCredential(tokenID);
            firebaseAuth.signInWithCredential(authCredential);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUESTCODE_DANGNHAP_GOOGLE){
            if(resultCode == RESULT_OK){
                GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount account = signInResult.getSignInAccount();
                String tokenID = account.getIdToken();
                ChungThucDangNhapFireBase(tokenID);
            }
        }else{
            callbackManager.onActivityResult(requestCode,resultCode,data);
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnGoogle:
                DangNhapGoogle(apiClient);
                break;

            case R.id.btnFacebook:
                DangNhapFacebook();
                break;

            case R.id.txtDangKy:
                Intent iDangKy = new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(iDangKy);
                break;

            case R.id.btnDangNhap:
                DangNhap();
                break;
//
//            case R.id.txtQuenMatKhau:
//                Intent iKhoiPhucMatKhau = new Intent(DangNhapActivity.this,KhoiPhucMatKhauActivity.class);
//                startActivity(iKhoiPhucMatKhau);
//                break;
        }
    }

    private void DangNhap() {
        email = txtEmailUser.getText().toString();
        password = txtPassUser.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(DangNhapActivity.this,"LOGIN FAILED, PLEASE CHECK YOUR ID AND PASSWORD",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this,"Please check your connect internet", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("mauser",user.getUid());
            editor.commit();

            Intent iTrangChu = new Intent(this,TrangChuActivity.class);
            startActivity(iTrangChu);
        }else{
        }
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
}
