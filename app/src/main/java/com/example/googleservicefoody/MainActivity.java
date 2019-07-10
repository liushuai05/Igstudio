package com.example.googleservicefoody;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapter.AdapterUser;
import Model.UserModel;

public class MainActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {


    Button btnThemDuLieu, btnDocDuLieu;
    TextView txtHienThi;
    FirebaseDatabase database; // = FirebaseDatabase.getInstance();
    DatabaseReference myRef; // = database.getReference("message");
    ValueEventListener postListener;

    FirebaseAuth mAuth;

    //bo3 listview
    ListView lvUser;
    ArrayList<UserModel> userModels;
    AdapterUser adapterUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addControl();

        addEvent();
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(this);
    }

    private void addEvent() {
        btnThemDuLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnDocDuLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void addControl() {
        btnThemDuLieu = findViewById(R.id.btnThemDuLieu);
        btnDocDuLieu = findViewById(R.id.btnDocDuLieu);
        lvUser = findViewById(R.id.lvUser);
        txtHienThi = findViewById(R.id.txtHienThi);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("users");


        userModels = new ArrayList<UserModel>();
        adapterUser = new AdapterUser(MainActivity.this, R.layout.item_user, userModels);
        lvUser.setAdapter(adapterUser);

        mAuth.createUserWithEmailAndPassword("mkig@gmail.com","123456").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(MainActivity.this, "Tao TK Thanh cong",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

    }

//    @Override
//    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        userModels.clear();
//        Log.d("kiemtra", dataSnapshot.toString());
//        Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();
//        for (DataSnapshot data : nodeChild) {
//            UserModel user = data.getValue(UserModel.class);
//            userModels.add(user);
//            adapterUser.notifyDataSetChanged();
//        }
//    }

}
