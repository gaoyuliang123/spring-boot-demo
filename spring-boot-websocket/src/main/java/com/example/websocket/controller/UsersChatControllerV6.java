package com.example.websocket.controller;

import com.example.websocket.model.InMessage;
import com.example.websocket.model.User;
import com.example.websocket.service.WebScoketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@EnableScheduling
public class UsersChatControllerV6 {

    @Autowired
    private WebScoketService webScoketService;

    public static Map<String, String>  userMap = new HashMap<>();
    static {
        userMap.put("zhangsan", "123");
        userMap.put("lisi", "456");
        userMap.put("wangwu", "789");
    }

    public static Map<String, User>  onlineUsers = new HashMap<>();
    static {
        onlineUsers.put("sessionId", new User("admin", "admin"));
    }

    // http://localhost:8080/v6/index.html
    @PostMapping(value = "login")
    public String login(@RequestParam(value = "userName", required = true) String userName, @RequestParam(value = "password", required = true) String password, HttpSession session) {
        String pwd = userMap.get(userName);
        if (password.equals(pwd)) {
            String sessionId = session.getId();
            onlineUsers.put(sessionId, new User(userName, password));
            return "redirect:/v6/chat.html";
        } else {
            return "redirect:/v6/error.html";
        }
    }

    @Scheduled(fixedRate = 3000)
    @ResponseBody
    public String sendOnlineUser() {
        webScoketService.sendOnlineUser(onlineUsers);
        return "success";
    }

    @MessageMapping("/app/v6/chat")
    public void topicChat(InMessage message, SimpMessageHeaderAccessor headerAccessor){
        String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
        User user = onlineUsers.get(sessionId);
        message.setFrom(user.getUserName());
        webScoketService.sendTopicChat(message);

    }
}
