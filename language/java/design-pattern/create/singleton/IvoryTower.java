package gu.java.pattern.create.singleton;

/**
 * 象牙塔
 *
 * @author gudongxian
 * @create 2018-04-26 下午7:35
 **/
public final class IvoryTower {

    private final static  IvoryTower INSTANCE = new IvoryTower();

    private IvoryTower() {

    }

    public static IvoryTower getInstance() {
        return INSTANCE;
    }
}
