package gu.java.pattern.structure.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gudongxian
 * @create 2018-04-27 上午8:33
 **/
public class SimpleTroll implements Troll{

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTroll.class);

    @Override
    public void attack() {
        LOGGER.info("the troll tried to grab you!");
    }

    @Override
    public int getAttackPower() {
        return 10;
    }

    @Override
    public void feelBattle() {
        LOGGER.info("The troll shrieks in horror and runs away!");
    }
}
