package gu.java.pattern.structure.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gudongxian
 * @create 2018-04-27 上午10:18
 **/
public class WizardTowerProxy implements WizardTower {
    private static final Logger LOGGER = LoggerFactory.getLogger(WizardTowerProxy.class);
    private static final int NUM_WIZARE_ALLOWED = 3;
    private int numWizards;
    private final WizardTower wizardTower;

    public WizardTowerProxy(WizardTower wizardTower) {
        this.wizardTower = wizardTower;
    }

    @Override
    public void enter(Wizard wizard) {
        if (numWizards < NUM_WIZARE_ALLOWED) {
            wizardTower.enter(wizard);
            numWizards++;
        } else {
            LOGGER.info("{} is not allowed to enter", wizard    );
        }
    }
}
