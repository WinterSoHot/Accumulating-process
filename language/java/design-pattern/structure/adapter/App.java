package gu.java.pattern.structure.adapter;

/**
 * @author gudongxian
 * @create 2018-04-26 下午8:30
 **/
public class App {

    public static void main(String[] args) {
        Captain captain = new Captain(new FishBoatAdapter());
        captain.row();
    }
}
