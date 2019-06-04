package gu.java.pattern.structure.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 渔船
 *
 * @author gudongxian
 * @create 2018-04-26 下午8:27
 **/
public class FishingBoat {

    private static final Logger LOGGER = LoggerFactory.getLogger(FishingBoat.class);

    public void sail() {
        LOGGER.info("The fishing boat is sailing");
    }
}
