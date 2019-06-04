package com.spring.ioc.Part3iocmore;

import com.spring.ioc.Part3iocmore.bean.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by gudongxian on 2017/3/16.
 */
public class PropertiesTest {

    public static void main(String[] args) {
        //ApplicationContext cxt = new AnnotationConfigApplicationContext(PropertiesDemo.class);
        ApplicationContext cxt = new AnnotationConfigApplicationContext(PropertiesDemo2.class);
        // ApplicationContext cxt = new ClassPathXmlApplicationContext("com/spring/ioc/Part3iocmore/PropertiesConfig.xml");
        //User user = (User) cxt.getBean("account");
        Account bean = cxt.getBean(Account.class);
        System.out.println(bean.getName() + "\t" + bean.getSex());
        //System.out.println(user.getName() + "\t" + user.getSex());
//        System.setProperty("spring.profiles.active", "development");
    }
}
