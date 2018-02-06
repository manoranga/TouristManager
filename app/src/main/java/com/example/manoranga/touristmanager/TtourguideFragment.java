package com.example.manoranga.touristmanager;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TtourguideFragment extends android.support.v4.app.Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<TourGuideDetails> tourGuideDetails;
    String URL_DATA ="http://teampro.azurewebsites.net/api/TourGuide/";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  rootView = inflater.inflate(R.layout.activity_ttourguide, container, false);

        recyclerView =(RecyclerView)rootView.findViewById(R.id.recycleGuide);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tourGuideDetails = new ArrayList<>();
        loadDeatils();
        return  rootView;
        }

    public void loadDeatils(){

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loding Data.......");
       // progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {

                    JSONArray ja = new JSONArray(response);

                    for (int i=0;i<ja.length();i++){
                        JSONObject object = ja.getJSONObject(i);

                        TourGuideDetails items = new TourGuideDetails(
                                object.getString("userName"),
                                object.getString("gender"),
                                object.getString("discription"),
                                object.getString("email"),
                                object.getString("nationality"),
                                object.getInt("chargePerDay"),
                              //  object.getInt("preference"),
                                object.getInt("status"),
                                object.getInt("rank"),
                                object.getInt("guideId")






                        );
                        tourGuideDetails.add(items);

                    }
                    adapter = new TourGuideAdapter(tourGuideDetails,getContext());
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
