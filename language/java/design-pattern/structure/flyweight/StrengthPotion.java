package gu.java.pattern.structure.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 强壮药剂
 * @author gudongxian
 * @create 2018-04-27 上午9:31
 **/
public class StrengthPotion implements Potion{

    private static final Logger LOGGER = LoggerFactory.getLogger(StrengthPotion.class);
    @Override
    public void drink() {
        LOGGER.info("You feel strong. (Potion={})", System.identityHashCode(this));
    }
}
