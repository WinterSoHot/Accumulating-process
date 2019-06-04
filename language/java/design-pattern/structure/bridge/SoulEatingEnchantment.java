package gu.java.pattern.structure.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gudongxian
 * @create 2018-04-26 下午8:53
 **/
public class SoulEatingEnchantment implements Enchantment {

    private static final Logger LOGGER = LoggerFactory.getLogger(SoulEatingEnchantment.class);

    @Override
    public void onActivate() {
        LOGGER.info("The item spreads bloodlust.");
    }

    @Override
    public void apply() {
        LOGGER.info("The item eats the soul of enemies.");
    }

    @Override
    public void onDeactivate() {
        LOGGER.info("Bloodlust slowly disappears.");
    }
}
