package com.example.springwebsocket.controller;

import com.example.springwebsocket.handler.SpringWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: TGL
 * @date: 2018/6/16 23:06
 */
@Controller
public class WebsocketController {
    @Autowired
    private SpringWebSocketHandler socketHandler;

    @RequestMapping("/websocket")
    public ModelAndView index() {
        return new ModelAndView("login");
    }

    @RequestMapping("/websocket/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        System.out.println(username+"登录");
        HttpSession session = request.getSession(false);
        session.setAttribute("SESSION_USERNAME", username);
        //response.sendRedirect("/WEB-INF/jsp/websocket.jsp");
        return new ModelAndView("websocket");
    }

    @RequestMapping("/websocket/send")
    @ResponseBody
    public String send(HttpServletRequest request) {
        String userId = request.getSession().getId();
        String userName = request.getParameter("username");
        socketHandler.sendMessageToUser(userId, new TextMessage("你好 这是在测试"));
        return null;
    }
}
