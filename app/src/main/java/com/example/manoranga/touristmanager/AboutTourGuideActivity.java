package com.example.manoranga.touristmanager;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AboutTourGuideActivity extends AppCompatActivity {
    TextView title;
    TextView description;
    TextView about;
    Button call,comment;
    String Emali ;
    RatingBar ratingBar;
    float rateValue;
    Button submit;
    RequestQueue requestQueue;
    String rateSubmitUrl = "http://teampro.azurewebsites.net/api/TourGuide/AddTourGuideRating";
    UserLocalStore userLocalStore;
    TourGuideDetails tourGuideDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_tourguide);
        about       = (TextView)findViewById(R.id.about);
        description = (TextView) findViewById(R.id.guideDescription);
        title       = (TextView) findViewById(R.id.title);
        call        = (Button) findViewById(R.id.callGuide);
        ratingBar   =  (RatingBar)findViewById(R.id.ratingBarGuide);
        submit      =  (Button)findViewById(R.id.rateGuideSubmit);


        userLocalStore = new UserLocalStore(getApplicationContext());

        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);


        Intent intent = getIntent();
        tourGuideDetails = (TourGuideDetails) intent.getSerializableExtra("guideDetails");

        title.setText(tourGuideDetails.getUname());
        about.setText(tourGuideDetails.getDescription());
        description.setText(tourGuideDetails.getGender());
        Picasso.with(this).load(tourGuideDetails.getUrl()).into(imageView);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        call.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Emali = tourGuideDetails.getEmail();
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",Emali, null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "To Request the service of the tourguide");
                startActivity(intent);
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue = v;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitRate();
                ratingBar.setRating(0F);
                Toast.makeText(getApplicationContext(), "Rate Submited", Toast.LENGTH_SHORT).show();
            }
        });



    }


    public void submitRate() {


        Map<String, Float> jsonParams = new HashMap<>();
        jsonParams.put("touristId", (float) userLocalStore.getUserDetails().getId());
        jsonParams.put("tourGuideId", (float) tourGuideDetails.getGuideId());
        jsonParams.put("ratingValue", rateValue);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,rateSubmitUrl, new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }


        );
        requestQueue = Volley.newRequestQueue(AboutTourGuideActivity.this);
        requestQueue.add(jsonObjectRequest);

    }

}
