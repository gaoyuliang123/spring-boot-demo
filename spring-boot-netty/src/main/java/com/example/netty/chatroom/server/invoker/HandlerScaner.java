package com.example.netty.chatroom.server.invoker;

import com.example.netty.chatroom.common.core.annotation.SocketCommand;
import com.example.netty.chatroom.common.core.annotation.SocketModule;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: TGL
 * @date: 2018/5/27 21:41
 */
public class HandlerScaner implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<? extends Object> clazz = bean.getClass();
        // 扫描类的所有接口父类
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces != null && interfaces.length > 0) {
            for (Class<?> interFace : interfaces) {
                SocketModule socketModule = interFace.getAnnotation(SocketModule.class);
                if (socketModule == null) {
                    continue;
                }
                Method[] methods = interFace.getMethods();
                if (methods != null && methods.length > 0) {
                    for (Method method : methods) {
                        // 判断是否为handler接口类
                        SocketCommand socketCommand = method.getAnnotation(SocketCommand.class);
                        if (socketCommand == null) {
                            continue;
                        }
                        Short module = socketModule.module();
                        Short cmd = socketCommand.cmd();
                        Invoker invoker = InvokerManager.getInvoker(module, cmd);
                        if (invoker != null) {
                            System.out.println("重复命令:"+"module:"+module +" "+"cmd：" + cmd);
                        } else {
                            InvokerManager.addInvoker(module,cmd, new Invoker(method, bean));
                        }
                    }
                }

            }
        }
        return bean;
    }
}
