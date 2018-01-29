package com.example.manoranga.touristmanager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
