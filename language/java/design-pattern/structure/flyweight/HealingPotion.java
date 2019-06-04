package gu.java.pattern.structure.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 治疗药剂
 * @author gudongxian
 * @create 2018-04-27 上午9:31
 **/
public class HealingPotion implements Potion{

    private static final Logger LOGGER = LoggerFactory.getLogger(HealingPotion.class);
    @Override
    public void drink() {
        LOGGER.info("You feel healed. (Potion={})", System.identityHashCode(this));
    }
}
