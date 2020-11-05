package com.space.smarthive.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.space.smarthive.R;

/**
 * @Author: Space
 * @Date: 2020/10/30 21:06
 */


public class HiveInfoItemCom extends LinearLayout {
    private TextView tvName, tvContent;

    public HiveInfoItemCom(Context context) {
        this(context, null);
    }

    public HiveInfoItemCom(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HiveInfoItemCom(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.info_item, this, true);
        tvName = findViewById(R.id.hive_item_name);
        tvContent = findViewById(R.id.hive_item_content);
    }

    public void setValues(String name, String content){
        tvName.setText(name);
        tvContent.setText(content);
    }

}
