package com.example.netty.chatroom.server.invoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description: 命令执行器
 * @author: TGL
 * @date: 2018/5/27 21:18
 */
public class Invoker {

    /**
     * 方法
     */
    private Method method;

    /**
     * 目标
     */
    private Object target;

    public Invoker(Method method, Object target) {
        this.method = method;
        this.target = target;
    }

    public Object invoke(Object... params) {
        try {
            return method.invoke(target, params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
