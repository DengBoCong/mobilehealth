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

public class DoctorListRecyclerViewAdapter extends RecyclerView.Adapter<DoctorListRecyclerViewAdapter.MyViewHolder> {
    private List<Subject> datas;
    private Context mContext;
    private LayoutInflater mLiLayoutInflater;

    public DoctorListRecyclerViewAdapter(Context context, List<Subject> mDatas){
        this.mContext = context;
        this.datas = mDatas;
        this.mLiLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(mLiLayoutInflater.inflate(R.layout.fragment_privatedoctor_doctorlist_recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleTextView.setText(datas.get(position).getTitle());
        holder.contentTextView.setText(datas.get(position).getContent());
        holder.imageView.setImageResource(datas.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;
        private TextView contentTextView;
        private ImageView imageView;

        public MyViewHolder(View view){
            super(view);
            titleTextView = (TextView)view.findViewById(R.id.privatedoctor_recyclerview_doctorListItem_title);
            contentTextView = (TextView)view.findViewById(R.id.privatedoctor_recyclerview_doctorListItem_content);
            imageView = (ImageView)view.findViewById(R.id.privatedoctor_recyclerview_doctorListItem_Image);
        }
    }
}
