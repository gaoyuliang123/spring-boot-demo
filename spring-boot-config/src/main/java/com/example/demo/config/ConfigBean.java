package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationProperties(prefix = "my")
public class ConfigBean {

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
