package gu.java.pattern.create.prototype;

/**
 * @author gudongxian
 * @create 2018-04-26 下午7:15
 **/
public class OrcBeast extends Beast {
    private String weapon;

    public OrcBeast(String weapon) {
        this.weapon = weapon;
    }

    public OrcBeast(OrcBeast elfBeast) {
        this.weapon = elfBeast.weapon;
    }

    @Override
    public Beast copy() throws CloneNotSupportedException {
        return new OrcBeast(this);
    }

    @Override
    public String toString() {
        return "OrcBeast{" +
                "weapon='" + weapon + '\'' +
                '}';
    }
}
