package com.example.power.mobile_health.UI.Fragment.PrivateDoctor;

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

import com.example.power.mobile_health.UI.Adapter.DoctorListRecyclerViewAdapter;
import com.example.power.mobile_health.UI.Model.Subject;
import com.example.power.mobile_health.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Power on 2019/1/18.
 */

public class DoctorListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_privatedoctor_doctorlist, container, false);

        initLayout(view);

        return view;
    }

    private void initLayout(View view){
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.privatedoctor_DoctorListRecyclerView);
        List<Subject> datas = new ArrayList<>();

        for(int i = 0; i < 2; i++){
            Subject subject = new Subject("名字", "医师简介", R.mipmap.person);
            datas.add(subject);
        }

        DoctorListRecyclerViewAdapter doctorListRecyclerViewAdapter = new DoctorListRecyclerViewAdapter(getActivity(), datas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(doctorListRecyclerViewAdapter);
    }
}
