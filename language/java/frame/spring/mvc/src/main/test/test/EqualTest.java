package test;

/**
 * Created by gudongxian on 2017/3/24.
 */
public class EqualTest {

    public static void main(String[] args) {
        Car car = new Car();
        car.setId(100);

        Car car1 = new Car();
        car1.setId(100);
        System.out.println(car.equals(car1));
    }
}
