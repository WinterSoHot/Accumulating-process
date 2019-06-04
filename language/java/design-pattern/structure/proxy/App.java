package gu.java.pattern.structure.proxy;

/**
 * @author gudongxian
 * @create 2018-04-27 上午10:10
 **/
public class App {

    /**
     * 代理对象和代理实现同一个接口.
     * 代理对象作为代理的组成,在进行操作的时候,检查代理中的条件
     *
     * @param args
     */
    public static void main(String[] args) {
        WizardTowerProxy wizardTowerProxy = new WizardTowerProxy(new IvoryTower());
        wizardTowerProxy.enter(new Wizard("gu"));
        wizardTowerProxy.enter(new Wizard("yummy"));
        wizardTowerProxy.enter(new Wizard("lily"));
        wizardTowerProxy.enter(new Wizard("mike"));
    }
}
