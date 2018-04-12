package com.example.javaee.designPattern.command;

/**
 * 录音机：接收者角色
 * @author tgl
 * @data create in 22:47 2018/4/12
 */
public class AudioPlayer {

    public void play() {
        System.out.println("------播放------");
    }

    public void rewind() {
        System.out.println("------倒带------");
    }

    public void stop() {
        System.out.println("------停止------");
    }
}
