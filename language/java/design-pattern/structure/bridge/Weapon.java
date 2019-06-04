package gu.java.pattern.structure.bridge;

public interface Weapon {
    //使用
    void wield();

    //挥舞
    void swing();

    //卸下
    void unWield();

    Enchantment getEnchantment();
}
