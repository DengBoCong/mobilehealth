package com.example.power.mobile_health.UI.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.power.mobile_health.R;

import java.util.List;

/**
 * Created by Power on 2019/1/17.
 */

public class PersonMessageRecyclerViewAdapter extends RecyclerView.Adapter<PersonMessageRecyclerViewAdapter.MyHolder> {
    private List<Integer> mImageDatas;
    private List<String[]> mTextDatas;
    private LayoutInflater layoutInflater;
    private Context context;

    public PersonMessageRecyclerViewAdapter(Context context, List<Integer> mImageDatas, List<String[]> mTextDatas){
        this.context = context;
        this.mImageDatas = mImageDatas;
        this.mTextDatas = mTextDatas;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mImageDatas.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.imageView.setImageResource(mImageDatas.get(position));
        holder.keyTextView.setText(mTextDatas.get(position)[0]);
        holder.valueTextView.setText(mTextDatas.get(position)[1]);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_person_message_item, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView keyTextView;
        private TextView valueTextView;

        public MyHolder(View view){
            super(view);
            /*imageView = (ImageView)view.findViewById(R.id.personActivity_reclerView_itemImage);
            keyTextView = (TextView)view.findViewById(R.id.personActivity_messageRecyclerView_nameKey);
            valueTextView = (TextView)view.findViewById(R.id.personActivity_messageRecyclerView_nameValue);*/
        }

    }
}
