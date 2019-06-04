package gu.java.pattern.create.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gudongxian
 * @create 2018-04-26 下午6:51
 **/
public class App {

    private final static Logger LOGGER = LoggerFactory.getLogger(App.class);

    private Blacksmith blacksmith;

    public App(Blacksmith blacksmith) {
        this.blacksmith = blacksmith;
    }

    public void manufactureWeapon() {
        Weapon weapon;
        weapon = blacksmith.manufactureWeapon(WeaponType.SPEAR);
        LOGGER.info(weapon.toString());

        weapon = blacksmith.manufactureWeapon(WeaponType.AXE);
        LOGGER.info(weapon.toString());

    }

    public static void main(String[] args) {
        App app = new App(new ElfBlacksmith());
        app.manufactureWeapon();

        app = new App(new OrcBlacksmith());
        app.manufactureWeapon();
    }
}
