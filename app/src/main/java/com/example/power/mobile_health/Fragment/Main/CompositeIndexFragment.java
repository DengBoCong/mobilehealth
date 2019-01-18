package com.example.power.mobile_health.Fragment.Main;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Toast;

import com.example.power.mobile_health.R;
import com.example.power.mobile_health.Utils.MpChartUtils.RadarMarkerView;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;

/**
 * Created by Power on 2019/1/12.
 */

public class CompositeIndexFragment extends Fragment {
    private RadarChart chart;
    private SwipeRefreshLayout swipeRefreshLayout;
    /*private Typeface tfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_viewpager_composite, container, false
        );

        initLayout(view);
        return view;
    }

    public void initLayout(View view){
        chart = view.findViewById(R.id.radarChart_composite);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout_composite);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Toast.makeText(getActivity(), "正在下拉刷新", Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);//结束动画
            }
        });

        /*chart.setBackgroundColor(getResources().getColor(R.color.colorOldMain));*//*Color.rgb(60, 65, 82)*/
        chart.getDescription().setEnabled(false);
        chart.setBackgroundColor(Color.TRANSPARENT);

        /*设置总布局相关设置*/
        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.LTGRAY);
        chart.setWebLineWidthInner(1f);
        chart.setWebColorInner(Color.LTGRAY);
        chart.setWebAlpha(100);

        //设置是否可以触摸，如为false，则不能拖动，缩放等
        chart.setTouchEnabled(true);

        //设置点击chart图对应的数据弹出标注
        MarkerView markerView = new RadarMarkerView(getActivity(), R.layout.chart_radar_markerview);
        markerView.setChartView(chart);
        chart.setMarker(markerView);

        setData();

        chart.animateXY(1400, 1400);/*, Easing.EasingOption.EaseInOutQuad*/

        XAxis xAxis = chart.getXAxis();
        /*xAxis.setTypeface(tfLight);*/
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(){
            private final String[] mActivities = new String[]{"心率", "体温", "血压", "体重", "血氧", "血脂"};

            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(getResources().getColor(R.color.colorBlackLow));

        YAxis yAxis = chart.getYAxis();
        /*yAxis.setTypeface(tfLight);*/
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(getResources().getColor(R.color.colorBlackLow));

        /*AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(chart, "rotation", 0, 360);
        objectAnimator.setRepeatCount(Animation.INFINITE);
        animatorSet.setDuration(5000);
        animatorSet.playTogether(objectAnimator);
        animatorSet.start();

        chart.setRotation(360);*/
    }

    private void setData() {

        float mul = 80;
        float min = 20;
        int cnt = 6;

        ArrayList<RadarEntry> entries1 = new ArrayList<>();
        ArrayList<RadarEntry> entries2 = new ArrayList<>();

        // NOTE: 条目添加到条目数组时的顺序决定了它们在图表中心的位置
        for (int i = 0; i < cnt; i++) {
            float val1 = (float) (Math.random() * mul) + min;
            entries1.add(new RadarEntry(val1));

            float val2 = (float) (Math.random() * mul) + min;
            entries2.add(new RadarEntry(val2));
        }

        RadarDataSet set1 = new RadarDataSet(entries1, "上周");
        set1.setColor(Color.rgb(54, 162, 235));
        set1.setFillColor(Color.rgb(54, 162, 235));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarDataSet set2 = new RadarDataSet(entries2, "本周");
        set2.setColor(Color.rgb(75, 192, 192));
        set2.setFillColor(Color.rgb(75, 192, 192));
        set2.setDrawFilled(true);
        set2.setFillAlpha(180);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
        /*data.setValueTypeface(tfLight);*/
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(getResources().getColor(R.color.colorBlackLow));

        chart.setData(data);
        chart.invalidate();
    }
}
