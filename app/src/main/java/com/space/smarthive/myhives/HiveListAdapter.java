package com.space.smarthive.myhives;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.space.smarthive.R;
import com.space.smarthive.viewer.WebViewer;

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
        holder.bleName.setText(name == null ? "unnamed" : name);
        holder.bleAddress.setText(data.get(position).device.getAddress());

        holder.itemView.setOnClickListener(v -> {
            WebViewer.start(context, "Dsd", "dsds");
            Log.i(TAG, "onBindViewHolder: hive item clicked");
        });
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
