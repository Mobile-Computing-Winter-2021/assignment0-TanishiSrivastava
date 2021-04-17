package com.tanishi.assignment5;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    int noOfTabs;
    public PageAdapter(@NonNull FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.noOfTabs=NumberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                WifiFragment tab1=new WifiFragment();
                return tab1;
            case 1:
                BarFragment tab2=new BarFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
