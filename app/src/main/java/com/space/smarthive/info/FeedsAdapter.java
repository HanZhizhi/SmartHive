package com.space.smarthive.info;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.space.smarthive.R;
import com.space.smarthive.data.Feed;
import com.space.smarthive.hivemanage.HivemanActivity;
import com.space.smarthive.viewer.WebViewer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Space
 * @Date: 2020/10/31 11:42
 */

class FeedViewHolder extends RecyclerView.ViewHolder{
    
    public ImageView ivIcon;
    public TextView tvTitle, tvContent, tvTime;

    public FeedViewHolder(@NonNull View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.tv_feed_item_title);
        tvContent = itemView.findViewById(R.id.tv_feed_item_note);
    }
}


public class FeedsAdapter extends RecyclerView.Adapter<FeedViewHolder>{
    private static final String TAG = "FeedsAdapter";
    private List<Feed> data;
    private Context context;

    public FeedsAdapter(RecyclerView recyclerView){
        context = recyclerView.getContext();

        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        holder.tvTitle.setText(data.get(position).title);
        holder.tvContent.setText(data.get(position).content);

        holder.itemView.setOnClickListener(v -> {
            WebViewer.start(context, data.get(position).url, data.get(position).title);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Feed> feeds){
        data.clear();
        data.addAll(feeds);
        notifyDataSetChanged();

        Log.i(TAG, "setData: ");
    }
}
