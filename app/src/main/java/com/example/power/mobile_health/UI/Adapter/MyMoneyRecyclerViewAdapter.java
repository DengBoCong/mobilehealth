package com.example.power.mobile_health.UI.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.power.mobile_health.UI.Model.TIModel;
import com.example.power.mobile_health.R;

import java.util.List;

/**
 * Created by Power on 2019/1/21.
 */

public class MyMoneyRecyclerViewAdapter extends RecyclerView.Adapter<MyMoneyRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<TIModel> mDatas;
    private LayoutInflater layoutInflater;

    public MyMoneyRecyclerViewAdapter(Context context, List<TIModel> mDatas){
        super();
        this.context = context;
        this.mDatas = mDatas;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_mymoney_recyclerview_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(mDatas.get(position).getText());
        holder.imageView.setImageResource(mDatas.get(position).getImage());
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;

        public MyViewHolder(View view){
            super(view);
            textView = (TextView)view.findViewById(R.id.mymoneyActivity_recyclerView_name);
            imageView = (ImageView)view.findViewById(R.id.mymoneyActivity_reclerView_Image);
        }
    }
}
