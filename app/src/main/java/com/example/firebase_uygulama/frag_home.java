package com.example.firebase_uygulama;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frag_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frag_home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView liste;
    final ArrayList<shop_info> shops = new ArrayList<shop_info>();

    public frag_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frag_home.
     */
    // TODO: Rename and change types and number of parameters
    public static frag_home newInstance(String param1, String param2) {
        frag_home fragment = new frag_home();
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
        View view=inflater.inflate(R.layout.fragment_frag_home, container, false);

        liste = (ListView) view.findViewById(R.id.list_shop);
        shops.add(new shop_info("I. Osman", "27 yıl"));
        shops.add(new shop_info("II. Osman", "28 yıl"));
        shops.add(new shop_info("III. Osman", "29 yıl"));
        shops.add(new shop_info("I. Osman", "27 yıl"));
        shops.add(new shop_info("II. Osman", "28 yıl"));
        shops.add(new shop_info("III. Osman", "29 yıl"));
        shops.add(new shop_info("I. Osman", "27 yıl"));
        shops.add(new shop_info("II. Osman", "28 yıl"));
        shops.add(new shop_info("III. Osman", "29 yıl"));
        shops.add(new shop_info("I. Osman", "27 yıl"));
        shops.add(new shop_info("II. Osman", "28 yıl"));
        shops.add(new shop_info("III. Osman", "29 yıl"));

        ShopAdapter shopAdapter = new ShopAdapter(this.getActivity(), shops);

        if (liste != null) {
            liste.setAdapter(shopAdapter);
        }
        return view;
    }
}