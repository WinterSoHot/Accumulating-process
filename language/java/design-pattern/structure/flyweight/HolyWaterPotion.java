package gu.java.pattern.structure.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 圣水药剂
 * @author gudongxian
 * @create 2018-04-27 上午9:31
 **/
public class HolyWaterPotion implements Potion{

    private static final Logger LOGGER = LoggerFactory.getLogger(HolyWaterPotion.class);
    @Override
    public void drink() {
        LOGGER.info("You feel blessed. (Potion={})", System.identityHashCode(this));
    }
}
