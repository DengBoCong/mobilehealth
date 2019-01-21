package com.example.power.mobile_health.UI.Activity.Drawer;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.power.mobile_health.UI.Adapter.MainTabAdapter;
import com.example.power.mobile_health.UI.Fragment.HealthReporter.HistoryFragment;
import com.example.power.mobile_health.UI.Fragment.HealthReporter.ThisWeekFragment;
import com.example.power.mobile_health.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Power on 2019/1/16.
 */

public class HealthReporteActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> listFragment;
    private List<String> listTitle;
    private static final int MOVABLE_COUNT = 4;

    private FragmentPagerAdapter fragmentPagerAdapter;
    private ThisWeekFragment thisWeekFragment;
    private HistoryFragment historyFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthreporter);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Toolbar toolbar = (Toolbar)findViewById(R.id.healthreporter_toolbar);
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initTabLayout();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initTabLayout(){
        tabLayout = (TabLayout)findViewById(R.id.tl_healthreproter);
        viewPager = (ViewPager)findViewById(R.id.vp_healthReporterActivity);

        thisWeekFragment = new ThisWeekFragment();
        historyFragment = new HistoryFragment();

        listFragment = new ArrayList<>();
        listFragment.add(thisWeekFragment);
        listFragment.add(historyFragment);

        listTitle = new ArrayList<>();
        listTitle.add("最新周报");
        listTitle.add("历史周报");

        tabLayout.setTabMode(listTitle.size() > MOVABLE_COUNT ? TabLayout.MODE_SCROLLABLE : TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(1)));

        fragmentPagerAdapter = new MainTabAdapter(this.getSupportFragmentManager(), listFragment, listTitle);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
