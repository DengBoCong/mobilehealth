package com.example.power.mobile_health.Activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;

import com.example.power.mobile_health.Adapter.MainTabAdapter;
import com.example.power.mobile_health.Adapter.RecyclerViewAdapter;
import com.example.power.mobile_health.Fragment.MainTabFragment;
import com.example.power.mobile_health.Fragment.TextFragment;
import com.example.power.mobile_health.Listener.AppBarStateChangeListener;
import com.example.power.mobile_health.R;
import com.example.power.mobile_health.Utils.AnimatorButton;
import com.example.power.mobile_health.Utils.DragFloatActionButton;
import com.example.power.mobile_health.Utils.UsualDialogger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int MOVABLE_COUNT = 4;//设置Tab小于等于4个，不滑动
    private float DENSITY;
    //dp和px之间的转化公式：px = (dp * density + 0.5f), dp = (px / density + 0.5f)
    private ViewPager tlViewPager;
    private PagerAdapter tlPagerAdapter;
    private TabLayout tabLayout;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private UsualDialogger dialogger = null;

    private List<Fragment> listFragment;
    private List<String> listTitle;

    private TextFragment hotRecommendFragment;
    private TextFragment hotCollectionFragment;
    private TextFragment hotMonthFragment;
    private TextFragment hotToday;
    private TextFragment hotCollectionFragment1;
    private TextFragment hotMonthFragment1;
    private TextFragment hotToday1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DENSITY = getResources().getDisplayMetrics().density;

        /*针对标题栏进行相关的操作*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setTitle("为您保驾护航");

        /*设置悬浮按钮*/
        /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
        DragFloatActionButton fab = (DragFloatActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUsualDialog();
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

        initTabPageLayout();//初始化TabLayout
        initCheckButton();//初始化点击检测按钮

        AppBarLayout appBarLayout = (AppBarLayout)findViewById(R.id.main_appBarLayout);

        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                ConstraintLayout constraintLayout = (ConstraintLayout)findViewById(R.id.main_ToolbarTitleLayout) ;
                if( state == State.EXPANDED ) {//展开状态
                    constraintLayout.setVisibility(View.GONE);
                }else if(state == State.COLLAPSED){//折叠状态
                    constraintLayout.setVisibility(View.VISIBLE);
                }else {//中间状态
                    constraintLayout.setVisibility(View.GONE);
                }
            }
        });

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
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorToolbar));

        //初始化各fragment
        hotRecommendFragment = new TextFragment();
        hotCollectionFragment = new TextFragment();
        hotMonthFragment = new TextFragment();
        hotToday = new TextFragment();
        hotCollectionFragment1 = new TextFragment();
        hotMonthFragment1 = new TextFragment();
        hotToday1 = new TextFragment();

        listFragment = new ArrayList<>();
        listFragment.add(hotRecommendFragment);
        listFragment.add(hotCollectionFragment);
        listFragment.add(hotMonthFragment);
        listFragment.add(hotToday);
        listFragment.add(hotCollectionFragment1);
        listFragment.add(hotMonthFragment1);
        listFragment.add(hotToday1);

        listTitle = new ArrayList<>();
        listTitle.add("综合指数");
        listTitle.add("心率指数");
        listTitle.add("体温指数");
        listTitle.add("血压指数");
        listTitle.add("体重指数");
        listTitle.add("血氧指数");
        listTitle.add("血脂指数");

        tabLayout.setTabMode(listTitle.size() > MOVABLE_COUNT ? TabLayout.MODE_SCROLLABLE: TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(3)));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(4)));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(5)));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(6)));

        fragmentPagerAdapter = new MainTabAdapter(this.getSupportFragmentManager(), listFragment, listTitle);
        tlViewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(tlViewPager);
    }

    public void initCheckButton(){
        final AnimatorButton checkButton = (AnimatorButton)findViewById(R.id.main_checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButton.startAnim();
            }
        });
    }

    public void showUsualDialog(){
        dialogger = UsualDialogger.Builder(this)
                .setTitle("通用对话框")
                .setMessage(getResources().getString(R.string.lorem_ipsum))
                .setOnConfirmClickListener("读取", new UsualDialogger.onConfirmClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "读取", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnCancelClickListener("了解", new UsualDialogger.onCancelClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "了解", Toast.LENGTH_SHORT).show();
                        if (dialogger != null) {
                            dialogger.dismiss();
                        }
                    }
                })
                .build().shown();
    }
}
