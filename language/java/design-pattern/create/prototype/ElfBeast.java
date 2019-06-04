package gu.java.pattern.create.prototype;

/**
 * @author gudongxian
 * @create 2018-04-26 下午7:15
 **/
public class ElfBeast extends Beast {
    private String helpType;

    public ElfBeast(String helpType) {
        this.helpType = helpType;
    }

    public ElfBeast(ElfBeast elfBeast) {
        this.helpType = elfBeast.helpType;
    }

    @Override
    public Beast copy() throws CloneNotSupportedException {
        return new ElfBeast(this);
    }

    @Override
    public String toString() {
        return "ElfBeast{" +
                "helpType='" + helpType + '\'' +
                '}';
    }
}
