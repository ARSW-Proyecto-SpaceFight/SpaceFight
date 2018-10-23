/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.services;

import edu.eci.arsw.spacefight.spacefight.game.BattleGroundGame;
import edu.eci.arsw.spacefight.spacefight.game.BattleGroundGameException;
import edu.eci.arsw.spacefight.spacefight.model.Ship;
import edu.eci.arsw.spacefight.spacefight.services.BattleGroundServices;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class BattleGroundImpInMemory implements BattleGroundServices{
    private HashMap<Integer, ConcurrentSkipListSet<Ship>> roomsData;

    public BattleGroundImpInMemory() {
        roomsData = new HashMap<>();
    }

    
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
            throw new BattleGroundGameException("Room"+roomId+"not regostered in the server");
        }
        else{
            if(!roomsData.get(roomId).contains(ship)){
                throw new BattleGroundGameException("Player"+ship.getId()+"not registered in room"+roomId);
            }
            else{
                roomsData.get(roomId).remove(ship);
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
}
