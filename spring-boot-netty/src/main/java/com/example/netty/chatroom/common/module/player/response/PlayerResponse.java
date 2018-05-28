package com.example.netty.chatroom.common.module.player.response;

import com.example.netty.chatroom.common.core.serializer.Serializer;

/**
 * @description:
 * @author: TGL
 * @date: 2018/5/28 21:49
 */
public class PlayerResponse extends Serializer {
    /**
     * 玩家Id
     */
    private Long playerId;
    /**
     * 玩家姓名
     */
    private String playerName;
    /**
     * 玩家等级
     */
    private Integer level;
    /**
     * 经验值
     */
    private Integer exp;

    @Override
    protected void read() {
        this.playerId = readLong();
        this.playerName = readString();
        this.level = readInt();
        this.exp =  readInt();
    }

    @Override
    protected void write() {
        super.writeLong(this.playerId);
        super.writeString(this.playerName);
        super.writeInt(this.level);
        super.writeInt(this.exp);
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }
}
