package gu.java.pattern.create.prototype;

/**
 * @author gudongxian
 * @create 2018-04-26 下午7:09
 **/
public abstract class ProtoType implements Cloneable {
    public abstract Object copy() throws CloneNotSupportedException;
}
