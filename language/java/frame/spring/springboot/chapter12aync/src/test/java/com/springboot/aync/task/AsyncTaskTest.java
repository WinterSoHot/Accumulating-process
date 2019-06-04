package com.springboot.aync.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncTaskTest {

    @Autowired
    AsyncTask asyncTask;

    @Test
    public void test() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> doTaskOne = asyncTask.doTaskOne();
        Future<String> doTaskTwo = asyncTask.doTaskTwo();
        Future<String> doTaskThree = asyncTask.doTaskThree();


        while (true) {
            if (doTaskOne.isDone() && doTaskTwo.isDone() && doTaskThree.isDone()) {
                System.out.println(doTaskOne.get());
                System.out.println(doTaskTwo.get());
                System.out.println(doTaskThree.get());
                break;
            }

            Thread.sleep(1000L);
        }

        long end = System.currentTimeMillis();
        System.out.println("Tasks Completed that spends " + (end - start) + "ms");
    }
}