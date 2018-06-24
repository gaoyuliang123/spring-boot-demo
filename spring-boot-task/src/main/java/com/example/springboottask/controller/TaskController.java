package com.example.springboottask.controller;

import com.example.springboottask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: TGL
 * @date: 2018/6/19 22:53
 */
@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;
    @RequestMapping("/aysnc")
    public String aysnc() {
        taskService.aysncTask();
        return "async success";
    }
    @RequestMapping("/scheduled")
    public String scheduled() {
        System.out.println("scheduled task");
        taskService.scheduledTask();
        return "scheduled success";
    }
}
