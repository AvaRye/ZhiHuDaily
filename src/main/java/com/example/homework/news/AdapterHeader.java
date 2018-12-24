package com.example.homework.news;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homework.data.ItemBean;
import com.example.homework.R;
import com.example.homework.data.Translation;
import com.example.homework.detail.SecondActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterHeader extends PagerAdapter {
    private List<View> mViewList = new ArrayList<>();
    private int size;
//    private List<ItemBean> topDataSet;
    private List<Translation.TopStoriesBean> topDataSet;
    private Context mContext;

    public AdapterHeader(Context context, List<Translation.TopStoriesBean> topDataSet, int size) {
        this.mContext = context;
        this.topDataSet = topDataSet;
        this.size = size;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        View view = mViewList.get(position);
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_header, container, false);
//        final ItemBean bean = topDataSet.get(position);
        final Translation.TopStoriesBean bean = topDataSet.get(position);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.textView.setText(bean.getTitle());
        Picasso.with(mContext).load(bean.getImage())
                .fit()
                .centerCrop()
                .into(viewHolder.imageView);
        viewHolder.imageView.setColorFilter(Color.GRAY,PorterDuff.Mode.MULTIPLY);
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, SecondActivity.class);
                intent.putExtra("id", bean.getId());
                mContext.startActivity(intent);
            }
        });
        mViewList.add(view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViewList.get(position));
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    static class ViewHolder {
        ConstraintLayout layout;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view) {
            layout = view.findViewById(R.id.header);
            imageView = view.findViewById(R.id.iv_header);
            textView = view.findViewById(R.id.tv_header);
        }
    }

//        public void refreshViews(List<ItemBean> topDataSet,List<View> mViewList){
//        this.topDataSet = topDataSet;
//        this.mViewList = mViewList;
//        notifyDataSetChanged();
//    }

}
