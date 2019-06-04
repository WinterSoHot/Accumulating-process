package gu.java.pattern.structure.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gudongxian
 * @create 2018-04-26 下午8:44
 **/
public class FlyingEnchantment implements Enchantment {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlyingEnchantment.class);

    @Override
    public void onActivate() {
        LOGGER.info("The item begins to glow faintly.");
    }

    @Override
    public void apply() {
        LOGGER.info("The item flies and strikes the enemies finally returning to owner's hand.");
    }

    @Override
    public void onDeactivate() {
        LOGGER.info("The item's glow fades.");
    }

}
