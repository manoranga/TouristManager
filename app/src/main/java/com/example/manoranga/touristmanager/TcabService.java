package com.example.manoranga.touristmanager;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TcabService extends android.support.v4.app.Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<CabDetails> cabDetailsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  rootView = inflater.inflate(R.layout.activity_tcab_service, container, false);

        recyclerView =(RecyclerView) rootView.findViewById(R.id.recycleCab);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cabDetailsList = new ArrayList<>();
        for(int x =0;x<15;x++){
            CabDetails cabDetails = new CabDetails("Owner" +(1+x),"http://www.freepngimg.com/download/taxi_cab/5-2-taxi-cab-free-download-png.png",
                    "gggggg",255,2.5);
            cabDetailsList.add(cabDetails);
        }
        adapter = new CabAdapter(cabDetailsList,getContext());
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
