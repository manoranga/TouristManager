package com.example.manoranga.touristmanager;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Thotels extends android.support.v4.app.Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<HotelDetails> hotelDetails;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_thotels, container, false);

        recyclerView =(RecyclerView) rootView.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        hotelDetails = new ArrayList<>();
        for(int x=0 ;x<10;x++){
            HotelDetails list = new HotelDetails("Hotel"+(x+1),"nice",
                    "http://www145.pair.com/zoom44/RRR/RRR_logo20m.JPG",25,1.2);
            hotelDetails.add(list);
        }

        adapter = new HotelAdapter(hotelDetails,getContext());
        recyclerView.setAdapter(adapter);




        return rootView;
    }
}

