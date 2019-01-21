package com.example.power.mobile_health.UI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
import android.widget.Toast;

import com.example.power.mobile_health.UI.Activity.Drawer.AboutActivity;
import com.example.power.mobile_health.UI.Activity.Drawer.FeedBackActivity;
import com.example.power.mobile_health.UI.Activity.Drawer.HealthReporteActivity;
import com.example.power.mobile_health.UI.Activity.Drawer.MyMessageActivity;
import com.example.power.mobile_health.UI.Activity.Drawer.MyMoneyActivity;
import com.example.power.mobile_health.UI.Activity.Drawer.PrivateDoctorActivity;
import com.example.power.mobile_health.UI.Adapter.MainTabAdapter;
import com.example.power.mobile_health.UI.Fragment.Main.BloodFatIndexFragment;
import com.example.power.mobile_health.UI.Fragment.Main.BloodOxygenIndexFragment;
import com.example.power.mobile_health.UI.Fragment.Main.BloodPressureIndexFragment;
import com.example.power.mobile_health.UI.Fragment.Main.CompositeIndexFragment;
import com.example.power.mobile_health.UI.Fragment.Main.HeartRateIndexFragment;
import com.example.power.mobile_health.UI.Fragment.Main.TemperatureIndexFragment;
import com.example.power.mobile_health.UI.Fragment.Main.WeightIndexFragment;
import com.example.power.mobile_health.UI.Listener.AppBarStateChangeListener;
import com.example.power.mobile_health.R;
import com.example.power.mobile_health.UI.Utils.Module.AnimatorButton;
import com.example.power.mobile_health.UI.Utils.Module.DragFloatActionButton;
import com.example.power.mobile_health.UI.Utils.Module.MainIndexAnimatorButton;
import com.example.power.mobile_health.UI.Utils.Module.UsualDialogger;

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

    private CompositeIndexFragment compositeIndexFragment;
    private HeartRateIndexFragment heartRateIndexFragment;
    private TemperatureIndexFragment temperatureIndexFragment;
    private BloodPressureIndexFragment bloodPressureIndexFragment;
    private WeightIndexFragment weightIndexFragment;
    private BloodOxygenIndexFragment bloodOxygenIndexFragment;
    private BloodFatIndexFragment bloodFatIndexFragment;

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
                /*showUsualDialog();*/
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
        item.setCheckable(false);//去除item被选中后的默认效果

        if (id == R.id.nav_message) {
            Intent intent = new Intent(this, MyMessageActivity.class);
            startActivity(intent);
        } else if(id == R.id.nav_money){
            Intent intent = new Intent(this, MyMoneyActivity.class);
            startActivity(intent);
        } else if(id == R.id.nav_report){
            Intent intent = new Intent(this, HealthReporteActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_doctor) {
            Intent intent = new Intent(this, PrivateDoctorActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_cloudSync) {

        } else if (id == R.id.nav_money) {

        } else if (id == R.id.nav_feedback){
            Intent intent = new Intent(this, FeedBackActivity.class);
            startActivity(intent);
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
        compositeIndexFragment = new CompositeIndexFragment();
        heartRateIndexFragment = new HeartRateIndexFragment();
        temperatureIndexFragment = new TemperatureIndexFragment();
        bloodPressureIndexFragment = new BloodPressureIndexFragment();
        weightIndexFragment = new WeightIndexFragment();
        bloodOxygenIndexFragment = new BloodOxygenIndexFragment();
        bloodFatIndexFragment = new BloodFatIndexFragment();

        listFragment = new ArrayList<>();
        listFragment.add(compositeIndexFragment);
        listFragment.add(heartRateIndexFragment);
        listFragment.add(temperatureIndexFragment);
        listFragment.add(bloodPressureIndexFragment);
        listFragment.add(weightIndexFragment);
        listFragment.add(bloodOxygenIndexFragment);
        listFragment.add(bloodFatIndexFragment);

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

        final MainIndexAnimatorButton heartButton = (MainIndexAnimatorButton)findViewById(R.id.main_button_heartrate);
        final MainIndexAnimatorButton temperatureButton = (MainIndexAnimatorButton)findViewById(R.id.main_button_temperature);
        final MainIndexAnimatorButton bloodPressureButton = (MainIndexAnimatorButton)findViewById(R.id.main_button_bloodPressure);
        final MainIndexAnimatorButton bloodFatButton = (MainIndexAnimatorButton)findViewById(R.id.main_button_bloodFat);
        final MainIndexAnimatorButton weightButton = (MainIndexAnimatorButton)findViewById(R.id.main_button_weight);
        final MainIndexAnimatorButton bloodOxygenButton = (MainIndexAnimatorButton)findViewById(R.id.main_button_bloodOxygen);

        heartButton.setText("心率");
        temperatureButton.setText("体温");
        bloodPressureButton.setText("血压");
        bloodFatButton.setText("血脂");
        weightButton.setText("体重");
        bloodOxygenButton.setText("血氧");

        heartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showUsualDialog("心率情况：正常", "上一次心率为69次/分，属于正常，过去的历史记录中分析，无危险到驾驶的情况，请放心驾驶", "了解", "读取");
            }
        });

        temperatureButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showUsualDialog("体温情况：正常", "上一次体温为37.8℃，属于正常，过去的历史记录中分析，无危险到驾驶的情况，请放心驾驶", "了解", "读取");
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButton.startAnim();
                heartButton.startAnim(0f, 270f, 0f, 240f);
                temperatureButton.startAnim(0f, -270f, 0f, 240f);
                bloodPressureButton.startAnim(0f, 270f, 0f, -240f);
                bloodFatButton.startAnim(0f, -270f, 0f, -240f);
                weightButton.startAnim(0f, -370f, 0f, 0f);
                bloodOxygenButton.startAnim(0f, 370f, 0f, 0f);
            }
        });
    }

    public void showUsualDialog(String title, String content, String leftString, String rightString){
        dialogger = UsualDialogger.Builder(this)
                .setTitle(title)
                .setMessage(content)
                .setOnConfirmClickListener(rightString, new UsualDialogger.onConfirmClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "读取", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnCancelClickListener(leftString, new UsualDialogger.onCancelClickListener() {
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
