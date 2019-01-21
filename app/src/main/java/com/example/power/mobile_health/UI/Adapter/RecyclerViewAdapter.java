package com.example.power.mobile_health.UI.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.power.mobile_health.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Power on 2019/1/5.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<ArrayList<String> > mDatas;
    private Context context;
    private LayoutInflater layoutInflater;
    public RecyclerViewAdapter(Context context, List<ArrayList<String> > mDatas){
        this.context = context;
        this.mDatas = mDatas;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.time_tv.setText( mDatas.get(position).get(0));
        holder.value_tv.setText(mDatas.get(position).get(1));
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_home,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends ViewHolder{
        TextView time_tv;
        TextView value_tv;

        public MyViewHolder(View view){
            super(view);
            time_tv = (TextView) view.findViewById(R.id.temperature_time_tv);
            value_tv = (TextView)view.findViewById(R.id.temperature_value_tv);
        }
    }
}
