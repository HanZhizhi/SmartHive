package com.space.smarthive.info;

import android.util.Log;

import com.space.smarthive.data.Feed;

import java.util.List;

/**
 * @Author: Space
 * @Date: 2020/10/31 11:09
 */


public class InfoPresenter implements InfoContract.Presenter, InfoContract.LoadDataCallback{
    private static final String TAG = "InfoPresenter";
    private final InfoContract.View mInfoView;
    private InfoModel model;

    public InfoPresenter(InfoContract.View infoView) {
        this.mInfoView = infoView;
        //this.mInfoView.setPresenter(this);

        model = new InfoModel();
    }

    @Override
    public void onSuccess(List<Feed> feeds) {
        mInfoView.updateRecyclerView(feeds);
    }

    @Override
    public void onFailed() {

    }

    @Override
    public void loadFeeds() {
        model.getFeeds(this);
        Log.i(TAG, "loadFeeds: ");
    }

    @Override
    public void start() {
        loadFeeds();
    }
}
