package gu.java.pattern.structure.composite;

/**
 * @author gudongxian
 * @create 2018-04-26 下午9:16
 **/
public class Letter extends LetterComposite {
    private char c;

    public Letter(char c) {
        this.c = c;
    }

    @Override
    protected void printThisBefore() {
        System.out.print(c);
    }
}
