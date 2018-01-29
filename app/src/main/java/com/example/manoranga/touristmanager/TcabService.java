package com.example.manoranga.touristmanager;

import android.app.ProgressDialog;
import android.os.Bundle;
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

public class TcabService extends android.support.v4.app.Fragment {
    private static final String URL_DATA ="http://teampro.azurewebsites.net/api/CabDetails";
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
        loadDeatils();
        return rootView;
    }
    public void loadDeatils(){

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Data.......");
       // progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {

                    JSONArray ja = new JSONArray(response);

                    for (int i=0;i<ja.length();i++){
                        JSONObject object = ja.getJSONObject(i);
                        CabDetails items = new CabDetails(
                                object.getInt("cabId"),
                               // object.getInt("preference"),
                               // object.getInt("status"),
                                object.getString("userName"),
                                object.getString("discription"),
                              //  object.getInt("rank"),
                                object.getString("currentLocation"),
                                object.getString("email"),
                                object.getString("contactNo")

                        );
                        cabDetailsList.add(items);

                    }
                    adapter = new CabAdapter(cabDetailsList,getContext());
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),"eeeeeeee"+e.getMessage(),Toast.LENGTH_LONG).show();
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
