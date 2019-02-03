package com.example.homework.news

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.constraint.ConstraintLayout
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.homework.data.ItemBean
import com.example.homework.R
import com.example.homework.data.Translation
import com.example.homework.detail.SecondActivity
import com.squareup.picasso.Picasso

import java.util.ArrayList

class AdapterHeader(private val mContext: Context, private val topDataSet: List<Translation.TopStoriesBean>, private val size: Int) : PagerAdapter() {
    private val mViewList = ArrayList<View>()

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_header, container, false)
        val bean = topDataSet[position % size]
        val viewHolder = ViewHolder(view)
        viewHolder.textView.text = bean.title
        Picasso.with(mContext).load(bean.image)
                .fit()
                .centerCrop()
                .into(viewHolder.imageView)
        viewHolder.imageView.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
        viewHolder.layout.setOnClickListener {
            val intent = Intent()
            intent.setClass(mContext, SecondActivity::class.java!!)
            intent.putExtra("id", bean.id)
            mContext.startActivity(intent)
        }
        mViewList.add(view)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(mViewList[position])
    }

    override fun getCount(): Int {
        return 1000000000//emmmmmm随便多大是吧
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o
    }

    internal class ViewHolder(view: View) {
        var layout: ConstraintLayout
        var imageView: ImageView
        var textView: TextView

        init {
            layout = view.findViewById(R.id.header)
            imageView = view.findViewById(R.id.iv_header)
            textView = view.findViewById(R.id.tv_header)
        }
    }


}
