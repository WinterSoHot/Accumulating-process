package gu.java.pattern.create.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gudongxian
 * @create 2018-04-26 下午6:02
 **/
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        Hero gug = new Hero.Builder("gug", Profession.FASHI).withHairColor(HairColor.GREEN)
                .withHairType(Hair.SHORT_HAIR).withWeapon(Weapon.SHORT_WORD).build();

        LOGGER.info("Hero:{}",gug);
    }
}
