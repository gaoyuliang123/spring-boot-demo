package com.example.netty.chatroom.common.exception;

/**
 * @description: 错误码携带异常
 * @author: TGL
 * @date: 2018/5/28 22:19
 */
public class ErrorCodeException extends RuntimeException {

    /**
     * 错误代码
     */
    private final int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public ErrorCodeException(int errorCode) {
        this.errorCode = errorCode;
    }
}
