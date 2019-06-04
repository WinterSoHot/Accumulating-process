package gu.java.pattern.create.factory;

/**
 * 兽人铁匠
 *
 * @author gudongxian
 * @create 2018-04-26 下午6:51
 **/
public class OrcBlacksmith implements Blacksmith {
    @Override
    public Weapon manufactureWeapon(WeaponType weaponType) {
        return new OrcWeapon(weaponType);
    }
}
