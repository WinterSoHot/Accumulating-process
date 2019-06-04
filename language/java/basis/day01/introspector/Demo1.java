package base.gudongxian.day01.introspector;

import org.junit.Test;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 内省--->一个变态的反射.

 内省主要解决 的问题： 把对象的属性数据封装 到对象中。
 */
public class Demo1 {


    @Test
    public void getAllProperties() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor item :
                propertyDescriptors) {
            System.out.println(item.getReadMethod());
        }
    }


    @Test
    public void testProperties() throws IntrospectionException, InvocationTargetException, IllegalAccessException {

        //设置id 属性
        Person p = new Person();
        // 属性描述器
        PropertyDescriptor descriptor = new PropertyDescriptor("id",Person.class);
        Method method = descriptor.getWriteMethod();
        method.invoke(p,123);

        Method readMethod = descriptor.getReadMethod();
        System.out.println(readMethod.invoke(p,null));
        System.out.println(p);
    }
}
