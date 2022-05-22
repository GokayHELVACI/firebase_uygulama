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
        final ArrayList<shop_info> shops = new ArrayList<shop_info>();

        liste = (ListView) view.findViewById(R.id.list_shop);
        shops.add(new shop_info("Antakya Usulü Tavuk Döner Dürüm", "40 tl","https://cdn.getiryemek.com/products/1650546128813_500x375.jpeg"));
        shops.add(new shop_info("Antakya Usulü Zurna Tavuk Döner Dürüm", "43 tl","https://cdn.getiryemek.com/restaurants/1617702222494_1125x522.jpeg"));
        shops.add(new shop_info("Sultan Lokması(Tavuk Dönerden)", "45 tl","https://cdn.getiryemek.com/products/1650546139604_500x375.jpeg"));
        shops.add(new shop_info("Sultan Lokması(Et Dönerden)", "50 tl","https://cdn.getiryemek.com/products/1650546139604_500x375.jpeg"));
        shops.add(new shop_info("Antakya Usulü Et Döner Dürüm", "45 tl","https://cdn.getiryemek.com/products/1650546128813_500x375.jpeg"));
        shops.add(new shop_info("Pilav Üstü Tavuk Döner", "48 tl","https://cdn.getiryemek.com/products/1650546135799_500x375.jpeg"));
        shops.add(new shop_info("İskender (Tavuk Dönerden)", "48 tl","https://cdn.getiryemek.com/products/1650546133089_500x375.jpeg"));
        shops.add(new shop_info("Tavuk Döner", "48 tl","https://cdn.getiryemek.com/products/1650546141645_500x375.jpeg"));
        shops.add(new shop_info("Patates Kızartması", "25 tl","https://cdn.getiryemek.com/products/1621514360915_500x375.jpeg"));

        ShopAdapter shopAdapter = new ShopAdapter(this.getActivity(), shops);

        if (liste != null) {
            liste.setAdapter(shopAdapter);
        }
        return view;
    }
}