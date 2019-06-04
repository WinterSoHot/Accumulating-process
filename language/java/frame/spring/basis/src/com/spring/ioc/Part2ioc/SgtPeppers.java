package com.spring.ioc.Part2ioc;

import org.springframework.stereotype.Component;

/**
 * Created by gudongxian on 2017/3/15.
 * CD实现类
 */

@Component
public class SgtPeppers implements CompactDisc {

    private  String title="方圆几里";
    private  String artist = "薛之谦";
    @Override
    public void play() {
        System.out.println("正在播放  "+title+"  作者："+artist);
    }
}
