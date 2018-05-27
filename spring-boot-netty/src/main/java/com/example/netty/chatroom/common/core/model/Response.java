package com.example.netty.chatroom.common.core.model;

/**
 * @description:
 * @author: TGL
 * @date: 2018/5/27 16:20
 */
public class Response {
    /**
     * 模块号
     */
    private Short module;
    /**
     * 命令号
     */
    private Short cmd;
    /**
     * 结果码
     */
    private int stateCode = ResultCode.SUCCESS;
    /**
     * 数据
     */
    private byte[] data;

    public Response(Request message) {
        this.module = message.getModule();
        this.cmd = message.getCmd();
    }

    public Response(Short module, Short cmd, byte[] data) {
        this.module = module;
        this.cmd = cmd;
        this.data = data;
    }

    public Short getModule() {
        return module;
    }

    public void setModule(Short module) {
        this.module = module;
    }

    public Short getCmd() {
        return cmd;
    }

    public void setCmd(Short cmd) {
        this.cmd = cmd;
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
