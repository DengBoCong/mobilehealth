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

import com.example.power.mobile_health.UI.Adapter.MyMessageRecyclerViewAdapter;
import com.example.power.mobile_health.UI.Model.Subject;
import com.example.power.mobile_health.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Power on 2019/1/20.
 */

public class MyMessageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Subject> mDatas;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymessage);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Toolbar toolbar = (Toolbar)findViewById(R.id.mymessageActivity_toolbar);
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        recyclerView = (RecyclerView)findViewById(R.id.mymessageActivity_recyclerView);

        mDatas = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            Subject subject = new Subject("发送的内容", "发送的时间", R.mipmap.person);
            mDatas.add(subject);
        }

        MyMessageRecyclerViewAdapter myMessageRecyclerViewAdapter = new MyMessageRecyclerViewAdapter(this, mDatas);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(myMessageRecyclerViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);

    }
}
