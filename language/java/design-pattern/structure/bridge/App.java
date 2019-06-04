package gu.java.pattern.structure.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gudongxian
 * @create 2018-04-26 下午8:45
 **/
public class App {

    /**
     *组合在继承。桥的模式也可以被认为是两层抽象。通过使用Bridge，您可以将抽象与实现分离，从而使两者能够独立地变化。
     * <p>
     *在桥式模式中，抽象({@link Weapon})和实现({@link Enchantment})有自己的类层次结构。实现的接口。
     *可以在不影响客户的情况下进行更改。
     * <p>
     *在本例中，我们有两个类层次结构。一种武器和另一种魔法。我们可以很容易的
     *将任何武器与任何魔法结合使用，而不是创造深层的等级结构。
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOGGER.info("The knight receives an enchanted sword.");
        Sword enchantedSword = new Sword(new SoulEatingEnchantment());
        enchantedSword.wield();
        enchantedSword.swing();
        enchantedSword.unWield();

        LOGGER.info("The valkyrie receives an enchanted hammer.");
        Hammer hammer = new Hammer(new FlyingEnchantment());
        hammer.wield();
        hammer.swing();
        hammer.unWield();
    }
}
