package gu.java.pattern.create.factory;

/**
 * 小精灵铁匠
 * @author gudongxian
 * @create 2018-04-26 下午6:50
 **/
public class ElfBlacksmith implements Blacksmith {
    @Override
    public Weapon manufactureWeapon(WeaponType weaponType) {
        return new ElfWeapon(weaponType);
    }
}
