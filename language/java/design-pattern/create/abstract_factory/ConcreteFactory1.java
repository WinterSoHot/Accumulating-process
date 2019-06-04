package gu.java.pattern.create.abstract_factory;

/**
 * 实现创建具体产品对象的操作
 * @author gudongxian
 * @create 2018-04-24 下午8:03
 **/
public class ConcreteFactory1 implements AbstractFactory{
    @Override
    public AbstractProductA createProductA() {

        return new ProductA1();
    }

    @Override
    public AbstractProductB createProductB() {

        return new ProductB1();
    }
}
