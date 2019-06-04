package gu.java.pattern.behavior.command;

/**
 * @author gudongxian
 * @create 2018-04-29 上午8:12
 **/
public class Goblin extends Target {

    public Goblin() {
        setSize(Size.NORMAL);
        setVisibility(Visibility.VISIBLE);
    }

    @Override
    public String toString() {
        return "Goblin";
    }
}
