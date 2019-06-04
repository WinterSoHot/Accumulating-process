package gu.java.pattern.structure.flyweight;

/**
 * 当程序需要大量的对象时，Flyweight模式非常有用。它提供了通过共享对象实例来减少资源使用的方法。
 *
 * @author gudongxian
 * @create 2018-04-27 上午9:28
 **/
public class App {

    /**
     * In this example {@link AlchemistShop} has great amount of potions on its shelves. To fill the
     * shelves {@link AlchemistShop} uses {@link PotionFactory} (which represents the Flyweight in this
     * example). Internally {@link PotionFactory} holds a map of the potions and lazily creates new ones
     * when requested.
     * <p>
     * To enable safe sharing, between clients and threads, Flyweight objects must be immutable.
     * Flyweight objects are by definition value objects.
     *
     * @param args
     */
    public static void main(String[] args) {
        AlchemistShop shop = new AlchemistShop();
        shop.enumerate();
    }
}
