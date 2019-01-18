package com.example.power.mobile_health.Activity.Drawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.power.mobile_health.Adapter.PersonMessageRecyclerViewAdapter;
import com.example.power.mobile_health.Adapter.RecyclerViewAdapter;
import com.example.power.mobile_health.R;
import com.example.power.mobile_health.Utils.BlurUtils;

import java.util.List;

/**
 * Created by Power on 2019/1/16.
 */

public class PersonActivity extends AppCompatActivity  {
    private ImageView mBlurImage;
    private RecyclerView recyclerView;
    private PersonMessageRecyclerViewAdapter personMessageRecyclerViewAdapter;
    private List<Integer> mImageDatas;
    private List<String[]> mTextDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        /*设置ToolBar*/
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Toolbar toolbar = (Toolbar)findViewById(R.id.person_toolbar);
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initView();
        initData();
        /*initMessageData();*/

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

    private void initView() {
        mBlurImage = (ImageView) findViewById(R.id.personActivity_originImage);
    }

    private void initData() {
        // 获取图片
        Bitmap originBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.person);
        Bitmap blurBitmap = BlurUtils.blur(this, originBitmap);

        // 填充模糊后的图像和原图
        mBlurImage.setImageBitmap(blurBitmap);
    }

    private void initMessageData(){
        /*mImageDatas.add(R.drawable.ic_perm_identity_black_24dp);
        mImageDatas.add(R.drawable.ic_wc_black_24dp);
        mImageDatas.add(R.drawable.ic_create_black_24dp);
        mImageDatas.add(R.drawable.ic_vpn_key_black_24dp);
        mImageDatas.add(R.drawable.ic_call_black_24dp);
        mImageDatas.add(R.drawable.ic_menu_about);

        String[] account = {"用户名", "林沫"};
        String[] sex = {"性别", "男"};
        String[] sign = {"个性签名", "默默地变好"};
        String[] pass = {"密码", "******"};
        String[] phone = {"紧急呼叫", "18870125082"};
        String[] version = {"版本号", "2.11.3"};

        mTextDatas.add(account);
        mTextDatas.add(sex);
        mTextDatas.add(sign);
        mTextDatas.add(pass);
        mTextDatas.add(phone);
        mTextDatas.add(version);*/

        /*personMessageRecyclerViewAdapter = new PersonMessageRecyclerViewAdapter(this, mImageDatas, mTextDatas);
        recyclerView = (RecyclerView)findViewById(R.id.personActivity_messageRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(personMessageRecyclerViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());*/
    }
}
