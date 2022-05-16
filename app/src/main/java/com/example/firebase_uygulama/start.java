package com.example.firebase_uygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void giris_intent(View view){

        Intent intent= new Intent(getApplicationContext(),signin.class);
        startActivity(intent);

    }
    public void kayit_intent(View view){

        Intent intent= new Intent(getApplicationContext(),signup.class);
        startActivity(intent);

    }

}