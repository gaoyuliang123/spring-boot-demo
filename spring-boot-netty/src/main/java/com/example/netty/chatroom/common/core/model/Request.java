package com.example.netty.chatroom.common.core.model;

/**
 * @description: 消息对象
 * @author: TGL
 * @date: 2018/5/27 10:36
 */
public class Request {

    /**
     * 模块号
     */
    private short module;
    /**
     * 命令号
     */
    private short cmd;
    /**
     * 数据
     */
    private byte[] data;

    public Request() {
    }

    public Request(short module, short cmd, byte[] data) {
        this.module = module;
        this.cmd = cmd;
        this.data = data;
    }

    public short getModule() {
        return module;
    }

    public void setModule(short module) {
        this.module = module;
    }

    public short getCmd() {
        return cmd;
    }

    public void setCmd(short cmd) {
        this.cmd = cmd;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
