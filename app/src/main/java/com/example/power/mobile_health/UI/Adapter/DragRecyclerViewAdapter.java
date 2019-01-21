package com.example.power.mobile_health.UI.Adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
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
 * Created by Power on 2019/1/18.
 */

public class DragRecyclerViewAdapter extends RecyclerView.Adapter<DragRecyclerViewAdapter.MyViewHolder> {
    private List<Subject> datas;
    private Context mContext;
    private LayoutInflater mLiLayoutInflater;

    public DragRecyclerViewAdapter(List<Subject> datas, Context context) {
        this.datas = datas;
        this.mContext = context;
        this.mLiLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public DragRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mLiLayoutInflater.inflate(R.layout.activity_privatedoctor_recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(DragRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tv_title.setText(datas.get(position).getTitle());
        holder.tv_content.setText(datas.get(position).getContent());
        holder.img.setImageResource(datas.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public TextView tv_content;
        public ImageView img;
        public ConstraintLayout ll_item, ll_hidden;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.privatedoctor_recyclerview_messageItem_title);
            tv_content = (TextView)itemView.findViewById(R.id.privatedoctor_recyclerview_messageItem_content);
            img = (ImageView) itemView.findViewById(R.id.privatedoctor_recyclerview_messageItem_Image);

            ll_item = (ConstraintLayout) itemView.findViewById(R.id.privatedoctor_recyclerview_item);
            ll_hidden = (ConstraintLayout) itemView.findViewById(R.id.privatedoctor_recyclerview_messageItem_rightLayout);
        }
    }
}
