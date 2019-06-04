package com.springboot.schedule.task;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 定时任务
 */
@Component
public class ScheduleTask {

    private static final SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 3000L)
    public void repeatCurrentTime() {
        System.out.println("Current Time is " + sf.format(new Date()));
    }
}
