package com.space.smarthive.info;

import com.space.smarthive.data.Feed;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Space
 * @Date: 2020/10/31 11:04
 */


public class InfoModel {
    public void getFeeds(InfoContract.LoadDataCallback callback){
        List<Feed> feeds = new ArrayList<>();

        // 添加数据
        feeds.add(new Feed("基于STM32的智慧蜂箱设计", "智慧蜂箱是智慧养蜂系统的硬件基础，设计一种安全、可靠、智能的智慧蜂箱是本项目的重要研发内容之一。该部分内容主要包括微控制器的选择、无线通信方式的选择、Web服务器的选择和TCP/IP网络协议栈的选择。", "2020:09:67"));
        feeds.add(new Feed("基于Android的智慧养蜂APP开发", "智慧养蜂手机APP是智慧养蜂系统的软件基础，也是系统与用户交互的主要渠道，设计一种稳定、高效、友好的智慧养蜂APP是本项目的重要研发内容之一", "rer83943"));
        feeds.add(new Feed("蜂蜂蜂蜂", "大家觉得经济的巨大经济等等等等等等等等的代价", "2030:03:67"));

        callback.onSuccess(feeds);
    }
}
