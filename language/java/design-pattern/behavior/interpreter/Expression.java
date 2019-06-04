package gu.java.pattern.behavior.interpreter;

/**
 * Expression
 *
 * @author gudongxian
 * @create 2018-05-03 上午9:26
 **/
public abstract class Expression {
    public abstract int interpret();

    @Override
    public abstract String toString();
}
