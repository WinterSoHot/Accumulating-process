package gu.java.pattern.behavior.interpreter;

/**
 * @author gudongxian
 * @create 2018-05-03 下午8:47
 **/
public class NumberExpression extends Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    public NumberExpression(String s) {
        this.number = Integer.parseInt(s);
    }

    @Override
    public int interpret() {
        return this.number;
    }

    @Override
    public String toString() {
        return "number";
    }
}
