package com.example.power.mobile_health.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.power.mobile_health.R;

import java.util.List;

/**
 * Created by Power on 2019/1/18.
 */

public class HealthReporterHistoryRecyclerViewAdapter extends RecyclerView.Adapter<HealthReporterHistoryRecyclerViewAdapter.MyViewHolder> {
    private List<String[]> mDatas;
    private Context context;
    private LayoutInflater layoutInflater;

    public HealthReporterHistoryRecyclerViewAdapter(Context context, List<String[]> mDatas){
        this.mDatas = mDatas;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleTextView.setText(mDatas.get(position)[0]);
        holder.contentTextView.setText(mDatas.get(position)[1]);
        holder.flagTextView.setText(mDatas.get(position)[2]);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_healthreporter_recyclerview_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;
        private TextView contentTextView;
        private TextView flagTextView;

        public MyViewHolder(View view){
            super(view);
            titleTextView = view.findViewById(R.id.healthreporterActivity_recyclerView_itemTitle);
            contentTextView = view.findViewById(R.id.healthreporterActivity_recyclerView_itemContent);
            flagTextView = view.findViewById(R.id.healthreporterActivity_recyclerView_itemFlag);
        }
    }
}
