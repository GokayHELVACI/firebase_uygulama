package com.example.firebase_uygulama;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.action_bar_layout, null);
        actionBar.setCustomView(v);
        setContentView(R.layout.activity_start);
        if(FirebaseAuth.getInstance().getCurrentUser()!=null) {
            Intent intent= new Intent(getApplicationContext(),home.class);
            startActivity(intent);

        }
    }

    public void giris_intent(View view){

        Intent intent= new Intent(getApplicationContext(),signin.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim2,R.anim.anim3);


    }
    public void kayit_intent(View view){

        Intent intent= new Intent(getApplicationContext(),signup.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim2,R.anim.anim3);


    }

}