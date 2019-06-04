package gu.java.pattern.structure.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gudongxian
 * @create 2018-04-27 上午8:33
 **/
public class App {


    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    /**
     * 装饰者模式是比子类更加灵活的替代方式,装饰者实现和目标一样的接口,
     *
     * @param args 用 {@link ClubbedTroll} 装饰 {@link SimpleTroll}
     */
    public static void main(String[] args) {
        Troll troll = new SimpleTroll();
        troll.attack();
        troll.feelBattle();
        LOGGER.info("Simple troll power {}", troll.getAttackPower());

        Troll clubbedTroll = new ClubbedTroll(troll);
        clubbedTroll.attack();
        clubbedTroll.feelBattle();
        LOGGER.info("Clubbed troll power {}", clubbedTroll.getAttackPower());
    }
}
