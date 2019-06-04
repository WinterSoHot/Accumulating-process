package gu.java.pattern.create.builder;

/**
 *
 * 头发颜色
 * @author gudongxian
 * @create 2018-04-26 下午5:54
 **/
public enum  HairColor {
    RED,BLACK,GREEN,BLUE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
