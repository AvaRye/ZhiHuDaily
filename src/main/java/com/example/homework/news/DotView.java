package com.example.homework.news;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.homework.R;

public class DotView extends LinearLayout {
    private int pageCount;
    private ImageView[] imageViews;

    public DotView(Context context, int pageCount) {
        super(context);
        this.pageCount = pageCount;
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void initDotView() {
        imageViews = new ImageView[pageCount];
        for (int i = 0; i < pageCount; i++) {
            LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
            );
            margin.setMargins(5, 0, 5, 0);
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new LayoutParams(10, 10));
            imageViews[i] = imageView;
            if (i == 0) {
                imageViews[i].setBackgroundResource(R.drawable.ic_center_focus_strong_white);
            } else {
                imageViews[i].setBackgroundResource(R.drawable.ic_center_focus_weak_white);
            }
        }
    }

    public void changeFocus(int arg) {
        for (int i = 0; i < pageCount; i++) {
            if (arg == i) {
                imageViews[i].setBackgroundResource(R.drawable.ic_center_focus_strong_white);
            } else {
                imageViews[i].setBackgroundResource(R.drawable.ic_center_focus_weak_white);
            }

        }
    }
}
