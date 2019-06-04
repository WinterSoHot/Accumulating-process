package gu.java.pattern.create.prototype;

/**
 * @author gudongxian
 * @create 2018-04-26 下午7:13
 **/
public class ElfMage extends Mage {

    private String helpType;

    public ElfMage(String helpType) {
        this.helpType = helpType;
    }

    public ElfMage(ElfMage mage) {
        this.helpType = mage.helpType;
    }

    @Override
    public Mage copy() throws CloneNotSupportedException {
        return new ElfMage(this);
    }

    @Override
    public String toString() {
        return "ElfMage{" +
                "helpType='" + helpType + '\'' +
                '}';
    }
}
