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
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Power on 2019/1/12.
 */

public class WeightIndexFragment extends Fragment {
    private CombinedChart chart;
    private final int count = 12;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean historyIsOpen;

    private final String[] months = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager_weight, container, false);

        initLayout(view);

        return view;
    }

    public void initLayout(View view){
        initSwipRefreshLayout(view);
        initDataTextView(view);
        initRecyclerView(view);

        chart = view.findViewById(R.id.combinedChart_weight);
        chart.getDescription().setEnabled(false);
        chart.setBackgroundColor(Color.WHITE);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.setHighlightFullBarEnabled(false);

        // draw bars behind lines
        chart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE, CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.SCATTER
        });

        Legend l = chart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter() {
            public String getFormattedValue(float value) {
                return months[(int) value % months.length];
            }
        });

        CombinedData data = new CombinedData();

        data.setData(generateLineData());
        data.setData(generateBarData());
        /*data.setData(generateBubbleData());*/
        data.setData(generateScatterData());
        /*data.setData(generateCandleData());*/
        /*data.setValueTypeface(tfLight);*/

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        chart.setData(data);
        chart.invalidate();
    }

    private LineData generateLineData() {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<>();

        for (int index = 0; index < count; index++)
            entries.add(new Entry(index + 0.5f, getRandom(15, 5)));

        LineDataSet set = new LineDataSet(entries, "近一月体重(kg)");
        set.setColor(Color.rgb(240, 238, 70));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(240, 238, 70));
        set.setCircleRadius(5f);
        set.setFillColor(Color.rgb(240, 238, 70));
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return d;
    }

    private BarData generateBarData() {

        ArrayList<BarEntry> entries1 = new ArrayList<>();
        ArrayList<BarEntry> entries2 = new ArrayList<>();

        for (int index = 0; index < count; index++) {
            entries1.add(new BarEntry(0, getRandom(25, 25)));

            // stacked
            entries2.add(new BarEntry(0, new float[]{0, 0}));
        }

        BarDataSet set1 = new BarDataSet(entries1, "近一月体重(斤)");
        set1.setColor(Color.rgb(60, 220, 78));
        set1.setValueTextColor(Color.rgb(60, 220, 78));
        set1.setValueTextSize(10f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        BarDataSet set2 = new BarDataSet(entries2, "");
        /*set2.setStackLabels(new String[]{"Stack 1", "Stack 2"});*/
        /*set2.setColors(Color.rgb(61, 165, 255), Color.rgb(23, 197, 255));
        set2.setValueTextColor(Color.rgb(61, 165, 255));
        set2.setValueTextSize(10f);
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);*/

        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.45f; // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData d = new BarData(set1, set2);
        d.setBarWidth(barWidth);

        // make this BarData object grouped
        d.groupBars(0, groupSpace, barSpace); // start at x = 0

        return d;
    }

    private ScatterData generateScatterData() {

        ScatterData d = new ScatterData();

        ArrayList<Entry> entries = new ArrayList<>();

        for (float index = 0; index < count; index += 0.5f)
            entries.add(new Entry(index + 0.25f, 100f));

        ScatterDataSet set = new ScatterDataSet(entries, "目标体重(斤)");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setScatterShapeSize(7.5f);
        set.setDrawValues(false);
        set.setValueTextSize(10f);
        d.addDataSet(set);

        return d;
    }

    private CandleData generateCandleData() {

        CandleData d = new CandleData();

        ArrayList<CandleEntry> entries = new ArrayList<>();

        for (int index = 0; index < count; index += 2)
            entries.add(new CandleEntry(index + 1f, 90, 70, 85, 75f));

        CandleDataSet set = new CandleDataSet(entries, "Candle DataSet");
        set.setDecreasingColor(Color.rgb(142, 150, 175));
        set.setShadowColor(Color.DKGRAY);
        set.setBarSpace(0.3f);
        set.setValueTextSize(10f);
        set.setDrawValues(false);
        d.addDataSet(set);

        return d;
    }

    private BubbleData generateBubbleData() {

        BubbleData bd = new BubbleData();

        ArrayList<BubbleEntry> entries = new ArrayList<>();

        for (int index = 0; index < count; index++) {
            float y = getRandom(10, 105);
            float size = getRandom(100, 105);
            entries.add(new BubbleEntry(index + 0.5f, y, size));
        }

        BubbleDataSet set = new BubbleDataSet(entries, "Bubble DataSet");
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.WHITE);
        set.setHighlightCircleWidth(1.5f);
        set.setDrawValues(true);
        bd.addDataSet(set);

        return bd;
    }

    private float getRandom(float range, float start) {
        return (float) (Math.random() * range) + start;
    }

    private void initSwipRefreshLayout(View view){
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout_weight);
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
        final ConstraintLayout checkList = (ConstraintLayout)view.findViewById(R.id.weight_constraintLayout_checkList);
        final TextView historyMore = (TextView)view.findViewById(R.id.weight_historyMore);

        List<ArrayList<String> > mDatas;
        RecyclerViewAdapter recyclerViewAdapter;
        mDatas = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            ArrayList<String> keyValue = new ArrayList<>();
            keyValue.add("2019-1-16  9:40:16");
            keyValue.add("60kg  还差10kg");
            mDatas.add(keyValue);
        }
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), mDatas);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.weight_historyList);
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
