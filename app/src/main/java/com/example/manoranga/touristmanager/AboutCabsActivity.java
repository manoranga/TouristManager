package com.example.manoranga.touristmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import static android.widget.Toast.LENGTH_LONG;

public class AboutCabsActivity extends AppCompatActivity {
    TextView title;
    TextView description;
    Button mComment;
    Button mSubmit;
    Button callCab;
    String txtCommentCab;
    RequestQueue requestQueue;
    String cabCommenturl = "http://teampro.azurewebsites.net/api/CabDetails/AddCabComment";
    String cabRateUrl = "http://teampro.azurewebsites.net/api/CabDetails/AddCabCabRating";
    UserLocalStore userLocalStore;
    RatingBar ratingBar;
    float rateValue;

    final Context context = this;
    CabDetails cabDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_cabs);


        description = (TextView) findViewById(R.id.CabDescription1);
        callCab = (Button) findViewById(R.id.CallCab);
        mSubmit = (Button) findViewById(R.id.btnSubmit);
        mComment = (Button) findViewById(R.id.btnCommentCab);
        title = (TextView) findViewById(R.id.title);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar1);

        userLocalStore = new UserLocalStore(getApplicationContext());


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);



        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Intent intent = getIntent();
        cabDetails = (CabDetails) intent.getSerializableExtra("CabInfo");
        Picasso.with(context).load(cabDetails.getUrl_Cab()).into(imageView);
        title.setText(cabDetails.getUserName());
        description.setText(cabDetails.getDescription());

        mComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.user_comment, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);


                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.userinput);


                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        Toast.makeText(getApplicationContext(), userInput.getText().toString(), LENGTH_LONG).show();
                                        txtCommentCab = userInput.getText().toString();
                                        submitCabComment();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });


                AlertDialog alertDialog = alertDialogBuilder.create();


                alertDialog.show();

            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue = v;
            }
        });

        callCab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = getIntent();
                cabDetails = (CabDetails) intent1.getSerializableExtra("CabInfo");


              /*  Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + cabDetails.getContactNum()));

                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
               context.startActivity(callIntent);
*/
                    //return;
                dialContactPhone(String.valueOf(cabDetails.getContactNum()));
                }


            private void dialContactPhone(final String phoneNumber) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
            }

        });
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cabRate();
                ratingBar.setRating(0F);
                Toast.makeText(getApplicationContext(),"Rate Submited",LENGTH_LONG).show();
            }
        });

    }
    public void submitCabComment() {


        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("cabComment1", txtCommentCab);
        jsonParams.put("userName", userLocalStore.getUserDetails().getName());
        jsonParams.put("cabId", String.valueOf(cabDetails.getCabId()));

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,cabCommenturl, new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(getApplicationContext(), "Rate Submited", Toast.LENGTH_SHORT).show();

                    }
                }


        );
        requestQueue = Volley.newRequestQueue(AboutCabsActivity.this);
        requestQueue.add(jsonObjectRequest);

    }
    public void cabRate() {


        Map<String, Float> jsonParams = new HashMap<>();
        jsonParams.put("touristId", (float) userLocalStore.getUserDetails().getId());
        jsonParams.put("cabId", (float) cabDetails.getCabId());
        jsonParams.put("ratingValue", rateValue);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,cabRateUrl, new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(getApplicationContext(), "Rate Submited", Toast.LENGTH_SHORT).show();

                    }
                }


        );
        requestQueue = Volley.newRequestQueue(AboutCabsActivity.this);
        requestQueue.add(jsonObjectRequest);

    }

    }
