package com.space.smarthive.hivemanage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.space.smarthive.R;
import com.space.smarthive.databinding.ActivityHivemanBinding;

public class HivemanActivity extends AppCompatActivity {
    ActivityHivemanBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_hiveman);
        viewBinding = ActivityHivemanBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        viewBinding.hiveCom.setValues("温度", "36.1°C");
        viewBinding.hiveCom2.setValues("湿度", "65.7% rh");
        viewBinding.hiveCom3.setValues("蜂箱重量", "23kg");
    }
}