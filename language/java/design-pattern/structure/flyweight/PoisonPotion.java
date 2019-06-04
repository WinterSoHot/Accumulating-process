package gu.java.pattern.structure.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 毒药
 * @author gudongxian
 * @create 2018-04-27 上午9:31
 **/
public class PoisonPotion implements Potion{

    private static final Logger LOGGER = LoggerFactory.getLogger(PoisonPotion.class);
    @Override
    public void drink() {
        LOGGER.info("Urgh! This is poisonous. (Potion={})", System.identityHashCode(this));
    }
}
