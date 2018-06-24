package com.example.springbootservlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @description:
 * @author: TGL
 * @date: 2018/6/24 22:51
 */
public class CustomListenner implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("CustomListenner contextDestroyed······");
    }
}
