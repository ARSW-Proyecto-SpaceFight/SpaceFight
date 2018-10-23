package edu.eci.arsw.spacefight.spacefight.services;

import edu.eci.arsw.spacefight.spacefight.game.BattleGroundGameException;
import edu.eci.arsw.spacefight.spacefight.model.Ship;
import java.util.Set;


public interface BattleGroundServices {
    
    public void registerPlayerToRoom(int roomId,Ship ship) throws BattleGroundGameException;
    
    public void removePlayerFromRoom(int roomId,Ship ship) throws BattleGroundGameException;
    
    public Set<Ship> getRegisteredPlayers(int roomId) throws BattleGroundGameException;
    
    public void createRoom(int roomId) throws BattleGroundGameException;
    
    public void removeRoom(int roomId) throws BattleGroundGameException;
    
    public int getTotalRooms() throws BattleGroundGameException;
    
    public void moveShip(int roomId, int shipId, int key);
    
    public void playerOnline(int roomId, int player);

}
