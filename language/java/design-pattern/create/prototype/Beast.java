package gu.java.pattern.create.prototype;

public abstract class Beast extends ProtoType {
    public abstract Beast copy() throws CloneNotSupportedException;
}
