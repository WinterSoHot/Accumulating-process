package gu.java.pattern.structure.bridge;

/**
 * 魔法
 *
 * @author gudongxian
 * @create 2018-04-26 下午8:40
 **/
public interface Enchantment {
    void onActivate();

    void apply();

    void onDeactivate();
}
