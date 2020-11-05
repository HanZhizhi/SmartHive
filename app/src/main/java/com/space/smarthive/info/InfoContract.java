package com.space.smarthive.info;

import com.space.smarthive.BasePresenter;
import com.space.smarthive.BaseView;
import com.space.smarthive.data.Feed;

import java.util.List;

/**
 * @Author: Space
 * @Date: 2020/10/31 10:59
 */



public interface InfoContract {
    interface View extends BaseView<Presenter>{
        void updateRecyclerView(List<Feed> feeds);
    }

    interface Presenter extends BasePresenter{
        void loadFeeds();
    }

    interface LoadDataCallback{
        void onSuccess(List<Feed> feeds);
        void onFailed();
    }
}
