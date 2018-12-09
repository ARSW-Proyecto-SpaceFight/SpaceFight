package edu.eci.arsw.spacefight.spacefight.Game;

import edu.eci.arsw.spacefight.spacefight.model.Flag;
import edu.eci.arsw.spacefight.spacefight.model.LifeOrb;
import edu.eci.arsw.spacefight.spacefight.model.Meteorite;
import edu.eci.arsw.spacefight.spacefight.model.Ship;
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
    void removeMeteoritesToRoom(int roomid, Meteorite meteorite)throws MasterException;
    void removeOneMeteorite (int roomid, Meteorite meteorite)throws MasterException;
    ArrayList<Meteorite> getMeteoritesFromRoom(int roomid) throws MasterException;
    int getNextTeam(int roomId);
    void insertLifesOrbToRoom(int roomid, LifeOrb lifeOrb)throws MasterException;
    void removeLifesOrbToRoom(int roomid, LifeOrb lifeOrb)throws MasterException;
    void removeOneLifeOrb (int roomid, LifeOrb lifeOrb)throws MasterException;
    ArrayList<LifeOrb> getLifeOrbFromRoom(int roomid) throws MasterException;
    ArrayList<LifeOrb> getFlags() throws BattleGroundGameException;
    Flag getFlagTeam1() throws BattleGroundGameException;
    Flag getFlagTeam2() throws BattleGroundGameException;


}
