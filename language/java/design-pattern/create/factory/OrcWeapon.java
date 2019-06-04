package gu.java.pattern.create.factory;

/**
 * 兽人武器
 * @author gudongxian
 * @create 2018-04-26 下午6:48
 **/
public class OrcWeapon implements Weapon{

    private WeaponType weaponType;

    public OrcWeapon(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    @Override
    public String toString() {
        return "OrcWeapon{" +
                "weaponType=" + weaponType +
                '}';
    }

    @Override
    public WeaponType getWeaponType() {
        return null;
    }
}
