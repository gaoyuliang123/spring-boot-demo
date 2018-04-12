package com.example.javaee.designPattern.command;

/**
 * 键盘：请求者角色
 * @author tgl
 * @data create in 22:55 2018/4/12
 */
public class Keypad {
    private Command playCommand;
    private Command rewindCommand;
    private Command stopCommand;

    public Keypad() {
    }

    public Keypad(Command playCommand, Command rewindCommand, Command stopCommand) {
        this.playCommand = playCommand;
        this.rewindCommand = rewindCommand;
        this.stopCommand = stopCommand;
    }

    public void play() {
        playCommand.exeution();
    }

    public void rewind() {
        rewindCommand.exeution();
    }

    public void stop() {
        stopCommand.exeution();
    }

    public void setPlayCommand(Command playCommand) {
        this.playCommand = playCommand;
    }

    public void setRewindCommand(Command rewindCommand) {
        this.rewindCommand = rewindCommand;
    }

    public void setStopCommand(Command stopCommand) {
        this.stopCommand = stopCommand;
    }
}
