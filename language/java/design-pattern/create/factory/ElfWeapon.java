package gu.java.pattern.create.factory;

/**
 * 小精灵武器
 * @author gudongxian
 * @create 2018-04-26 下午6:48
 **/
public class ElfWeapon implements Weapon{

    private WeaponType weaponType;

    public ElfWeapon(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    @Override
    public String toString() {
        return "ElfWeapon{" +
                "weaponType=" + weaponType +
                '}';
    }

    @Override
    public WeaponType getWeaponType() {
        return null;
    }
}
