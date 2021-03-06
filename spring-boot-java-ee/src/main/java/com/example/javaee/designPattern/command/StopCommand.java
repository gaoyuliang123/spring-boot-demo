package com.example.javaee.designPattern.command;

/**
 * 具体命令角色类
 * @author tgl
 * @data create in 22:53 2018/4/12
 */
public class StopCommand implements Command {
    private AudioPlayer audioPlayer;

    public StopCommand(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void exeution() {
        audioPlayer.stop();
    }
}
