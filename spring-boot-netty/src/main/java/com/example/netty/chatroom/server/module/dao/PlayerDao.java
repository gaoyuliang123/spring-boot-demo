package com.example.netty.chatroom.server.module.dao;


import com.example.netty.chatroom.server.module.entity.Player;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 玩家dao
 * @author TGL
 *
 */

public interface PlayerDao {
	

	
	/**
	 * 获取玩家通过id
	 * @param playerId
	 * @return
	 */
	public Player getPlayerById(long playerId);
	
	
	/**
	 * 获取玩家通过玩家名
	 * @param playerName
	 * @return
	 */
	public Player getPlayerByName(final String playerName);
	
	
	/**
	 * 创建玩家
	 * @param player
	 * @return
	 */
	public Player createPlayer(Player player);

}
