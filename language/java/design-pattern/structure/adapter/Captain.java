package gu.java.pattern.structure.adapter;

/**
 * 指挥
 *
 * @author gudongxian
 * @create 2018-04-26 下午8:29
 **/
public class Captain {

    private RowingBoat rowingBoat;

    public Captain(RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }

    /**
     * 指挥船划动
     */
    public void row() {
        rowingBoat.row();
    }
}
