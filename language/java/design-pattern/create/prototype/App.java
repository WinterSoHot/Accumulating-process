package gu.java.pattern.create.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gudongxian
 * @create 2018-04-26 下午7:22
 **/
public class App {

    private final static Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        HeroFactory factory;
        Mage mage;
        Beast beast;
        Warlord warlord;
        factory = new HeroFactoryImpl(new ElfMage("cooking"), new ElfWarlord("elfWarlord"), new ElfBeast("beast"));

        mage = factory.createMage();
        beast = factory.createBeast();
        warlord = factory.createWarlord();

        LOGGER.info(mage.toString());
        LOGGER.info(beast.toString());
        LOGGER.info(warlord.toString());

        factory = new HeroFactoryImpl(new OrcMage("axe"), new OrcWarlord("ss"), new OrcBeast("xx"));


        mage = factory.createMage();
        beast = factory.createBeast();
        warlord = factory.createWarlord();

        LOGGER.info(mage.toString());
        LOGGER.info(beast.toString());
        LOGGER.info(warlord.toString());
    }
}
