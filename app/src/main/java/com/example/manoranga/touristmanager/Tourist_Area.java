package com.example.manoranga.touristmanager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Tourist_Area extends AppCompatActivity {
    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapter;
    private List<Thotels_items> thotels_items;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist__area);

        recyclerView = (RecyclerView)findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        thotels_items = new ArrayList<>();
        for(int x=0 ;x<10;x++) {
            Thotels_items thotels_item = new Thotels_items("hotel", "ampara", "https://www.google.lk/imgres?imgurl=https%3A%2F%2Fi.jeded.com%2Fi%2Fhhhh.91087.jpg&imgrefurl=https%3A%2F%2Fsubscene.com%2Fsubtitles%2Fhhhh%2Fenglish%2F1645729&docid=I3E8yIknyyBcvM&tbnid=_aNSf0D_WCi2CM%3A&vet=10ahUKEwjv_-HZ9ubXAhWCJZQKHbqaBqAQMwg9KAEwAQ..i&w=1600&h=2400&bih=588&bi" +
                    "w=1366&q=hhhh&ved=0ahUKEwjv_-HZ9ubXAhWCJZQKHbqaBqAQMwg9KAEwAQ&iact=mrc&uact=8", 2.5, 25555);


        thotels_items.add(thotels_item);


        }
        adapter=new HotelsAdapter(thotels_items,this);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }
   // public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_signup, menu);
      //  return true;
  //  }

  //  @Override
  //  public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
     //   int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     //   if (id == R.id.action_settings) {
       //     return true;
        }

       // return super.onOptionsItemSelected(item);
   // }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Thotels thotels = new Thotels();
                    return thotels;
                case 1:
                    Ttourguide ttourguide = new Ttourguide();
                    return ttourguide;
                case 2:
                    TcabService tcabService = new TcabService();
                    return tcabService;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "HOTELS";
                case 1:
                    return "TOURGUIDE";
                case 2:
                    return "CABSERVICE";
            }
            return null;
        }
    }




