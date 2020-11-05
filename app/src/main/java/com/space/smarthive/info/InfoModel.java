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
        feeds.add(new Feed("中国在世界上排名第一的行业，在疫情下陷入最惨境遇",
                "这次的疫情给许多产业都带来了或大或小的冲击，有的甚至是致命的。例如，养蜂业。二月中旬，因为疫情，蜜蜂不能顺利转场，蜜蜂集体中毒，大量死亡，面对无法挽回的经济损失，养蜂人刘德成不堪重负选结束了自己的生命。",
                "2020:09:67",
                "https://new.qq.com/omn/20200323/20200323A0M5MZ00.html"));
        feeds.add(new Feed("邵武：办好培训班 加快农村养蜂业发展",
                "近日，邵武市专门举办了一期“种子工程”养蜂技术培训班，目的是为了培育养蜂种子能手，并希望通过他们的辐射带动，加快农村养蜂业发展。",
                "2020-10-24",
                "http://media.fjtv.net/folder852/2020-10-24/2369705.html"));
        feeds.add(new Feed("贵州省黔东南州黎平县做好疫情防控 稳步推进养蜂业",
                "连日来，贵州省黔东南州黎平县在做好疫情防控的同时，稳步推进养殖林下养蜂业。据悉，今年黎平县德化乡计划养殖蜜蜂2000箱，将覆盖贫困群众200余户,预计可实现户均增收7500余元。",
                "2030:03:67",
                "https://www.cfsn.cn/front/web/site.shengnewshow?sjid=24&newsid=24660"));

        callback.onSuccess(feeds);
    }
}
