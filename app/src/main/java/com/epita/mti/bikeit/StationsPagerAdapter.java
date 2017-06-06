package com.epita.mti.bikeit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epita.mti.bikeit.models.Station;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T4ze on 27/05/2017.
 */

public class StationsPagerAdapter extends FragmentPagerAdapter {
    public List<Station> mDataset = new ArrayList<>();

    public StationsPagerAdapter(FragmentManager fm, List<Station> mDataset) {
        super(fm);
        this.mDataset = mDataset;
    }

    @Override
    public Fragment getItem(int position) {
        PlaceHolderFragment fragment = new PlaceHolderFragment();
        fragment.setStation(mDataset.get(position));
        return fragment;
    }
    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (mDataset.get(position).getRecord().getFields().getName());
    }
}
