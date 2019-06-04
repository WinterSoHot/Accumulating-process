package com.spring.ioc.Part2ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gudongxian on 2017/3/15.
 */
@Component(value = "cdplayer")
public class CDPlayer implements  MediaPlayer {

    private  CompactDisc compactDisc;

    @Autowired
    public CDPlayer(CompactDisc compactDisc) {
        this.compactDisc = compactDisc;
    }

    @Override
    public void play() {
        compactDisc.play();
    }
}
