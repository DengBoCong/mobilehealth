package com.example.power.mobile_health.Activity.Drawer;

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

import com.example.power.mobile_health.Adapter.MainTabAdapter;
import com.example.power.mobile_health.Fragment.PrivateDoctor.DoctorListFragment;
import com.example.power.mobile_health.Fragment.PrivateDoctor.MainShowFragment;
import com.example.power.mobile_health.Fragment.PrivateDoctor.MessageListFragment;
import com.example.power.mobile_health.R;
import com.example.power.mobile_health.Utils.Module.NoSlideViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Power on 2019/1/16.
 */

public class PrivateDoctorActivity extends AppCompatActivity {
    private static final int MOVABLE_COUNT = 4;
    private TabLayout tabLayout;
    private NoSlideViewPager viewPager;
    private List<Fragment> listFragment;
    private List<String> listTitle;
    private FragmentPagerAdapter fragmentPagerAdapter;

    private MessageListFragment messageListFragment;
    private DoctorListFragment doctorListFragment;
    private MainShowFragment mainShowFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privatedoctor);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Toolbar toolbar = (Toolbar)findViewById(R.id.privatedoctor_toolbar);
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initLayout();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initLayout(){
        tabLayout = (TabLayout)findViewById(R.id.tl_privatedoctor);
        viewPager = (NoSlideViewPager)findViewById(R.id.vp_privateDoctorActivity);

        messageListFragment = new MessageListFragment();
        messageListFragment.setFragmentContext(this);
        doctorListFragment = new DoctorListFragment();
        mainShowFragment = new MainShowFragment();

        listFragment = new ArrayList<>();
        listFragment.add(messageListFragment);
        listFragment.add(doctorListFragment);
        listFragment.add(mainShowFragment);

        listTitle = new ArrayList<>();
        listTitle.add("消息列表");
        listTitle.add("私人医师");
        listTitle.add("线上医院");

        tabLayout.setTabMode(listTitle.size() > MOVABLE_COUNT ? TabLayout.MODE_SCROLLABLE : TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(2)));

        fragmentPagerAdapter = new MainTabAdapter(this.getSupportFragmentManager(), listFragment, listTitle);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
