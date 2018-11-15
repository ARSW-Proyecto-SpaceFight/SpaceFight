package edu.eci.arsw.spacefight.spacefight.Services;

import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGameException;
import edu.eci.arsw.spacefight.spacefight.Game.MasterException;
import edu.eci.arsw.spacefight.spacefight.model.Ship;
import java.util.Set;


public interface SpaceFightServices {
    
    public void registerPlayerToRoom(int roomId,Ship ship,int team) throws BattleGroundGameException;
    
    public void removePlayerFromRoom(int roomId,Ship ship,int team) throws BattleGroundGameException;
    
    public Set<Ship> getRegisteredPlayers(int roomId) throws BattleGroundGameException;
    
    public void createRoom(int roomId) throws BattleGroundGameException;
    
    public void removeRoom(int roomId) throws BattleGroundGameException;
    
    public int getTotalRooms() throws BattleGroundGameException;
    
    public void moveShip(int roomId, String username, int key,int team) throws MasterException ;
    
    public void playerOnline(int roomId, String username)throws BattleGroundGameException;
    
    public Ship getPlayer(int roomId, String username) throws BattleGroundGameException;
    
    public Set<Integer> getRooms();

}
