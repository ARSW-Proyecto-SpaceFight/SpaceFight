package edu.eci.arsw.spacefight.spacefight.Game;

import edu.eci.arsw.spacefight.spacefight.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public interface Master {
    void insertRoom(int roomid,BattleGroundGame bg) throws MasterException;
    void removeRoom(int roomid) throws MasterException;
    void registerPlayerToRoom(int roomId, Ship ship, int team) throws MasterException;
    void removePlayerFromRoom(int roomId, Ship ship, int team)throws MasterException;
    HashMap<Integer, BattleGroundGame> getRoomsMap();
    public Ship getShip(String username,int roomId,int team)throws MasterException;
    public BattleGroundGame getRoom(int roomId)throws MasterException;
    boolean containsRoom(int roomid);
    void shoot(int roomId,String username)throws MasterException;
    void insertMeteoritesToRoom(int roomid, Meteorite meteorite)throws MasterException;
    ArrayList<Meteorite> getMeteoritesFromRoom(int roomid) throws MasterException;
    int getNextTeam(int roomId);
    void insertLifesOrbToRoom(int roomid, LifeOrb lifeOrb)throws MasterException;
    ArrayList<LifeOrb> getLifeOrbFromRoom(int roomid) throws MasterException;


    ArrayList<Flag> getFlags(int roomId);

    ArrayList<Base> getBases(int roomId);
}
