package com.example.demo.controller;

import com.example.demo.config.ConfigBean;
import com.example.demo.config.ConfigBeanTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    /**
     * 自定义属性:在要使用的地方通过注解@Value(value="${config.name}")就可以绑定到你想要的属性上面
     */
    @Value(value = "${my.name}")
    private String name;
    @Value(value = "${my.worlds}")
    private String worlds;
    @Value("${my.say}")
    private String say;

    /**
     * 参数绑定到一个对象的bean
     */
    @Autowired
    private ConfigBean configBean;
    @Autowired
    private ConfigBeanTest configBeanTest;

    @Value(value = "${my.uuid}")
    private String uuid;

    @Value(value = "${my.application.env}")
    private String profilesEnv;

    @RequestMapping("/")
    public String saySomething() {
//        return name + " say: " + worlds;
//        return configBean.getName() + " say: " + configBean.getWorlds();
        return configBeanTest.getName() + " say: " + configBeanTest.getWorlds();
//        return say;
//        return profilesEnv;
    }

    @RequestMapping("/random")
    public Map<String, Object> random() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("uuid", uuid);
        return resultMap;
    }
}
