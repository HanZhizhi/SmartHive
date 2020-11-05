package com.space.smarthive.utils;

import android.content.Context;

import androidx.core.content.ContextCompat;

/**
 * @Author: Space
 * @Date: 2020/10/30 16:57
 */


public class ColorUtil {
    public static int getColor(Context context, int colorId){
        return ContextCompat.getColor(context,colorId);
    }
}
