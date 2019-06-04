package gu.java.pattern.structure.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gudongxian
 * @create 2018-04-26 下午8:50
 **/
public class Sword implements Weapon {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sword.class);

    private Enchantment enchantment;

    public Sword(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    @Override
    public void wield() {
        LOGGER.info("the sword is wielded");
        enchantment.onActivate();
    }

    @Override
    public void swing() {
        LOGGER.info("the sword is swinged");
        enchantment.apply();
    }

    @Override
    public void unWield() {
        LOGGER.info("the sword is unwielded");
        enchantment.onDeactivate();
    }

    @Override
    public Enchantment getEnchantment() {
        return enchantment;
    }
}
