package base.gudongxian.day01.thread;

/**
 * 线程
 * <p>
 * 多线程的好处：在一个进程可以执行多个任务
 * <p>
 * 自定义线程的创建方式：
 *
 * 方法一
 * 1. 自定义一个类继承线程
 * 2. 重写run()方法
 * 3. 创建自定义线程，执行start 方法
 *
 * 方法二
 * 1.自定义类实现Runnable 接口
 * 2. 在run() 写入自己的代码
 * 3. 创建Runnable 对象
 * 4. 创建Thread 对象 将Runnable 作为参数传进去
 * 5. 执行start 方法
 *
 * 线程安全的解决方案
 *
 *线程安全的根本原因：
 * 1. 多个线程共享一个资源
 * 2. 操作共享资源的代码至少两行
 * 1.同步代码块
 *
 * synchronized (锁)｛
 *      代码
 * ｝
 *
 *
 * 2. 同步函数
 * 修饰符 synchronized 函数名 (参数)｛｝
 *
 *注意：
 * 1. 同步代码块的锁可以是任意对象，同步函数 非静态函数的锁是this 对象，静态函数的锁是class对象
 * 2. 锁的对象必须是多线程共享的对象，否则锁不住
 * 3. 在同步代码块和同步函数中调用sleep 函数时不会释放锁的，调用wait 方法会释放锁
 */
public class Demo1 extends Thread {


    public Demo1(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1("mm");
        demo1.start();
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }

    }
}
