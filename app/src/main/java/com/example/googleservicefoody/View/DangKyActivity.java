package com.example.googleservicefoody.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.googleservicefoody.Controller.DangKyController;
import com.example.googleservicefoody.Model.ThanhVienModel;
import com.example.googleservicefoody.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangKyActivity extends AppCompatActivity {
    EditText txtEmail, txtPass, txtPass2;
    Button btnDangKy, btnThoat;
    private FirebaseAuth mAuth;
    private String TAG = "Check register ";
    String email, pass , pass2;
    ProgressDialog p;

    DangKyController d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        addControl();
        addEvent();
    }

    private void addEvent() {
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangKy();
            }
        });
    }

    private void addControl() {
        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        txtPass2 = findViewById(R.id.txtPass2);
        btnDangKy = findViewById(R.id.btnDangKy);
        btnThoat = findViewById(R.id.btnThoat);
        p = new ProgressDialog(this,R.style.AppCompatAlertDialogStyle);
        mAuth = FirebaseAuth.getInstance();
    }

    private void dangKy() {
        p.setMessage(getString(R.string.dangxuly));
        p.show();
        email = txtEmail.getText().toString();
        pass = txtPass.getText().toString();
        pass2 = txtPass2.getText().toString();
        if(email.trim().length() == 0 ){
            Toast.makeText(this,"Tài khoản không hợp lệ",Toast.LENGTH_SHORT).show();
        }else if(pass.trim().length() == 0){
            Toast.makeText(this,"Mật khẩu quá ngắn",Toast.LENGTH_SHORT).show();
        }else if(!pass2.equals(pass)){
            Toast.makeText(this,"Mật khẩu bạn nhập không khớp",Toast.LENGTH_SHORT).show();
        }else{
            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        p.dismiss();
                        ThanhVienModel thanhVienModel = new ThanhVienModel();
                        thanhVienModel.setHoten(email);
                        thanhVienModel.setHinhanh("user.png");
                        String uid = task.getResult().getUser().getUid();
                        d = new DangKyController();
                        d.ThemThongTinThanhVienController(thanhVienModel,uid);
                        Toast.makeText(DangKyActivity.this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
