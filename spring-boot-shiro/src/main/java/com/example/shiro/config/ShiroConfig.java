package com.example.shiro.config;

import com.example.shiro.filter.KickoutSessionControlFilter;
import com.example.shiro.realm.CustomShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;



/**
 * @description:
 * @author: TGL
 * @date: 2018/6/9 21:26
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private Environment evn;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 没有登录的用户只能访问登录页面
        shiroFilterFactoryBean.setLoginUrl("/auth/login");
        // 登录成功后要跳转到页面
        shiroFilterFactoryBean.setSuccessUrl("/auth/index");
        // 未授权要跳转到页面（配置是否生效待定）
        shiroFilterFactoryBean.setUnauthorizedUrl("/auth/403");
        // 自定义拦截器
        Map<String, Filter> filterMap = new LinkedHashMap<String, Filter>(16);
        // TODO
        filterMap.put("kickout", null);
        shiroFilterFactoryBean.setFilters(filterMap);
        // 权限控制
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>(16);
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/auth/login", "anon");
        filterChainDefinitionMap.put("/auth/logout", "logout");
        filterChainDefinitionMap.put("/auth/kickout", "anon");
        filterChainDefinitionMap.put("/auth/**", "authc,kickout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("customShiroRealm") CustomShiroRealm customShiroRealm,
                                           @Qualifier("redisCacheManager") RedisCacheManager redisCacheManager,
                                           @Qualifier("sessionManager") SessionManager sessionManager) {
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealm(customShiroRealm);
        webSecurityManager.setCacheManager(redisCacheManager);
        webSecurityManager.setSessionManager(sessionManager);
        return webSecurityManager;
    }

    /**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     *
     * @return
     */
    @Bean("customShiroRealm")
    public CustomShiroRealm customShiroRealm() {
        return new CustomShiroRealm();
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(evn.getProperty("spring.redis.host", "localhost"));
        redisManager.setPort(Integer.parseInt(evn.getProperty("spring.redis.port", "6379")));
        redisManager.setTimeout(Integer.parseInt(evn.getProperty("spring.redis.timeout", "0")));
        // 失效时间
        redisManager.setExpire(1800);
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    @Bean("redisCacheManager")
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(this.redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO 是shiro seesionDAO层的实现，通过redis。
     * 使用的是shiro-redis开源插件
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(this.redisManager());
        return redisSessionDAO;
    }

    /**
     * Session Manager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean("sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(this.redisSessionDAO());
        return sessionManager;
    }

    /**
     * 限制同一账号登录同时登录人数控制
     *
     * @return
     */
    @Bean
    public KickoutSessionControlFilter kickoutSessionControlFilter() {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        return kickoutSessionControlFilter;
    }

    /***
     * 授权所用配置
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /***
     * 使授权注解起作用不如不想配置可以在pom文件中加入
     * <dependency>
     *    <groupId>org.springframework.boot</groupId>
     *    <artifactId>spring-boot-starter-aop</artifactId>
     * </dependency>
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * Shiro生命周期处理器
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
