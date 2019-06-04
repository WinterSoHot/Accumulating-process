package base.gudongxian.day01.introspector;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ResourceBundle;


/**
 * Created by gudongxian on 2017/6/16.
 */
public class BeanInStance {

    public static void main(String[] args) throws Exception {
        Person person = (Person) getInstance();
        System.out.println(person);
    }

    public static Object getInstance() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Person.class.getResourceAsStream("/config.txt"), "utf-8"));
        String className = bufferedReader.readLine();
        Class aClass = Class.forName(className);
        Constructor constructor = aClass.getConstructor(null);
        Object o = constructor.newInstance(null);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] split = line.split("=");
            Field field = aClass.getDeclaredField(split[0]);
            if (field.getType() == int.class)
                field.set(o, Integer.parseInt(split[1]));
            else
                field.set(o, split[1]);
        }
        return o;
    }
}
