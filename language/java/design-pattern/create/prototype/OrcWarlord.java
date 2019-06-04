package gu.java.pattern.create.prototype;

/**
 * @author gudongxian
 * @create 2018-04-26 下午7:16
 **/
public class OrcWarlord extends Warlord{
    private String weapon;

    public OrcWarlord(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "OrcWarlord{" +
                "weapon='" + weapon + '\'' +
                '}';
    }

    public OrcWarlord(OrcWarlord elfWarlord) {
        this.weapon = elfWarlord.weapon;
    }

    @Override
    public Warlord copy() throws CloneNotSupportedException {
        return new OrcWarlord(this);
    }
}
