package com.example.power.mobile_health.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.power.mobile_health.Activity.MainActivity;
import com.example.power.mobile_health.Adapter.RecyclerViewAdapter;
import com.example.power.mobile_health.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Power on 2019/1/4.
 */

public class TextFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                 R.layout.fragment_screen_slide_page, container, false
        );

        /*initData(rootView);*/

        return rootView;
    }

    /*public void initData(View view){

        List<String> mDatas;
        RecyclerViewAdapter recyclerViewAdapter;
        mDatas = new ArrayList<String>();
        for ( int i=0; i < 1; i++) {
            mDatas.add( "item"+i);
        }
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), mDatas);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.main_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }*/
}
