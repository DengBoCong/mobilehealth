package com.example.power.mobile_health.UI.Activity.Drawer;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.power.mobile_health.UI.Adapter.MyMoneyRecyclerViewAdapter;
import com.example.power.mobile_health.UI.Model.TIModel;
import com.example.power.mobile_health.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Power on 2019/1/16.
 */

public class MyMoneyActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<TIModel> mDatas;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymoney);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Toolbar toolbar = (Toolbar)findViewById(R.id.mymoneyActivity_toolbar);
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initRecyclerView();
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

    private void initRecyclerView(){
        recyclerView = (RecyclerView)findViewById(R.id.mymoneyActivity_mainRecyclerView);

        mDatas = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            TIModel tiModel = new TIModel("体现", R.drawable.ic_monetization_on_black_24dp);
            mDatas.add(tiModel);
        }

        linearLayoutManager = new LinearLayoutManager(this);
        MyMoneyRecyclerViewAdapter myMoneyRecyclerViewAdapter = new MyMoneyRecyclerViewAdapter(this, mDatas);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myMoneyRecyclerViewAdapter);

    }
}
