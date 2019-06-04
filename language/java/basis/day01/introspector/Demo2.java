package base.gudongxian.day01.introspector;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gudongxian on 2017/6/19.
 * BeanUtils 主要解决问题：把对象的属性数据放到对象里面
 * <p>
 * BeanUtils的好处：
 * 1. BeanUtils设置属性值的时候，如果属性是基本数据 类型，BeanUtils会自动帮我转换数据类型。
 * 2. BeanUtils设置属性值的时候底层也是依赖于get或者Set方法设置以及获取属性值的。
 * 3. BeanUtils设置属性值,如果设置的属性是其他的引用 类型数据，那么这时候必须要注册一个类型转换器。
 */
public class Demo2 {


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        String id = "110";
        String name = "gg";
        String salary = "3000";
        String birthday = "2013-12-10";

        ConvertUtils.register(new Converter() {
            public Object convert(Class aClass, Object o) {
                Date date = null;
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    date = simpleDateFormat.parse((String) o);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date;
            }
        }, Date.class);

        Emp emp = new Emp();

        BeanUtils.setProperty(emp,"id",id);
        BeanUtils.setProperty(emp,"name",name);
        BeanUtils.setProperty(emp,"salary",salary);
        BeanUtils.setProperty(emp,"birthday",birthday);

        System.out.println(emp);

    }
}
