package com.example.firebase_uygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        if(FirebaseAuth.getInstance().getCurrentUser()!=null) {
            Intent intent= new Intent(getApplicationContext(),home.class);
            startActivity(intent);
        }
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