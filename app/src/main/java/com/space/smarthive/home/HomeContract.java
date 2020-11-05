package com.space.smarthive.home;

import com.space.smarthive.BasePresenter;
import com.space.smarthive.BaseView;

/**
 * @Author: Space
 * @Date: 2020/10/29 21:03
 */


public interface HomeContract {
    interface Presenter extends BasePresenter{
    }

    interface View extends BaseView<Presenter>{

    }
}
