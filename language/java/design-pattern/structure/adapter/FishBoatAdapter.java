package gu.java.pattern.structure.adapter;

/**
 * @author gudongxian
 * @create 2018-04-26 下午8:28
 **/
public class FishBoatAdapter implements RowingBoat {

    private FishingBoat fishingBoat;

    public FishBoatAdapter() {
        this.fishingBoat = new FishingBoat();
    }

    @Override
    public void row() {
        fishingBoat.sail();
    }
}
