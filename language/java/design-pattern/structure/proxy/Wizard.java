package gu.java.pattern.structure.proxy;

/**
 * 法师
 * @author gudongxian
 * @create 2018-04-27 上午10:16
 **/
public class Wizard {

    private String name;

    public Wizard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
