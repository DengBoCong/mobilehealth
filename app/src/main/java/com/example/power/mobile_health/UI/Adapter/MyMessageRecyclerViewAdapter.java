package com.example.power.mobile_health.UI.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.power.mobile_health.UI.Model.Subject;
import com.example.power.mobile_health.R;

import java.util.List;

/**
 * Created by Power on 2019/1/20.
 */

public class MyMessageRecyclerViewAdapter extends RecyclerView.Adapter<MyMessageRecyclerViewAdapter.MyViewHolder> {
    private List<Subject> mDatas;
    private LayoutInflater layoutInflater;
    private Context context;

    public MyMessageRecyclerViewAdapter(Context context, List<Subject> mDatas){
        super();
        this.context = context;
        this.mDatas = mDatas;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyMessageRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.title.setText(mDatas.get(position).getTitle());
        holder.content.setText(mDatas.get(position).getContent());
        holder.imageView.setImageResource(mDatas.get(position).getImg());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_mymessage_recyclerview_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView content;
        private ImageView imageView;

        public MyViewHolder(View view){
            super(view);
            title = (TextView)view.findViewById(R.id.mymessage_recyclerview_item_title);
            content = (TextView)view.findViewById(R.id.mymessage_recyclerview_item_content);
            imageView = (ImageView)view.findViewById(R.id.mymessage_recyclerview_item_Image);
        }
    }
}
