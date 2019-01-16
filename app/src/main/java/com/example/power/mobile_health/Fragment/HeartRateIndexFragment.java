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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.power.mobile_health.Adapter.RecyclerViewAdapter;
import com.example.power.mobile_health.R;
import com.example.power.mobile_health.Utils.Module.CircleTextView;
import com.example.power.mobile_health.Utils.MpChartUtils.RadarMarkerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Power on 2019/1/12.
 */

public class HeartRateIndexFragment extends Fragment {
    private LineChart chart;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean historyIsOpen;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager_heartrate, container, false);

        initLayout(view);

        return view;
    }

    public void initLayout(View view){
        initSwipRefreshLayout(view);
        initDataTextView(view);
        initRecyclerView(view);

        chart = (LineChart)view.findViewById(R.id.lineChart_heartrate);
        // 没有描述
        chart.getDescription().setEnabled(false);

        // 设置允许手势动作
        chart.setTouchEnabled(true);

        //设置点击chart图对应的数据弹出标注
        MarkerView markerView = new RadarMarkerView(getActivity(), R.layout.chart_radar_markerview);
        markerView.setChartView(chart);
        chart.setMarker(markerView);

        chart.setDragDecelerationFrictionCoef(0.9f);

        // 设置允许缩放拖拉拽
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(false);
        chart.setHighlightPerDragEnabled(true);

        // 如果禁用，可以分别在X轴和Y轴上进行缩放。
        chart.setPinchZoom(true);

        // 设置背景颜色
        chart.setBackgroundColor(Color.TRANSPARENT);

        setData(7, 100);

        chart.animateX(1500);

        //获取图例（仅在设置数据后可用）
        Legend l = chart.getLegend();

        // 图例的相关设置
        l.setForm(Legend.LegendForm.LINE);
        /*l.setTypeface(tfLight);*/
        l.setTextSize(11f);
        l.setTextColor(getResources().getColor(R.color.colorBlackLow));
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        //        l.setYOffset(11f);

        XAxis xAxis = chart.getXAxis();
        /*xAxis.setTypeface(tfLight);*/
        xAxis.setTextSize(11f);
        xAxis.setTextColor(getResources().getColor(R.color.colorBlackLow));
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = chart.getAxisLeft();
        /*leftAxis.setTypeface(tfLight);*/
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMaximum(200f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = chart.getAxisRight();
        /*rightAxis.setTypeface(tfLight);*/
        rightAxis.setTextColor(Color.RED);
        rightAxis.setAxisMaximum(900);
        rightAxis.setAxisMinimum(-200);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);
    }

    private void setData(int count, float range) {

        ArrayList<Entry> values1 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * (range / 2f)) + 50;
            values1.add(new Entry(i, val));
        }

        ArrayList<Entry> values2 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 450;
            values2.add(new Entry(i, val));
        }

        ArrayList<Entry> values3 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 500;
            values3.add(new Entry(i, val));
        }

        LineDataSet set1, set2;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) chart.getData().getDataSetByIndex(1);
            set1.setValues(values1);
            set2.setValues(values2);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values1, "上周");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setCircleColor(ColorTemplate.getHoloBlue());
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(false);
            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);

            // create a dataset and give it a type
            set2 = new LineDataSet(values2, "本周");
            set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set2.setColor(Color.RED);
            set2.setCircleColor(Color.RED);
            set2.setLineWidth(2f);
            set2.setCircleRadius(3f);
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);
            set2.setHighLightColor(Color.rgb(244, 117, 117));
            //set2.setFillFormatter(new MyFillFormatter(900f));

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);  //设置曲线为圆滑的线
            set2.setMode(LineDataSet.Mode.CUBIC_BEZIER);

            // 关联各数据
            LineData data = new LineData(set1, set2);
            data.setValueTextColor(getResources().getColor(R.color.colorBlackLow));
            data.setValueTextSize(9f);

            // 设置数据
            chart.setData(data);
        }
    }

    private void initSwipRefreshLayout(View view){
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout_heartrate);
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
        //对数据进行更新
    }

    private void initRecyclerView(View view){
        historyIsOpen = false;
        final ConstraintLayout checkList = (ConstraintLayout)view.findViewById(R.id.heartrate_constraintLayout_checkList);
        final TextView historyMore = (TextView)view.findViewById(R.id.heartrate_historyMore);

        List<ArrayList<String> > mDatas;
        RecyclerViewAdapter recyclerViewAdapter;
        mDatas = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            ArrayList<String> keyValue = new ArrayList<>();
            keyValue.add("2019-1-16  9:40:16");
            keyValue.add("72次/分钟 正常");
            mDatas.add(keyValue);
        }
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), mDatas);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.heartrate_historyList);
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
