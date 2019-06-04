package gu.java.pattern.create.prototype;

/**
 * @author gudongxian
 * @create 2018-04-26 下午7:13
 **/
public class OrcMage extends Mage {

    private String weapon;

    public OrcMage(String weapon) {
        this.weapon = weapon;
    }

    public OrcMage(OrcMage mage) {
        this.weapon = mage.weapon;
    }

    @Override
    public Mage copy() throws CloneNotSupportedException {
        return new OrcMage(this);
    }

    @Override
    public String toString() {
        return "OrcMage{" +
                "weapon='" + weapon + '\'' +
                '}';
    }
}
