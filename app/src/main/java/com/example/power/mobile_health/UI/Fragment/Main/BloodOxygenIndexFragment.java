package com.example.power.mobile_health.UI.Fragment.Main;

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

import com.example.power.mobile_health.UI.Adapter.RecyclerViewAdapter;
import com.example.power.mobile_health.R;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Power on 2019/1/12.
 */

public class BloodOxygenIndexFragment extends Fragment {
    private BubbleChart chart;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean historyIsOpen;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager_bloodoxygen, container, false);

        initLayout(view);

        return view;
    }

    private void initLayout(View view){
        initSwipRefreshLayout(view);
        initDataTextView(view);
        initRecyclerView(view);

        chart = view.findViewById(R.id.bubbleChart_bloodoxygen);
        chart.getDescription().setEnabled(false);

        chart.setDrawGridBackground(false);

        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        chart.setMaxVisibleValueCount(200);
        chart.setPinchZoom(true);

        setData(14, 100);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        /*l.setTypeface(tfLight);*/

        YAxis yl = chart.getAxisLeft();
        /*yl.setTypeface(tfLight);*/
        yl.setSpaceTop(30f);
        yl.setSpaceBottom(30f);
        yl.setDrawZeroLine(false);

        chart.getAxisRight().setEnabled(false);

        XAxis xl = chart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        /*xl.setTypeface(tfLight);*/
    }

    private void setData(int count, int range){
        /*ArrayList<BubbleEntry> values1 = new ArrayList<>();*/
        ArrayList<BubbleEntry> values2 = new ArrayList<>();
        /*ArrayList<BubbleEntry> values3 = new ArrayList<>();*/

        for (int i = 0; i < count; i++) {
            /*values1.add(new BubbleEntry(i, (float) (Math.random() * range), (float) (Math.random() * range), R.drawable.star));*/
            values2.add(new BubbleEntry(i, (float) (Math.random() * range), (float) (Math.random() * range), R.drawable.star));
            /*values3.add(new BubbleEntry(i, (float) (Math.random() * range), (float) (Math.random() * range)));*/
        }

        // create a dataset and give it a type
        /*BubbleDataSet set1 = new BubbleDataSet(values1, "DS 1");
        set1.setColor(ColorTemplate.COLORFUL_COLORS[0], 130);
        set1.setDrawValues(true);*/

        BubbleDataSet set2 = new BubbleDataSet(values2, "近两周血氧饱和度");
        set2.setColor(ColorTemplate.COLORFUL_COLORS[1], 130);
        set2.setDrawValues(true);

        /*BubbleDataSet set3 = new BubbleDataSet(values3, "DS 3");
        set3.setColor(ColorTemplate.COLORFUL_COLORS[2], 130);
        set3.setDrawValues(true);*/

        ArrayList<IBubbleDataSet> dataSets = new ArrayList<>();
        /*dataSets.add(set1);*/ // add the data sets
        dataSets.add(set2);
        /*dataSets.add(set3);*/

        // create a data object with the data sets
        BubbleData data = new BubbleData(dataSets);
        data.setDrawValues(false);
        /*data.setValueTypeface(tfLight);*/
        data.setValueTextSize(8f);
        data.setValueTextColor(Color.WHITE);
        data.setHighlightCircleWidth(1.5f);

        chart.setData(data);
        chart.invalidate();
    }

    private void initSwipRefreshLayout(View view){
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout_bloodoxygen);
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
        final ConstraintLayout checkList = (ConstraintLayout)view.findViewById(R.id.bloodoxygen_constraintLayout_checkList);
        final TextView historyMore = (TextView)view.findViewById(R.id.bloodoxygen_historyMore);

        List<ArrayList<String> > mDatas;
        RecyclerViewAdapter recyclerViewAdapter;
        mDatas = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            ArrayList<String> keyValue = new ArrayList<>();
            keyValue.add("2019-1-16  9:40:16");
            keyValue.add("98% SpO2");
            mDatas.add(keyValue);
        }
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), mDatas);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.bloodoxygen_historyList);
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
