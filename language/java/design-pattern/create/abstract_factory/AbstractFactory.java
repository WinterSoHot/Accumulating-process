package gu.java.pattern.create.abstract_factory;
/**
 * 声明一个创建抽象产品对象的操作接口
 */
public interface AbstractFactory {
    AbstractProductA createProductA();
    AbstractProductB createProductB();
}
