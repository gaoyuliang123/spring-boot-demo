package com.example.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.example.shiro.entity.SysUser;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @description: 限制并发人数登陆
 * @author: TGL
 * @date: 2018/6/9 22:15
 */
@Data
@Slf4j
public class KickoutSessionControlFilter extends AccessControlFilter {
    /**
     * 踢出会话后的url
     */
    private String kickoutUrl;
    /**
     * 踢出之前(false)或之后（true）登陆的
     */
    private boolean kickoutAfter = false;
    /**
     * 最大会话数
     */
    private int maxSession = 1;

    private SessionManager sessionManager;

    private Cache<String, Deque<Serializable>> cache;

    /**
     * 设置Cache的key的前缀
     * @param cacheManager
     */
    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro_redis_cache");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = super.getSubject(request, response);
        // 如果没有登陆，直接进行后面的流程
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            return true;
        }
        Session session = subject.getSession();
        Serializable sessionId = session.getId();
        SysUser sysUser = (SysUser)subject.getPrincipal();
        String userName = sysUser.getUserName();
        // 读取缓存，没有就存入
        Deque<Serializable>  deque = cache.get(userName);
        if (deque == null) {
            deque = new LinkedList<Serializable>();
        }
        // 如果队列没有sessionId且用户没有踢出则放入队列
        if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
            deque.push(sessionId);
            cache.put(userName, deque);
        }
        // 如果队列中的sessionId数大于最大会话数则踢人
        while (deque.size() > maxSession) {
            // 被踢出的会话
            Serializable kickoutSessionId = null;
            if (kickoutAfter) {
                // 踢出后登陆的
                kickoutSessionId = deque.removeFirst();
                cache.put(userName, deque);
            } else {
                // 踢出前登陆的
                kickoutSessionId = deque.removeLast();
                cache.put(userName, deque);
            }
            // 获取被踢出sessionId的Session对象
            Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
            if (kickoutSession != null) {
                kickoutSession.setAttribute("kickout", true);
            }
        }

        // 如果被踢出了直接退出 重定向踢出后的地址
        if (session.getAttribute("kickout") != null) {
            // 退出登陆
            subject.logout();
            super.saveRequest(request);
            Map<String, String> resultMap = new HashMap<>();
            // 判断是否ajax请求
            if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest)request).getHeader("X-Requested-With"))) {
                resultMap.put("user_status", "300");
                resultMap.put("message", "您已经在其他地方登录，请重新登录！");
                out(response, resultMap);
            } else {
                // 重定向
                WebUtils.issueRedirect(request, response, kickoutUrl);
            }
        }

        return false;
    }

    private void out(ServletResponse response, Map<String, String> resultMap)
            throws IOException {
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(JSON.toJSONString(resultMap));
            out.flush();
            out.close();
        } catch (Exception e) {
            System.err.println("KickoutSessionFilter.class 输出JSON异常，可以忽略。");
        }
    }
}
