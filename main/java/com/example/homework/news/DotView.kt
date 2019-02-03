package com.example.homework.news

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout

import com.example.homework.R

class DotView : LinearLayout {
    private var pageCount: Int = 0
    private var imageViews: Array<ImageView>? = null

    constructor(context: Context, pageCount: Int) : super(context) {
        this.pageCount = pageCount
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    fun initDotView() {
        for (i in 0 until pageCount) {
            val margin = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            )
            margin.setMargins(5, 0, 5, 0)
            val imageView = ImageView(context)
            imageView.layoutParams = LinearLayout.LayoutParams(10, 10)
            imageViews!![i] = imageView
            if (i == 0) {
                imageViews!![i].setBackgroundResource(R.drawable.ic_center_focus_strong_white)
            } else {
                imageViews!![i].setBackgroundResource(R.drawable.ic_center_focus_weak_white)
            }
        }
    }

    fun changeFocus(arg: Int) {
        for (i in 0 until pageCount) {
            if (arg == i) {
                imageViews!![i].setBackgroundResource(R.drawable.ic_center_focus_strong_white)
            } else {
                imageViews!![i].setBackgroundResource(R.drawable.ic_center_focus_weak_white)
            }

        }
    }
}
