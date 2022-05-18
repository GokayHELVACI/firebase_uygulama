package com.example.firebase_uygulama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
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
        setContentView(R.layout.activity_signin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                }

            }


        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}