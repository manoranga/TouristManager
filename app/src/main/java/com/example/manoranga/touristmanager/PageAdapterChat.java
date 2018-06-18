package com.example.manoranga.touristmanager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Manoranga on 12/20/2017.
 */

public class PageAdapterChat extends FragmentPagerAdapter {
    public PageAdapterChat(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Chat_TourGuideFragment chat_tourGuideFragment = new Chat_TourGuideFragment();
                return chat_tourGuideFragment;
            case 1:
                Chat_TouristFragment chat_touristFragment = new Chat_TouristFragment();
                return chat_touristFragment;
        }
        return null;

    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "CHAT WITH TOURGUIDE";
            case 1:
                return "CHAT WITH TOURIST";


        }
        return null;
    }
}
