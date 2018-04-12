package com.example.javaee.designPattern.command;

/**
 * 客户端
 * @author tgl
 * @data create in 23:00 2018/4/12
 */
public class Test {
    public static void main(String[] args){
        // 创建接收者
        AudioPlayer audioPlayer = new AudioPlayer();

        // 命令
        Command playCommand = new PlayCommand(audioPlayer);
        Command rewindCommand = new RewindCommand(audioPlayer);
        Command stopCommand = new StopCommand(audioPlayer);

        // 请求
        Keypad keypad = new Keypad(playCommand, rewindCommand, stopCommand);
        keypad.play();
        keypad.rewind();
        keypad.stop();
    }


}
