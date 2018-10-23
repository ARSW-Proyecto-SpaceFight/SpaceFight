package edu.eci.arsw.spacefight.spacefight.Services;

import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGameException;
import edu.eci.arsw.spacefight.spacefight.model.Ship;
import java.util.Set;


public interface SpaceFightServices {
    
    public void registerPlayerToRoom(int roomId,Ship ship) throws BattleGroundGameException;
    
    public void removePlayerFromRoom(int roomId,Ship ship) throws BattleGroundGameException;
    
    public Set<Ship> getRegisteredPlayers(int roomId) throws BattleGroundGameException;
    
    public void createRoom(int roomId) throws BattleGroundGameException;
    
    public void removeRoom(int roomId) throws BattleGroundGameException;
    
    public int getTotalRooms() throws BattleGroundGameException;
    
    public void moveShip(int roomId, int shipId, int key);

}
