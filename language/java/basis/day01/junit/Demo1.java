package base.gudongxian.day01.junit;

import org.junit.Test;

import java.util.Arrays;

/*
junit(单元测试框架)

目前存在的问题：
	1. 目前的方法如果需要测试，都需要在main方法上调用。
	2. 目前的结果都需要我们人工对比。


junit要注意的细节：
	1. 如果使用junit测试一个方法的时候，在junit窗口上显示绿条那么代表测试正确，
		如果是出现了红条，则代表该方法测试出现了异常不通过。
	2. 如果点击方法名、 类名、包名、 工程名运行junit分别测试的是对应的方法，类、 包中 的所有类的test方法，工程中的所有test方法。
	3.  @Test测试的方法不能是static修饰与不能带有形参。
	4. 如果测试一个方法的时候需要准备测试的环境或者是清理测试的环境，那么可以@Before、 @After 、@BeforeClass、 @AfterClass这四个注解。
	@Before、 @After 是在每个测试方法测试的时候都会调用一次， @BeforeClass、 @AfterClass是在所有的测试方法测试之前与测试之后调用一次而已。

junit使用规范：
	1. 一个类如果需要测试，那么该类就应该对应着一个测试类，测试类的命名规范 ： 被测试类的类名+ Test.
	2. 一个被测试的方法一般对应着一个测试的方法，测试的方法的命名规范是： test+ 被测试的方法的方法名

 */
public class Demo1 {

    @Test
    public void getMax() {
        int a = 1;
        int b = 3;
        int max = a > b ? a : b;
        System.out.println("最大值：" + max);
    }

    @Test
    public void sort() {
        int[] arr = new int[]{4, 1, 12, 10};

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println("数组元素是：" + Arrays.toString(arr));
    }

}
