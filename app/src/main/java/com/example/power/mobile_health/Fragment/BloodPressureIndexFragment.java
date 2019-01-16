package com.example.power.mobile_health.Fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.example.power.mobile_health.Adapter.RecyclerViewAdapter;
import com.example.power.mobile_health.R;
import com.example.power.mobile_health.Utils.Module.CircleTextView;
import com.example.power.mobile_health.Utils.MpChartUtils.MyMarkerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Power on 2019/1/12.
 */

public class BloodPressureIndexFragment extends Fragment {
    private BarChart chart;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean historyIsOpen;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager_bloodpressure, container, false);

        initLayout(view);

        return view;
    }

    public void initLayout(View view){
        initSwipeRefreshLayout(view);
        initDataTextView(view);
        initRecyclerView(view);

        chart = view.findViewById(R.id.barChart_bloodpressure);
        chart.getDescription().setEnabled(false);

//        chart.setDrawBorders(true);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawBarShadow(false);

        chart.setDrawGridBackground(false);

        setData(30, 200f);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.chart_marker_view);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv); // Set the marker to the chart

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        /*l.setTypeface(tfLight);*/
        l.setYOffset(0f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        XAxis xAxis = chart.getXAxis();
        /*xAxis.setTypeface(tfLight);*/
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setTextColor(getResources().getColor(R.color.colorBlackLow));
        xAxis.setValueFormatter(new IndexAxisValueFormatter() {
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });

        YAxis leftAxis = chart.getAxisLeft();
        /*leftAxis.setTypeface(tfLight);*/
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        chart.getAxisRight().setEnabled(false);
    }

    public void setData(int count, float range){
        float groupSpace = 0.08f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.2f; // x4 DataSet
        // (0.2 + 0.03) * 4 + 0.08 = 1.00 -> interval per "group"

        int groupCount = count;
        /*int startYear = 1980;
        int endYear = startYear + groupCount;*/

        ArrayList<BarEntry> values1 = new ArrayList<>();
        ArrayList<BarEntry> values2 = new ArrayList<>();

        float randomMultiplier = range;

        for (int i = 0; i < count; i++) {
            values1.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
            values2.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
        }

        BarDataSet set1, set2;

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {

            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) chart.getData().getDataSetByIndex(1);
            set1.setValues(values1);
            set2.setValues(values2);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();

        } else {
            // create 4 DataSets
            set1 = new BarDataSet(values1, "高压");
            set1.setColor(Color.rgb(104, 241, 175));
            set2 = new BarDataSet(values2, "低压");
            set2.setColor(Color.rgb(164, 228, 251));

            BarData data = new BarData(set1, set2);
            data.setValueFormatter(new LargeValueFormatter());

            /*data.setValueTypeface(tfLight);*/

            chart.setData(data);
        }

        // specify the width each bar should have
        chart.getBarData().setBarWidth(barWidth);

        // restrict the x-axis range
        chart.getXAxis().setAxisMinimum(0);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chart.groupBars(0, groupSpace, barSpace);
        chart.invalidate();
    }

    private void initSwipeRefreshLayout(View view){
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout_bloodpressure);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "正在刷新", Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initDataTextView(View view){

    }

    private void initRecyclerView(View view){
        historyIsOpen = false;
        final ConstraintLayout checkList = (ConstraintLayout)view.findViewById(R.id.bloodpressure_constraintLayout_checkList);
        final TextView historyMore = (TextView)view.findViewById(R.id.bloodpressure_historyMore);

        List<ArrayList<String> > mDatas;
        RecyclerViewAdapter recyclerViewAdapter;
        mDatas = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            ArrayList<String> keyValue = new ArrayList<>();
            keyValue.add("2019-1-16  9:40:16");
            keyValue.add("20.1% / 22.0分");
            mDatas.add(keyValue);
        }
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), mDatas);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.bloodpressure_historyList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(historyMore, "translationY", -25f, 40f);
        objectAnimator.setRepeatCount(Animation.INFINITE);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(historyMore, "alpha", 0, 1);
        objectAnimator1.setRepeatCount(Animation.INFINITE);
        animatorSet.playTogether(objectAnimator, objectAnimator1);
        animatorSet.setDuration(1800);
        animatorSet.start();

        final AnimatorSet animatorSet1 = new AnimatorSet();
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(checkList, "alpha", 0, 1);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(checkList, "translationY", -500f, 0f);
        animatorSet1.playTogether(objectAnimator2, objectAnimator3);
        animatorSet1.setDuration(1500);

        historyMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(historyIsOpen){
                    historyIsOpen = false;
                    historyMore.setText("︾");
                    checkList.setVisibility(View.GONE);
                }else{
                    historyIsOpen = true;
                    historyMore.setText("︽");
                    checkList.setVisibility(View.VISIBLE);
                    animatorSet1.start();
                }

            }
        });
    }
}
