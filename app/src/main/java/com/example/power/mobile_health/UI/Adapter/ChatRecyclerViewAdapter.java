package com.example.power.mobile_health.UI.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.power.mobile_health.UI.Model.ChatModel;
import com.example.power.mobile_health.UI.Model.ItemModel;
import com.example.power.mobile_health.R;

import java.util.ArrayList;

/**
 * Created by Power on 2019/1/19.
 */

public class ChatRecyclerViewAdapter extends RecyclerView.Adapter<ChatRecyclerViewAdapter.BaseAdapter> {
    private ArrayList<ItemModel> dataList = new ArrayList<>();

    public void replaceAll(ArrayList<ItemModel> list) {
        dataList.clear();
        if (list != null && list.size() > 0) {
            dataList.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<ItemModel> list) {
        if (dataList != null && list != null) {
            dataList.addAll(list);
            notifyItemRangeChanged(dataList.size(),list.size());
        }

    }

    @Override
    public ChatRecyclerViewAdapter.BaseAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ItemModel.CHAT_A:
                return new ChatAViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat_object, parent, false));
            case ItemModel.CHAT_B:
                return new ChatBViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat_self, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ChatRecyclerViewAdapter.BaseAdapter holder, int position) {
        holder.setData(dataList.get(position).object);
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).type;
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public class BaseAdapter extends RecyclerView.ViewHolder {

        public BaseAdapter(View itemView) {
            super(itemView);
        }

        void setData(Object object) {

        }
    }

    private class ChatAViewHolder extends BaseAdapter {
        private ImageView ic_user;
        private TextView tv;

        public ChatAViewHolder(View view) {
            super(view);
            ic_user = (ImageView) itemView.findViewById(R.id.ic_user);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }

        @Override
        void setData(Object object) {
            super.setData(object);
            ChatModel model = (ChatModel) object;
            /*Picasso.with(itemView.getContext()).load(model.getIcon()).placeholder(R.mipmap.ic_launcher).into(ic_user);*/
            ic_user.setImageResource(R.mipmap.person);
            tv.setText(model.getContent());
        }
    }

    private class ChatBViewHolder extends BaseAdapter {
        private ImageView ic_user;
        private TextView tv;

        public ChatBViewHolder(View view) {
            super(view);
            ic_user = (ImageView) itemView.findViewById(R.id.ic_user);
            tv = (TextView) itemView.findViewById(R.id.tv);

        }

        @Override
        void setData(Object object) {
            super.setData(object);
            ChatModel model = (ChatModel) object;
            /*Picasso.with(itemView.getContext()).load(model.getIcon()).placeholder(R.mipmap.ic_launcher).into(ic_user);*/
            ic_user.setImageResource(R.mipmap.person);
            tv.setText(model.getContent());
        }
    }
}
