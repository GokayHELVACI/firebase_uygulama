package com.example.firebase_uygulama;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frag_settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frag_settings extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FirebaseAuth myAuth;
    FirebaseUser myUser;
    DatabaseReference myReference;
    HashMap<String,Object> myData;

    public frag_settings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frag_settings.
     */
    // TODO: Rename and change types and number of parameters
    public static frag_settings newInstance(String param1, String param2) {
        frag_settings fragment = new frag_settings();
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
        View view=inflater.inflate(R.layout.fragment_frag_settings, container, false);

        TextView t1= view.findViewById(R.id.ad);
        TextView t2= view.findViewById(R.id.mail);

        myAuth=FirebaseAuth.getInstance();
        myReference= FirebaseDatabase.getInstance().getReference();
        myUser=myAuth.getCurrentUser();

        myReference.child("Kullanicilar").child(myUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()){


                        myData = new HashMap<>();

                        myData = (HashMap<String, Object>) task.getResult().getValue(Object.class);

                        t1.setText(myData.get("kullaniciAdi").toString());

                        t2.setText(myData.get("KullaniciEmail").toString());


                    }
            }
        });




        return view;
    }
}