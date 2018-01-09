package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "my")
@PropertySource("classpath:config/testconfigbean.properties")
public class ConfigBeanTest {

    private String name;
    private String worlds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorlds() {
        return worlds;
    }

    public void setWorlds(String worlds) {
        this.worlds = worlds;
    }
}
