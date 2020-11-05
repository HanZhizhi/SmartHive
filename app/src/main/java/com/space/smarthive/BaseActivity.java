package com.space.smarthive;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;

/**
 * @Author: Space
 * @Date: 2020/11/5 21:03
 */


public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImmersionBar.with(this)
                .statusBarColor(R.color.darkWhite)
                .navigationBarColor(R.color.darkWhite)
                .fitsSystemWindows(true)
                .autoDarkModeEnable(true)
                .init();
    }
}
