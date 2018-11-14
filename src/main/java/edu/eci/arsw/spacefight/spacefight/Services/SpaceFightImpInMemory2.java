/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.Services;

import edu.eci.arsw.spacefight.spacefight.Game.*;
import edu.eci.arsw.spacefight.spacefight.model.Ship;
import edu.eci.arsw.spacefight.spacefight.restcontrollers.SpaceFightMessageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 *
 * @author User
 */

public class SpaceFightImpInMemory2 {

    @Autowired
    SpaceFightMessageController smc;

    //@Autowired
    //Master ms;
    private MasterImp ms = new MasterImp();

    private HashMap<Integer, ConcurrentSkipListSet<Ship>> roomsData;


    public SpaceFightImpInMemory2() {
        this.roomsData = new HashMap<>();
    }

    
    //@Override
    public void registerPlayerToRoom(int roomId, Ship ship,int team) throws BattleGroundGameException {
        try {
            ms.registerPlayerToRoom(roomId,ship,team);
        } catch (MasterException e) {
            e.printStackTrace();
        }
    }

    //@Override
    public void removePlayerFromRoom(int roomId, Ship ship,int team) throws BattleGroundGameException {
        try {
            ms.removePlayerFromRoom(roomId,ship,team);
        } catch (MasterException e) {
            e.printStackTrace();
        }
    }


    //@Override
    public Set<Ship> getRegisteredPlayers(int roomId) throws BattleGroundGameException {
        if(!roomsData.containsKey(roomId)){
            throw new BattleGroundGameException("Room"+roomId+"does not exist");
        }
        else{
            return roomsData.get(roomId);
        }
    }

    //@Override
    public void createRoom(int roomId) throws BattleGroundGameException {
        try {
            ms.insertRoom(roomId,new BattleGroundImp());
        } catch (MasterException e) {
            e.printStackTrace();
        }
    }

   // @Override
    public void removeRoom(int roomId) throws BattleGroundGameException {
        try {
            ms.removeRoom(roomId);
        } catch (MasterException e) {
            e.printStackTrace();
        }
    }

    //@Override
    public int getTotalRooms() throws BattleGroundGameException {
        return ms.getRoomsMap().size();
    }

    //@Override
    public void moveShip(int roomId, String username, int key,int team) {
        try {
            ms.getShip(username, roomId, team).move(key);
        } catch (MasterException e) {
            e.printStackTrace();
        }

    }

    //@Override
    /*public synchronized void playerOnline(int roomId, int player){
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

   // @Override
    public Ship getPlayer(int roomId, int player) throws BattleGroundGameException {
        Ship playerShip = null;        
        for(Ship s: roomsData.get(roomId)){
            if(s.getId() == player){
                playerShip = s;
            }
        }
        if(playerShip != null){
            return playerShip;
        }else{
            throw new BattleGroundGameException("No existe el jugador");
        }
    }*/
}
