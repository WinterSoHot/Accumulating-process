package gu.java.pattern.create.prototype;

/**
 * 抽象工厂
 */
public interface HeroFactory {

    Mage createMage();

    Beast createBeast();

    Warlord createWarlord();
}
