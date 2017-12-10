package com.example.manoranga.touristmanager;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Ttourguide extends android.support.v4.app.Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<TourGuideDetails> tourGuideDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  rootView = inflater.inflate(R.layout.activity_ttourguide, container, false);

        recyclerView =(RecyclerView)rootView.findViewById(R.id.recycleGuide);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tourGuideDetails = new ArrayList<>();
        for(int x=0 ;x<10;x++){
            TourGuideDetails list = new TourGuideDetails("Hotel"+(x+1),"nice",
                    "http://www.math.uni-frankfurt.de/~person/_4170854.jpg",25,1.2);
            tourGuideDetails.add(list);
        }

        adapter = new TourGuideAdapter(tourGuideDetails,getContext());
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}
