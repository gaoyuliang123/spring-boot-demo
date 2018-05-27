package com.example.netty.chatroom.server.invoker;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 命令执行器管理
 * @author: TGL
 * @date: 2018/5/27 21:24
 */
public class InvokerManager {

    /**
     * 命令执行器
     */
    private static final Map<Short, Map<Short, Invoker>> invokers = new HashMap<>();

    /**
     * 添加命令执行器
     * @param module
     * @param cmd
     * @param invoker
     */
    public static void addInvoker(Short module, Short cmd, Invoker invoker){
        Map<Short, Invoker> invokerMap = invokers.get(module);
        if (invokerMap == null) {
            invokerMap = new HashMap<>();
            invokers.put(module, invokerMap);
        }
        invokerMap.put(cmd, invoker);
    }

    /**
     * 获取命令执行器
     * @param module
     * @param cmd
     * @return
     */
    public static Invoker getInvoker(Short module, Short cmd) {
        Map<Short, Invoker> invokerMap = invokers.get(module);
        if (invokerMap == null) {
            Invoker invoker = invokerMap.get(cmd);
            return invoker;
        }
        return null;
    }
}
