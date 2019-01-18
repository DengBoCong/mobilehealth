package com.example.power.mobile_health.Fragment.HealthReporter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.power.mobile_health.R;

/**
 * Created by Power on 2019/1/18.
 */

public class ThisWeekFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_healthreporter_thisweek, container, false);

        initLayout(view);

        return view;
    }

    private void initLayout(View view){

    }
}
