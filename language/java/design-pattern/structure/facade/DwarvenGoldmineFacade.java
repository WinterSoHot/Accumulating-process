package gu.java.pattern.structure.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gudongxian
 * @create 2018-04-27 上午8:56
 **/
public class DwarvenGoldmineFacade {

    private final List<DwarvenMineWorker> workers;

    public DwarvenGoldmineFacade() {
        workers = new ArrayList<>();
        workers.add(new DwarvenCartOperator());
        workers.add(new DwarvenGoldDigger());
        workers.add(new DwarvenTunnelDigger());
    }

    public void startNewDay() {
        makeAction(workers, DwarvenMineWorker.Action.WAKE_UP, DwarvenMineWorker.Action.GO_TO_MINE);
    }

    public void digOutWine() {
        makeAction(workers, DwarvenMineWorker.Action.WORK);
    }

    public void endDay() {
        makeAction(workers, DwarvenMineWorker.Action.GO_HOME, DwarvenMineWorker.Action.GO_TO_SLEEP);
    }

    private static void makeAction(Collection<DwarvenMineWorker> workers, DwarvenMineWorker.Action... actions) {
        workers.forEach(w -> w.action(actions));
    }
}
