package com.example.firebase_uygulama;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frag_shop#newInstance} factory method to
 * create an instance of this fragment.
 */

public class frag_shop extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static String[] Sepetim = new String[10];

    FirebaseAuth myAuth;
    FirebaseUser myUser;
    DatabaseReference myReference;
    HashMap<String,Object> myData;

    Button siparisBtn;
    public frag_shop() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frag_shop.
     */
    // TODO: Rename and change types and number of parameters
    public static frag_shop newInstance(String param1, String param2) {
        frag_shop fragment = new frag_shop();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        Arrays.fill(Sepetim, "");
        View view = inflater.inflate(R.layout.fragment_frag_shop, container, false);
        siparisBtn=view.findViewById(R.id.siparisbtn);

        ListView listemiz=(ListView) view.findViewById(R.id.list_sepet);

        siparisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Sepetim[0]!="") {
                    myReference.child("Kullanicilar").child(myUser.getUid()).child("sepet").removeValue();
                    getActivity().finish();
                    startActivity(getActivity().getIntent());

                    Intent intent= new Intent(getContext(),order.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.anim2,R.anim.anim3);
                }
                else
                    Toast.makeText(getContext(), "sepetinize ürün eklemelisiniz", Toast.LENGTH_SHORT).show();


            }
        });

        myAuth=FirebaseAuth.getInstance();
        myReference= FirebaseDatabase.getInstance().getReference();
        myUser=myAuth.getCurrentUser();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

            int idx;

        for(idx=0;idx<10;idx++)

            {

                int finalIdx = idx;
                myReference.child("Kullanicilar").child(myUser.getUid()).child("sepet").child(String.valueOf(idx)).get().addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    } else {
                        if (task.getResult().getValue(Object.class) != null) {
                            String firebase = String.valueOf(task.getResult().getValue(Object.class));
                            myData = new HashMap<>();

                            myData = (HashMap<String, Object>) task.getResult().getValue(Object.class);
                            Log.d("firebase", (String) myData.get("isim"));
                            Log.d("firebase", String.valueOf(finalIdx));

                            frag_shop.Sepetim[finalIdx] = String.valueOf(myData.get("isim"));

                            Log.d("fireeee", frag_shop.Sepetim[0]);

                        }
                        if (finalIdx == 9) {
                            try {

                                ArrayAdapter<String> veriAdaptoru = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, Sepetim);
                                listemiz.setAdapter(veriAdaptoru);
                            }
                            catch (Exception e){

                            }

                        }

                    }
                });


            }

            }
        });
        t.start(); // spawn thread


        try {
            t.join();  // wait for thread to finish
            Log.d("firsdasdaebase",  ""+ this.Sepetim[0]);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }





            return view;
    }
}