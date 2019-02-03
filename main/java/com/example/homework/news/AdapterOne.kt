package com.example.homework.news

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.view.ViewPager
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.homework.R
import com.example.homework.data.Translation
import com.example.homework.detail.SecondActivity
import com.squareup.picasso.Picasso

import java.util.ArrayList

class AdapterOne(private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var translation = Translation()
    private var dataSet: MutableList<Translation.StoriesBean> = ArrayList()
    private var topDataSet: MutableList<Translation.TopStoriesBean> = ArrayList()

    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> ITEM_TYPE_HEADER
            position == 1 -> ITEM_TYPE_DATE
            dataSet.size - moreDataNum == position - 1 -> ITEM_TYPE_DATE
            else -> ITEM_TYPE_NEWS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View
        when (viewType) {
            ITEM_TYPE_HEADER -> {
                itemView = LayoutInflater.from(mContext)
                        .inflate(R.layout.viewpager, parent, false)
                return HeaderViewHolder(itemView)
            }
            ITEM_TYPE_DATE -> {
                itemView = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_layout_date, parent, false)
                return DateViewHolder(itemView)
            }
            else -> {
                itemView = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_layout, parent, false)
                return NewsViewHolder(itemView)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                val adapterHeader = AdapterHeader(mContext, topDataSet, topDataSet.size)
                holder.viewPager.adapter = adapterHeader
                //TODO:dotView不造行不行
                holder.dotView = DotView(mContext, topDataSet.size)

                holder.dotView!!.initDotView()
                holder.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrolled(i: Int, v: Float, i1: Int) {}

                    override fun onPageSelected(i: Int) {
                        holder.dotView!!.changeFocus(i)
                    }

                    override fun onPageScrollStateChanged(i: Int) {}
                })
                holder.dotView!!.id = R.id.dot_view
                val constraintSet = ConstraintSet()
                constraintSet.clone(holder.constraintLayout)
                constraintSet.connect(R.id.dot_view, ConstraintSet.LEFT, R.id.viewpager_layout, ConstraintSet.LEFT, 10)
                constraintSet.connect(R.id.dot_view, ConstraintSet.RIGHT, R.id.viewpager_layout, ConstraintSet.RIGHT, 10)
                constraintSet.connect(R.id.dot_view, ConstraintSet.BOTTOM, R.id.viewpager_layout, ConstraintSet.BOTTOM, 10)
                constraintSet.applyTo(holder.constraintLayout)

            }
            is NewsViewHolder -> {
                val storiesBean = dataSet[position]
                holder.text.text = storiesBean.title
                Picasso.with(mContext).load(storiesBean.images!![0])
                        .fit()
                        .centerCrop()
                        .into(holder.image)
                holder.layout.setOnClickListener {
                    val intent = Intent()
                    intent.setClass(mContext, SecondActivity::class.java!!)
                    intent.putExtra("id", storiesBean.id)
                    mContext.startActivity(intent)
                }
            }
            else -> {
                val dateViewHolder = holder as DateViewHolder
                if (position == 1) {
                    dateViewHolder.textView.text = "今日关注"
                } else {
                    dateViewHolder.textView.text = translation.date
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return translation.stories.size
    }

    fun initItems(translation: Translation) {
        this.translation = translation
        this.dataSet = translation.stories
        this.topDataSet = translation.top_stories
        moreDataNum = translation.stories.size
        positionUp = 2
        notifyDataSetChanged()
    }

    fun refreshItems(translation: Translation) {
        this.translation = translation
        this.dataSet.clear()
        this.topDataSet.clear()
        this.dataSet.addAll(translation.stories)
        this.topDataSet.addAll(translation.top_stories)
        moreDataNum = translation.stories.size
        positionUp = 2
        notifyDataSetChanged()
    }

    fun loadMoreItems(translation: Translation) {
        positionUp++
        this.translation = translation
        this.dataSet.addAll(translation.stories)
        moreDataNum = translation.stories.size

        notifyDataSetChanged()
    }

    internal class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var viewPager: ViewPager = itemView.findViewById(R.id.view_pager)
        var constraintLayout: ConstraintLayout = itemView.findViewById(R.id.viewpager_layout)
        var dotView: DotView? = null

    }

    internal class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.iv_news)
        var text: TextView = itemView.findViewById(R.id.tv_news)
        var layout: CardView = itemView.findViewById(R.id.item)

    }

    internal class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.dateTextView)

    }

    companion object {
        private const val ITEM_TYPE_HEADER = 0
        private const val ITEM_TYPE_DATE = 1
        private const val ITEM_TYPE_NEWS = 2
        private var moreDataNum = 0
        private var positionUp = 2
    }
}
