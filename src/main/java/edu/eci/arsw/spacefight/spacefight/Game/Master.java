package edu.eci.arsw.spacefight.spacefight.Game;

import edu.eci.arsw.spacefight.spacefight.model.Ship;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface Master {
    void insertRoom(int roomid,BattleGroundGame bg) throws MasterException;
    void removeRoom(int roomid) throws MasterException;
    void registerPlayerToRoom(int roomId, Ship ship, int team) throws MasterException;
    void removePlayerFromRoom(int roomId, Ship ship, int team)throws MasterException;
    HashMap<Integer, BattleGroundGame> getRoomsMap();
}
