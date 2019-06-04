package gu.java.pattern.structure.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gudongxian
 * @create 2018-04-27 上午10:17
 **/
public class IvoryTower implements WizardTower{
    private static final Logger LOGGER = LoggerFactory.getLogger(IvoryTower.class);
    @Override
    public void enter(Wizard wizard) {
        LOGGER.info("{} enters the tower.",wizard);
    }
}
