package com.example.firebase_uygulama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class signup extends AppCompatActivity {
    EditText email,sifre,isim;
    Button btn;
    String txt_email,txt_şifre,txt_isim;

    FirebaseAuth myAuth;
    FirebaseUser myUser;
    DatabaseReference myReference;
    HashMap<String,Object> myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        isim=findViewById(R.id.editTextTextPersonName);
        email=findViewById(R.id.editTextTextPersonName2);
        sifre=findViewById(R.id.editTextTextPersonName3);


        myAuth=FirebaseAuth.getInstance();
        myReference= FirebaseDatabase.getInstance().getReference();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);






    }

    public void kayitol(View view) {

        txt_email=email.getText().toString();
        txt_şifre=sifre.getText().toString();
        txt_isim=isim.getText().toString();

        if(!TextUtils.isEmpty(txt_email) && !TextUtils.isEmpty(txt_şifre)){
            myAuth.createUserWithEmailAndPassword(txt_email,txt_şifre)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        myUser=myAuth.getCurrentUser();
                        myData= new HashMap<>();
                        myData.put("kullaniciAdi",txt_isim);
                        myData.put("KullaniciEmail",txt_email);
                        myData.put("KullaniciSifre",txt_şifre);
                        myReference.child("Kullanicilar").child(myUser.getUid()).setValue(myData).addOnCompleteListener(signup.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(signup.this,"Kayıt tamamlandı",Toast.LENGTH_SHORT).show();
                                    Intent intent= new Intent(getApplicationContext(),home.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(signup.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


                    }
                }
            });
        }

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