package com.example.firebase_uygulama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class order extends AppCompatActivity {
    ArrayList<String> list_comment=new ArrayList<String>();

    FirebaseAuth myAuth;
    FirebaseUser myUser;
    DatabaseReference myReference;
    Button btn;
    EditText edt;
    ListView liste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.action_bar_layout, null);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setCustomView(v);



        myAuth=FirebaseAuth.getInstance();
        myReference= FirebaseDatabase.getInstance().getReference();

        edt=findViewById(R.id.edt);
        btn=findViewById(R.id.btn_gndr);

        liste=(ListView) findViewById(R.id.comment_list);


        getComments();





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt.getText().toString().length()>3) {
                    myReference.child("Yorumlar").push().setValue(edt.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Kayıt tamamlandı", Toast.LENGTH_SHORT).show();
                                getComments();
                            } else {

                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "Yorumunuz 3 karakterden uzun olmalıdır", Toast.LENGTH_SHORT).show();

                }

                edt.getText().clear();
                }
        });



    }

    public void getComments(){
        myReference.child("Yorumlar").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                if(task.getResult().getValue()!=null) {
                    Map<String, String> data = (Map<String, String>) task.getResult().getValue();
                    list_comment.clear();
                    for (String key : data.keySet()) {
                        list_comment.add(data.get(key));
                    }
                    ArrayAdapter<String> veriAdaptoru = new ArrayAdapter<String>
                            (this, android.R.layout.simple_list_item_1, android.R.id.text1, list_comment);

                    liste.setAdapter(veriAdaptoru);
                    Log.d("TAG", " " + data.get(0));
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