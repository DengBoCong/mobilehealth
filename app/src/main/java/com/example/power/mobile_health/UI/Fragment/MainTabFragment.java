package com.example.power.mobile_health.UI.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.power.mobile_health.UI.Adapter.MainTabAdapter;
import com.example.power.mobile_health.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Power on 2019/1/4.
 */

public class MainTabFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;

    private List<Fragment> listFragment;
    private List<String> listTitle;

    private TextFragment hotRecommendFragment;
    private TextFragment hotCollectionFragment;
    private TextFragment hotMonthFragment;
    private TextFragment hotToday;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maintab, container, false);

        initControls(view);
        return view;
    }

    /*
    * @param view
    * */

    private void initControls(View view){
        tabLayout = (TabLayout)view.findViewById(R.id.tl_mainActivity);
        viewPager = (ViewPager)view.findViewById(R.id.vp_mainActivity);

        //初始化各fragment
        hotRecommendFragment = new TextFragment();
        hotCollectionFragment = new TextFragment();
        hotMonthFragment = new TextFragment();
        hotToday = new TextFragment();

        listFragment = new ArrayList<>();
        listFragment.add(hotRecommendFragment);
        listFragment.add(hotCollectionFragment);
        listFragment.add(hotMonthFragment);
        listFragment.add(hotToday);

        listTitle = new ArrayList<>();
        listTitle.add("热门推荐");
        listTitle.add("热门收藏");
        listTitle.add("本月热榜");
        listTitle.add("今日热榜");

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(3)));

        fragmentPagerAdapter  = new MainTabAdapter(getActivity().getSupportFragmentManager(), listFragment, listTitle);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
