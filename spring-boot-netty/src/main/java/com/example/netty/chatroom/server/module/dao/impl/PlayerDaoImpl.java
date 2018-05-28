package com.example.netty.chatroom.server.module.dao.impl;

import com.example.netty.chatroom.server.module.dao.PlayerDao;
import com.example.netty.chatroom.server.module.entity.Player;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: TGL
 * @date: 2018/5/28 21:40
 */
@Repository
public class PlayerDaoImpl implements PlayerDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    /**
     * 获取玩家通过id
     * @param playerId
     * @return
     */
    @Override
    public Player getPlayerById(long playerId){
        return hibernateTemplate.get(Player.class, playerId);
    }


    /**
     * 获取玩家通过玩家名
     * @param playerName
     * @return
     */
    @Override
    public Player getPlayerByName(final String playerName){

        return hibernateTemplate.execute(new HibernateCallback<Player>() {

            @Override
            public Player doInHibernate(Session session)throws HibernateException {
                SQLQuery query = session.createSQLQuery("SELECT * FROM player where playerName = ?");
                query.setString(1, playerName);
                query.addEntity(Player.class);

                @SuppressWarnings("unchecked")
                List<Player> list = query.list();
                if(list==null || list.isEmpty()){
                    return null;
                }
                return list.get(0);
            }
        });
    }


    /**
     * 创建玩家
     * @param player
     * @return
     */
    @Override
    public Player createPlayer(Player player){
        long playerId = (Long) hibernateTemplate.save(player);
        player.setPlayerId(playerId);
        return player;
    }
}
