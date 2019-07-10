package com.example.googleservicefoody;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapter.AdapterUser;
import Model.UserModel;

public class MainActivity extends AppCompatActivity implements ValueEventListener {


    Button btnThemDuLieu, btnDocDuLieu;
    TextView txtHienThi;
    FirebaseDatabase database; // = FirebaseDatabase.getInstance();
    DatabaseReference myRef; // = database.getReference("message");
    ValueEventListener postListener;

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
        myRef.addValueEventListener(this);

        userModels = new ArrayList<UserModel>();
        adapterUser = new AdapterUser(MainActivity.this, R.layout.item_user, userModels);
        lvUser.setAdapter(adapterUser);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Log.d("kiemtra", dataSnapshot.toString());
        Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();
        for (DataSnapshot data : nodeChild) {
            UserModel user = data.getValue(UserModel.class);
            userModels.add(user);
            adapterUser.notifyDataSetChanged();
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
