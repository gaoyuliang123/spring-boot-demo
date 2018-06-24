package com.example.springboottask.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: TGL
 * @date: 2018/6/19 22:56
 */
@Service
public class TaskService {
    @Async
    public String aysncTask() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "aysncTask";
    }

    @Scheduled(cron = "0/3 * * * * ?")
    public void scheduledTask() {
        System.out.println("欢迎光临！");
    }
}
