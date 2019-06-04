package com.springboot.aync.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * 异步任务
 *
 * @author gudongxian
 * @create 2017-11-22 下午3:12
 **/
@Component
public class AsyncTask {

    public static Random random = new Random();

    @Async
    public Future<String> doTaskOne() throws Exception {
        System.out.println("do Task one");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("completing task one spends " + (end - start) + " ms");
        return new AsyncResult<>("task one completed");
    }

    @Async
    public Future<String> doTaskTwo() throws Exception {
        System.out.println("do Task Two");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("completing task Two spends " + (end - start) + " ms");
        return new AsyncResult<>("task Two completed");
    }


    @Async
    public Future<String> doTaskThree() throws Exception {
        System.out.println("do Task Three");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("completing task Three spends " + (end - start) + " ms");
        return new AsyncResult<>("task Three completed");
    }


}
