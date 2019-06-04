package gu.java.pattern.create.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gudongxian
 * @create 2018-04-26 下午7:34
 **/
public class App {
    private final static Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        // eagerly initialized singleton
        IvoryTower ivoryTower1 = IvoryTower.getInstance();
        IvoryTower ivoryTower2 = IvoryTower.getInstance();
        LOGGER.info("ivoryTower1={}", ivoryTower1);
        LOGGER.info("ivoryTower2={}", ivoryTower2);

        // lazily initialized singleton
        ThreadSafeLazyLoadedIvoryTower threadSafeIvoryTower1 =
                ThreadSafeLazyLoadedIvoryTower.getInstance();
        ThreadSafeLazyLoadedIvoryTower threadSafeIvoryTower2 =
                ThreadSafeLazyLoadedIvoryTower.getInstance();
        LOGGER.info("threadSafeIvoryTower1={}", threadSafeIvoryTower1);
        LOGGER.info("threadSafeIvoryTower2={}", threadSafeIvoryTower2);

        // enum singleton
        EnumIvoryTower enumIvoryTower1 = EnumIvoryTower.INSTANCE;
        EnumIvoryTower enumIvoryTower2 = EnumIvoryTower.INSTANCE;
        LOGGER.info("enumIvoryTower1={}", enumIvoryTower1);
        LOGGER.info("enumIvoryTower2={}", enumIvoryTower2);

        // double checked locking
        ThreadSafeDoubleCheckLocking dcl1 = ThreadSafeDoubleCheckLocking.getInstance();
        LOGGER.info(dcl1.toString());
        ThreadSafeDoubleCheckLocking dcl2 = ThreadSafeDoubleCheckLocking.getInstance();
        LOGGER.info(dcl2.toString());

        // initialize on demand holder idiom
        InitializingOnDemandHolderIdiom demandHolderIdiom =
                InitializingOnDemandHolderIdiom.getInstance();
        LOGGER.info(demandHolderIdiom.toString());
        InitializingOnDemandHolderIdiom demandHolderIdiom2 =
                InitializingOnDemandHolderIdiom.getInstance();
        LOGGER.info(demandHolderIdiom2.toString());
    }
}
