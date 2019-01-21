package com.example.power.mobile_health.UI.Fragment.HealthReporter;

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

import com.example.power.mobile_health.UI.Adapter.HealthReporterHistoryRecyclerViewAdapter;
import com.example.power.mobile_health.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Power on 2019/1/18.
 */

public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<String[]> mDatas;
    private HealthReporterHistoryRecyclerViewAdapter healthReporterHistoryRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_healthreporter_history, container, false);

        initLayout(view);

        return view;
    }

    private void initLayout(View view){
        recyclerView = (RecyclerView)view.findViewById(R.id.healthreporterActivity_recyclerView);

        mDatas = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            String[] a = new String[]{"90", "这里是内容", "2019-1-18"};
            mDatas.add(a);
        }

        healthReporterHistoryRecyclerViewAdapter = new HealthReporterHistoryRecyclerViewAdapter(getActivity(), mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(healthReporterHistoryRecyclerViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
