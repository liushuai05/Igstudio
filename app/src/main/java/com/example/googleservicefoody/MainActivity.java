package com.example.googleservicefoody;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    Button btnThemDuLieu;
    FirebaseDatabase database; // = FirebaseDatabase.getInstance();
    DatabaseReference myRef; // = database.getReference("message");
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
                myRef.child("users").setValue("Teo",  26);
            }
        });
    }

    private void addControl() {
        btnThemDuLieu = findViewById(R.id.btnThemDuLieu);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("");
    }
}
