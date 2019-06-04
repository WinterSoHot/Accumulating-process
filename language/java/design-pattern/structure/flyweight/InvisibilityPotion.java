package gu.java.pattern.structure.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 隐身药剂
 * @author gudongxian
 * @create 2018-04-27 上午9:31
 **/
public class InvisibilityPotion implements Potion{

    private static final Logger LOGGER = LoggerFactory.getLogger(InvisibilityPotion.class);
    @Override
    public void drink() {
        LOGGER.info("You become invisible. (Potion={})", System.identityHashCode(this));
    }
}
