/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.Services;

import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGame;
import edu.eci.arsw.spacefight.spacefight.Game.BattleGroundGameException;
import edu.eci.arsw.spacefight.spacefight.Game.Master;
import edu.eci.arsw.spacefight.spacefight.model.Ship;
<<<<<<< HEAD
import edu.eci.arsw.spacefight.spacefight.Services.SpaceFightServices;
=======
import edu.eci.arsw.spacefight.spacefight.Services.BattleGroundServices;
>>>>>>> master
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class SpaceFightImpInMemory implements SpaceFightServices{
    private HashMap<Integer, ConcurrentSkipListSet<Ship>> roomsData;

    public SpaceFightImpInMemory() {
        roomsData = new HashMap<>();
    }
    private Master Bgsc = new Master();
    
    @Override
    public void registerPlayerToRoom(int roomId, Ship ship) throws BattleGroundGameException {
        System.out.println(ship.getId()+"-----------------------------ID-------------"+ship.getX()+","+ship.getY());
          if (!roomsData.containsKey(roomId)){
            throw new BattleGroundGameException("Room "+roomId+" not registered in the server.");
        }
        else{
            if (roomsData.get(roomId).contains(ship)){
                throw new BattleGroundGameException("Player "+ship.getId()+" already registered in room "+roomId);
            }
            else{
                  if(roomsData.get(roomId).size() == 6){
                    throw new BattleGroundGameException("In the room " + roomId + " the game has already start ");
                }
                roomsData.get(roomId).add(ship);
            }
          }
    }

    @Override
    public void removePlayerFromRoom(int roomId, Ship ship) throws BattleGroundGameException {
        if(!roomsData.containsKey(roomId)){
            throw new BattleGroundGameException("Room"+roomId+"not registered in the server");
        }
        else{                
            if(!roomsData.get(roomId).remove(ship)){                
                throw new BattleGroundGameException("Player"+ship.getId()+"not registered in room"+roomId);
            }
        }
    }

    @Override
    public Set<Ship> getRegisteredPlayers(int roomId) throws BattleGroundGameException {
        if(!roomsData.containsKey(roomId)){
            throw new BattleGroundGameException("Room"+roomId+"does not exist");
        }
        else{
            return roomsData.get(roomId);
        }
    }

    @Override
    public void createRoom(int roomId) throws BattleGroundGameException {
         if (roomsData.containsKey(roomId)){
            throw new BattleGroundGameException("Room "+roomId+" already registered in the server.");
        }else{
            roomsData.put(roomId, new ConcurrentSkipListSet<>());
        }
    }

    @Override
    public void removeRoom(int roomId) throws BattleGroundGameException {
        if (!roomsData.containsKey(roomId)){
            throw new BattleGroundGameException("Room "+roomId+" not registered in the server.");          
        }else{
            roomsData.remove(roomId);
        }
    }

    @Override
    public int getTotalRooms() throws BattleGroundGameException {
        return roomsData.size();
    }

    @Override
    public void moveShip(int roomId, int shipId, int key) {
        Ship ship = null;
        for(Ship s:roomsData.get(roomId)){
            if(s.getId() == shipId) ship = s;
        }        
        ship.move(key);
    }

    @Override
    public synchronized void playerOnline(int roomId, int player){
        for(Ship s: roomsData.get(roomId)){
            if(s.getId() == player){
                s.isOnline();
            }else if(s.notOnline()){                                      
                try {                                   
                    removePlayerFromRoom(roomId, s);
                } catch (BattleGroundGameException ex) {
                    //Logger.getLogger(BattleGroundImpInMemory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
