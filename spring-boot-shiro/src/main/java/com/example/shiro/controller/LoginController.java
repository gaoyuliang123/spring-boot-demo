package com.example.shiro.controller;

import com.example.shiro.entity.SysUser;
import com.example.shiro.utils.RequestUtils;
import org.apache.catalina.util.RequestUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: TGL
 * @date: 2018/6/10 22:34
 */
@Controller
@RequestMapping("/auth")
public class LoginController {
    @PostMapping("/login")
    public String submitLogin(String username, String password, HttpServletRequest request) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            SysUser sysUser = (SysUser)subject.getPrincipal();
        } catch (DisabledAccountException e) {
            request.setAttribute("msg","此用户被禁止");
            return "login";
        } catch (AuthenticationException e) {
            request.setAttribute("msg", "用户名或密码不正确");
            return "login";
        }
        return "redirect:/auth/index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/index")
    public String loginSuccessMessage(HttpServletRequest request) {
        String userName = "unknow";
        SysUser sysUser = RequestUtils.currentLoginUser();
        if (sysUser != null && sysUser.getUserName() != null) {
            userName = sysUser.getUserName();
        } else {
            return "redirect:/auth/login";
        }
        request.setAttribute("username", userName);
        return "index";
    }

    //被踢出后跳转的页面
    @RequestMapping(value = "/kickout", method = RequestMethod.GET)
    public String kickOut() {
        return "kickout";
    }
}
