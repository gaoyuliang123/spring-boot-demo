package com.example.websocket.service;

import com.example.websocket.model.InMessage;
import com.example.websocket.model.OutMessage;
import com.example.websocket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Component
public class WebScoketService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private WeatherService weatherService;

    public void sendTopicMessage(String destination, InMessage message) {
        for (int i = 0; i < 10; i++) {
            simpMessagingTemplate.convertAndSend(destination, new OutMessage(message.getContent() + i));
        }

    }

    public void sendChatMessage(String destination, InMessage message) {
        simpMessagingTemplate.convertAndSend(destination + message.getTo(), new OutMessage(message.getFrom()
         + " send:" + message.getContent()));
    }

    public void sendServerInfo() {
        int processors = Runtime.getRuntime().availableProcessors();
        long maxMemory = Runtime.getRuntime().maxMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        String content = String.format("服务器可用处理器:%s,最大内存:%s,可用内存:%s", processors, maxMemory, freeMemory);
        simpMessagingTemplate.convertAndSend("/topic/server_info", new OutMessage(content));
    }

    public void sendWeatherInfo() {
        Map<String, String> weatherMap = weatherService.getWeatherInfo("深圳");
        if (!CollectionUtils.isEmpty(weatherMap)) {
            String msgItem = "城市:%s, 日期:%s, 天气:%s, 温度:%s.";
            simpMessagingTemplate.convertAndSend("/topic/weather_info", new OutMessage(
                    String.format(msgItem, weatherMap.get("city"), weatherMap.get("date"), weatherMap.get("weather"),
                            weatherMap.get("temp"))
            ));
        }
    }

    public void sendOnlineUser(Map<String, User> map) {
        String msg = "";
        for(Map.Entry<String, User> entry : map.entrySet()){
            msg = msg.concat(entry.getValue().getUserName() + " || ");
        }
        simpMessagingTemplate.convertAndSend("/topic/onlineuser",msg);
    }
}
