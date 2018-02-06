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
                ThotelsFragment thotelsFragment = new ThotelsFragment();
                return thotelsFragment;
            case 1:
                TtourguideFragment ttourguideFragment = new TtourguideFragment();
                return ttourguideFragment;
            case 2:
                TcabServiceFragment tcabServiceFragment = new TcabServiceFragment();
                return tcabServiceFragment;


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
