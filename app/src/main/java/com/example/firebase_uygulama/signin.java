package com.example.firebase_uygulama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signin extends AppCompatActivity {

    FirebaseAuth auth;
    EditText mail,pass;
    String txt_mail,txt_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.action_bar_layout, null);
        actionBar.setCustomView(v);
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_signin);

            mail = findViewById(R.id.editTextTextEmailAddress_in);
            pass = findViewById(R.id.editTextTextPassword_in);
            auth = FirebaseAuth.getInstance();



    }

    public void giris_yap(View view){

        txt_mail=mail.getText().toString();
        txt_pass=pass.getText().toString();

        auth.signInWithEmailAndPassword(txt_mail, txt_pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), home.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim2,R.anim.anim3);

                }

            }


        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.anim0,R.anim.anim1);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}