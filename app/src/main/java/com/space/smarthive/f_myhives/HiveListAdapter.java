package com.space.smarthive.f_myhives;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.space.smarthive.R;
import com.space.smarthive.hivemanage.HivemanActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Space
 * @Date: 2020/11/1 21:10
 */


class HiveHolder extends RecyclerView.ViewHolder{
    TextView bleName, bleAddress;

    public HiveHolder(@NonNull View itemView) {
        super(itemView);

        bleName=itemView.findViewById(R.id.tv_hive_item_device_name);
        bleAddress=itemView.findViewById(R.id.tv_hive_item_address);
    }
}


public class HiveListAdapter extends RecyclerView.Adapter<HiveHolder> {
    private static final String TAG = "HiveListAdapter";

    private List<BleBean> data;
    private Context context;


    public HiveListAdapter(RecyclerView rc){
        context = rc.getContext();

        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public HiveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hive_item, parent, false);
        return new HiveHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HiveHolder holder, int position) {
        String name = data.get(position).device.getName();
        if (name == null) name = "未命名蜂箱";
        holder.bleName.setText(name);
        holder.bleAddress.setText(data.get(position).device.getAddress());

        String finalName = name;
        holder.itemView.setOnClickListener(v -> {
            startHiveMan(finalName);
            Log.i(TAG, "onBindViewHolder: hive item clicked");
        });
    }

    /*
     启动蜂箱管理页面
     */
    private void startHiveMan(String hiveName){
        Bundle hiveData = new Bundle();
        hiveData.putString("hive_name", hiveName);
        Intent hmIntent = new Intent(context, HivemanActivity.class);
        hmIntent.putExtras(hiveData);
        context.startActivity(hmIntent);
    }


    public void addDevice(BleBean l){
        if (!data.contains(l)){
            data.add(l);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
