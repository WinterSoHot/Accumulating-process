package gu.java.pattern.create.prototype;

/**
 * @author gudongxian
 * @create 2018-04-26 下午7:16
 **/
public class ElfWarlord  extends Warlord{
    private String helpType;

    public ElfWarlord(String helpType) {
        this.helpType = helpType;
    }

    @Override
    public String toString() {
        return "ElfWarlord{" +
                "helpType='" + helpType + '\'' +
                '}';
    }

    public ElfWarlord(ElfWarlord elfWarlord) {
        this.helpType = elfWarlord.helpType;
    }

    @Override
    public Warlord copy() throws CloneNotSupportedException {
        return new ElfWarlord(this);
    }

}
