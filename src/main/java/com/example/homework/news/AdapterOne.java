package com.example.homework.news;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homework.R;
import com.example.homework.data.Translation;
import com.example.homework.detail.SecondActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterOne extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int ITEM_TYPE_HEADER = 0;
    private final static int ITEM_TYPE_DATE = 1;
    private final static int ITEM_TYPE_NEWS = 2;
    private Context mContext;
    //    private List<ItemBean> dataSet = new ArrayList<>();
//    private List<ItemBean> topDataSet = new ArrayList<>();
    private Translation translation = new Translation();
    private List<Translation.StoriesBean> dataSet = new ArrayList<>();
    private List<Translation.TopStoriesBean> topDataSet = new ArrayList<>();

    public AdapterOne(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_HEADER;
        } else if (true) {
            return ITEM_TYPE_NEWS;
        } else {
            return ITEM_TYPE_DATE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == ITEM_TYPE_HEADER) {
            itemView = LayoutInflater.from(mContext)
                    .inflate(R.layout.viewpager, parent, false);
            return new HeaderViewHolder(itemView);
        } else if (viewType == ITEM_TYPE_DATE) {
            itemView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_layout_date, parent, false);
            return new DateViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_layout, parent, false);
            return new NewsViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            final HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            AdapterHeader adapterHeader = new AdapterHeader(mContext, topDataSet, topDataSet.size());
            headerViewHolder.viewPager.setAdapter(adapterHeader);

            //TODO:dotView不造行不行
//            this.dotView = headerViewHolder.dotView;
            headerViewHolder.dotView = new DotView(mContext,topDataSet.size());
            headerViewHolder.dotView.initDotView();
            headerViewHolder.dotView.setBackgroundColor(222222);
            headerViewHolder.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {
                }

                @Override
                public void onPageSelected(int i) {
                    headerViewHolder.dotView.changeFocus(i);
                }

                @Override
                public void onPageScrollStateChanged(int i) {
                }
            });
        } else if (holder instanceof NewsViewHolder) {
            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
//            final ItemBean itemBean = dataSet.get(position);
            final Translation.StoriesBean storiesBean = dataSet.get(position);
            newsViewHolder.text.setText(storiesBean.getTitle());
            Picasso.with(mContext).load(storiesBean.getImages().get(0))
                    .fit()
                    .centerCrop()
                    .into(newsViewHolder.image);
            newsViewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, SecondActivity.class);
                    intent.putExtra("id", storiesBean.getId());
                    mContext.startActivity(intent);
                }
            });
        } else {
            DateViewHolder dateViewHolder = (DateViewHolder) holder;
            if (position == 1) {
                dateViewHolder.textView.setText("今日关注");
            } else {
                dateViewHolder.textView.setText(translation.getDate());
            }
        }
    }

    @Override
    public int getItemCount() {
        return translation.getStories().size();
    }

    public void initItems(Translation translation) {
        this.translation = translation;
        this.dataSet = translation.getStories();
        this.topDataSet = translation.getTop_stories();
        notifyDataSetChanged();
    }

    public void initItems2(Translation translation) {
        this.translation = translation;
        this.dataSet = translation.getStories();
        notifyDataSetChanged();
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        ViewPager viewPager;
        ConstraintLayout constraintLayout;
        DotView dotView;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.view_pager);
            constraintLayout = itemView.findViewById(R.id.viewpager_layout);
        }
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        CardView layout;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item);
            image = itemView.findViewById(R.id.iv_news);
            text = itemView.findViewById(R.id.tv_news);
        }
    }

    static class DateViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.dateTextView);
        }
    }
}
