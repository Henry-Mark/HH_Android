package com.henry.hh.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.henry.hh.entity.PeopleNearby;
import com.henry.hh.fragment.JellyFragment;

import java.util.List;

/**
 * Date: 2016/11/7. 10:56
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:
 */
public class JellyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<PeopleNearby> peopleNearbies;
    public static String KEY = "jelly";

    public JellyFragmentPagerAdapter(FragmentManager fm, List<PeopleNearby> peopleNearbies) {
        super(fm);
        this.peopleNearbies = peopleNearbies;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        if (peopleNearbies != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(KEY, peopleNearbies.get(position));
            frag = new JellyFragment();
            frag.setArguments(bundle);
        }
        return frag;
    }

    @Override
    public int getCount() {
        return peopleNearbies.isEmpty() ? 0 : peopleNearbies.size();
    }
}
