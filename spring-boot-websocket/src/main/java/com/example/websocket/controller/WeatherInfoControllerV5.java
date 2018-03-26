package com.example.websocket.controller;

import com.example.websocket.service.WebScoketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
@EnableScheduling
public class WeatherInfoControllerV5 {
    @Autowired
    private WebScoketService webScoketService;

    // http://localhost:8080/v5/index.html
    // @Scheduled(fixedRate = 5000)
    public String weatherInfo() {
        webScoketService.sendWeatherInfo();
        return "success";
    }
}
