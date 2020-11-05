package com.space.smarthive.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.space.smarthive.R;

/**
 * @Author: Space
 * @Date: 2020/10/30 17:51
 */


public class HiveInfoItem extends LinearLayout {
    private static final String TAG = "HiveInfoItem";
    private String item, content;
    private TextView tvName, tvContent;
    private LinearLayout.LayoutParams paramName, paramContent;

    public HiveInfoItem(Context context) {
        this(context, null);
    }

    public HiveInfoItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HiveInfoItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context, attrs);
        initViews(context);
    }

    private void initAttrs(Context context, AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HiveInfoItem);
        item = ta.getString(R.styleable.HiveInfoItem_name);
        content = ta.getString(R.styleable.HiveInfoItem_value);
        ta.recycle();
    }

    private void initViews(Context context){
        tvName = new TextView(context);
        tvContent = new TextView(context);

        tvName.setText(item);
        tvName.setTextSize(34);
        tvName.setTextColor(Color.BLACK);
        tvContent.setText(content);
        tvContent.setTextSize(16);
//
//        paramName = new LinearLayout.LayoutParams(100, 30);
//        paramName.leftMargin = 10;
//        paramName.topMargin = 14;
//        paramName.rightMargin = 10;
//        paramName.bottomMargin = 14;
        addView(tvName);

//        paramContent = new LinearLayout.LayoutParams(100, 30);
//        paramContent.leftMargin = 100;
//        paramContent.topMargin = 14;
//        paramContent.rightMargin = 100;
//        paramContent.bottomMargin = 14;
        addView(tvContent);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        Log.i(TAG, "onMeasure: "+measureWidth+"   "+measureWidthMode+"   "+measureHeight+"   "+measureHeightMode);

        int width = 0, height = 0;
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int childWidth = child.getMeasuredWidth(), childHeight = child.getMeasuredHeight();
            Log.i(TAG, "onMeasure: "+childWidth+"  "+childHeight);
            width += childWidth + 100;
            height = Math.max(childHeight, height);
        }

        setMeasuredDimension((measureWidthMode==MeasureSpec.EXACTLY)?measureWidth:width,(measureHeightMode==MeasureSpec.EXACTLY)?measureHeight:height);
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG, "onLayout: "+l+"  "+t+"  "+r+"  "+b+"  ");

        //tvName.layout(l+10, t, l+10+tvName.getMeasuredWidth(), b);
        //tvContent.layout(l+tvName.getWidth()+100, t, l+tvName.getWidth()+400, b);

        int left=0;
        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            child.layout(left, t, childWidth, childHeight);

            left += childWidth;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
