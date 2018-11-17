package com.example.homework;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterOne extends RecyclerView.Adapter {
    public final static int ITEM_TYPE_DATER = 0;
    public final static int ITEM_TYPE_TEXT = 1;
    private Context mContext;
    private List<ItemBean> dataSet = new ArrayList<>();

    AdapterOne(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ITEM_TYPE_DATER : ITEM_TYPE_TEXT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == ITEM_TYPE_DATER) {
            itemView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_layout_dater, parent, false);
            return new DaterViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_layout, parent, false);
            return new TextViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DaterViewHolder) {
            DaterViewHolder daterViewHolder = (DaterViewHolder) holder;
            ItemBean itemBean1 = dataSet.get(position);
            daterViewHolder.date.setText(itemBean1.getDate());
        } else {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
            ItemBean itemBean2 = dataSet.get(position);
            textViewHolder.text.setText(itemBean2.getText());
            Picasso.with(mContext).load(dataSet.get(position).getImageUrl())
                    .fit()
                    .centerCrop()
                    .into(textViewHolder.image);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void refreshItems(List<ItemBean> dataSet) {
        this.dataSet.clear();
        this.dataSet.addAll(dataSet);
        this.notifyDataSetChanged();
    }

    public void getData(List<ItemBean> dataSet){
        this.dataSet.addAll(dataSet);
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.tv_image);
            text = (TextView) itemView.findViewById(R.id.tv_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    emmmm
                }
            });
        }
    }

    static class DaterViewHolder extends RecyclerView.ViewHolder {
        TextView date;

        public DaterViewHolder(@NonNull View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.dateTextView);
        }
    }

}
