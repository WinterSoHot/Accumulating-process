package gu.java.pattern.structure.facade;

/**
 * 外框设计模式经常被使用在一个非常复杂或很难去理解的系统中,
 * 因为系统有着大量无关的类,并且没有办法获取它的源代码.
 * 这个模式隐藏了系统复杂的部分,并且提供一个简单的接口给客户端,
 * 它通常包含一个包装类，其中包含客户端需要的一组成员。这些成员代表facade客户机访问系统，并隐藏实现细节。
 * In this example the Facade is ({@link DwarvenGoldmineFacade}) and it provides a simpler interface
 * to the goldmine subsystem.
 * @author gudongxian
 * @create 2018-04-27 上午8:52
 **/
public class App {

    public static void main(String[] args) {

        DwarvenGoldmineFacade facade = new DwarvenGoldmineFacade();
        facade.startNewDay();
        facade.digOutWine();
        facade.endDay();
    }
}
