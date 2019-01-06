package com.example.power.mobile_health.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.power.mobile_health.Adapter.MainTabAdapter;
import com.example.power.mobile_health.Adapter.RecyclerViewAdapter;
import com.example.power.mobile_health.Fragment.MainTabFragment;
import com.example.power.mobile_health.Fragment.TextFragment;
import com.example.power.mobile_health.Listener.AppBarStateChangeListener;
import com.example.power.mobile_health.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ViewPager tlViewPager;
    private PagerAdapter tlPagerAdapter;
    private TabLayout tabLayout;
    private FragmentPagerAdapter fragmentPagerAdapter;

    private List<Fragment> listFragment;
    private List<String> listTitle;

    private TextFragment hotRecommendFragment;
    private TextFragment hotCollectionFragment;
    private TextFragment hotMonthFragment;
    private TextFragment hotToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*针对标题栏进行相关的操作*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*设置悬浮按钮*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /*对侧边栏的开闭与标题栏的盒子按钮进行关联*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*往FrameLayout中添加Fragment*/
        /*if(findViewById(R.id.fg_container) != null){
            if(savedInstanceState != null){
                return;
            }
            MainTabFragment mainTabFragment = new MainTabFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fg_container, mainTabFragment).commit();
        }*/

        initTabPageLayout();

        AppBarLayout appBarLayout = (AppBarLayout)findViewById(R.id.main_appBarLayout);

        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if( state == State.EXPANDED ) {

                    //展开状态

                }else if(state == State.COLLAPSED){
                    //折叠状态

                }else {

                    //中间状态

                }
            }
        });
        /*List<String> mDatas;
        RecyclerViewAdapter recyclerViewAdapter;
        mDatas = new ArrayList<String>();
        for ( int i=0; i < 40; i++) {
            mDatas.add( "item"+i);
        }
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, mDatas);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.main_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            /*super.onBackPressed();*/
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void enterLoginMainActivity(View view){
        Intent intent = new Intent(this, LoginMainActivity.class);
        startActivity(intent);
        finish();
    }

    public void loadMainViewPager(){
        /*ViewPager mainViewPager = (ViewPager)findViewById(R.id.vp_main_container);*/
        LayoutInflater layoutInflater = getLayoutInflater();
        View viewConsult = layoutInflater.inflate(R.layout.main_consult_main, null);
        View viewCheck = layoutInflater.inflate(R.layout.main_check_main, null);
        View viewNav = layoutInflater.inflate(R.layout.main_nav_main, null);


    }

    public void initTabPageLayout(){
        tlViewPager = (ViewPager)findViewById(R.id.vp_mainActivity);
        tabLayout = (TabLayout)findViewById(R.id.tl_mainActivity);

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

        fragmentPagerAdapter = new MainTabAdapter(this.getSupportFragmentManager(), listFragment, listTitle);
        tlViewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(tlViewPager);
    }
}
