package com.space.smarthive.data;

/**
 * @Author: Space
 * @Date: 2020/10/31 11:01
 */


public class Feed {
    public String title, content, time, url;

    public Feed(String t, String c, String w, String u){
        this.title = t;
        this.content = c;
        this.time = w;
        this.url = u;
    }
}
