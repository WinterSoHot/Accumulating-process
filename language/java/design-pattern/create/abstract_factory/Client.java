package gu.java.pattern.create.abstract_factory;

/**
 * @author gudongxian
 * @create 2018-04-24 下午8:04
 **/
public class Client {


    /**
     * 仅使用AbstractFactory 和 AbstractProduct 类声明的接口
     * @param args
     */
    public static void main(String[] args) {

        AbstractFactory factory1 = new ConcreteFactory1();
        AbstractFactory factory2 = new ConcreteFactory2();
        AbstractProductA productA1 = factory1.createProductA();
        AbstractProductA productA2 = factory2.createProductA();
        AbstractProductB productB1 = factory1.createProductB();
        AbstractProductB productB2 = factory2.createProductB();

    }
}
