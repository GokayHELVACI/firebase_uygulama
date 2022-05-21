package com.example.firebase_uygulama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class home extends AppCompatActivity {
    BottomNavigationView bot;
    FirebaseAuth myAuth;
    FirebaseUser myUser;
    DatabaseReference myReference;
    frag_shop frg_shop=new frag_shop();
    frag_home frg_home=new frag_home();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.action_bar_layout, null);

        actionBar.setCustomView(v);
        if(FirebaseAuth.getInstance().getCurrentUser()!=null) {

            setContentView(R.layout.activity_home);

            bot = findViewById(R.id.bot);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCon, new frag_home()).commit();

            bot.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.shop:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCon, frg_shop).commit();

                            break;
                        case R.id.home:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCon, frg_home).commit();

                            break;
                        case R.id.ayarlar:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCon, new frag_settings()).commit();

                            break;

                    }

                    return true;
                }
            });


        }
        else{
            Intent intent= new Intent(getApplicationContext(),start.class);
            startActivity(intent);
        }

    }

    public void sepetiBosalt(View view){
        myAuth= FirebaseAuth.getInstance();
        myReference= FirebaseDatabase.getInstance().getReference();
        myUser=myAuth.getCurrentUser();
        myReference.child("Kullanicilar").child(myUser.getUid()).child("sepet").removeValue();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCon,new frag_shop()).commit();
        ShopAdapter.myData.clear();
        ShopAdapter.Counter=0;

    }
    public void cikis_yap(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent= new Intent(getApplicationContext(),start.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim0,R.anim.anim1);


    }

}