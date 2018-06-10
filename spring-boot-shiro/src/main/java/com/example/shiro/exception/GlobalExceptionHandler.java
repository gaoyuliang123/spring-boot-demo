package com.example.shiro.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @description: 自定义全局异常处理类
 * @author: TGL
 * @date: 2018/6/10 22:02
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorizedException(UnauthorizedException e) {
        log.error(e.getMessage());
        return "403";
    }
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorizationException.class)
    public String handleAuthorizationException(AuthorizationException e) {
        return "403";
    }
}
