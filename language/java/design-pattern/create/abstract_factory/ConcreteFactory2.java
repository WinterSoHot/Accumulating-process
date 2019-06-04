package gu.java.pattern.create.abstract_factory;

/**
 * @author gudongxian
 * @create 2018-04-24 下午8:03
 **/
public class ConcreteFactory2 implements AbstractFactory{
    @Override
    public AbstractProductA createProductA() {

        return new ProductA2();
    }

    @Override
    public AbstractProductB createProductB() {

        return new ProductB2();
    }
}
