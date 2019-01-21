package com.example.power.mobile_health.UI.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Power on 2019/1/4.
 */

public class MainTabAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragment;
    private List<String> listTitle;

    public MainTabAdapter(FragmentManager fragmentManager, List<Fragment> listFragment, List<String> listTitle){
        super(fragmentManager);
        this.listFragment = listFragment;
        this.listTitle = listTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position % listTitle.size());
    }
}
