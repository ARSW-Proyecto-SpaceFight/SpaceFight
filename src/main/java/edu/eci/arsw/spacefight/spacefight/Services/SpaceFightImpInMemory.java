/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.Services;

import edu.eci.arsw.spacefight.spacefight.Game.*;
import edu.eci.arsw.spacefight.spacefight.model.*;
import edu.eci.arsw.spacefight.spacefight.restcontrollers.SpaceFightMessageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 *
 * @author User
 */
@Service
public class SpaceFightImpInMemory implements SpaceFightServices{
    

    @Autowired
    private Master ms;
    @Autowired
    SpaceFightMessageController smc;
    //private MasterImp ms = new MasterImp();

    private HashMap<Integer, ConcurrentSkipListSet<Ship>> roomsData = new HashMap<>();


    public SpaceFightImpInMemory() {        
    }

    /**
     *This method register a Ship to an specific room and a specific team
     * @param roomId ID of the room
     * @param ship Object of the ship to register in the room
     * @param team ship's team number
     * @throws BattleGroundGameException
     */
    @Override
    public void registerPlayerToRoom(int roomId, Ship ship,int team) throws BattleGroundGameException {
        try {
            ms.registerPlayerToRoom(roomId,ship,team);
        } catch (MasterException e) {
            e.printStackTrace();
        }
    }

    /**
     *this method removes a ship from a given room and team
     * @param roomId ID of the room
     * @param ship Object of the Ship to remove
     * @param team Number of the ship's team
     * @throws BattleGroundGameException
     */
    @Override
    public void removePlayerFromRoom(int roomId, Ship ship,int team) throws BattleGroundGameException {
        try {
            ms.removePlayerFromRoom(roomId,ship,team);
        } catch (MasterException e) {
            e.printStackTrace();
        }
    }

    /**
     *this method gets all the registered players from a given room in a set
     * @param roomId ID of the room
     * @return a Set of all the Ships of the given room
     * @throws BattleGroundGameException
     */
    @Override
    public Set<Ship> getRegisteredPlayers(int roomId) throws BattleGroundGameException {
        if(!ms.containsRoom(roomId)){
            throw new BattleGroundGameException("Room"+roomId+"does not exist");
        }
        else{
            try {
                return new HashSet<Ship>(ms.getRoom(roomId).getAllShips());
            } catch (MasterException e) {
                throw new BattleGroundGameException(e.getMessage());
            }
        }
    }

    /**
     * this method creates a new room idexed with the given id
     * @param roomId ID of the room
     * @throws BattleGroundGameException
     */
    @Override
    public void createRoom(int roomId) throws BattleGroundGameException {
        try {

            BattleGroundGame bg= new BattleGroundImp(smc);
            bg.setId(roomId);
            ms.insertRoom(roomId,bg);
        } catch (MasterException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method removes a room who matches the given id
     * @param roomId ID of the room to remove
     * @throws BattleGroundGameException
     */
    @Override
    public void removeRoom(int roomId) throws BattleGroundGameException {
        try {
            ms.removeRoom(roomId);
        } catch (MasterException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns the amount of total active rooms
     * @return an integer of the total active rooms
     * @throws BattleGroundGameException
     */
    @Override
    public int getTotalRooms() throws BattleGroundGameException {
        return ms.getRoomsMap().size();
    }

    /**
     * This method moves the given ship from a given room
     * @param roomId ID of the room where the ship is
     * @param username Username of the ship's Owner
     * @param key The key pressed
     * @param team The team of the ship
     * @throws MasterException
     */
    @Override
    public void moveShip(int roomId, String username, int key,int team) throws MasterException {
        try {
            //ms.getShip(username, roomId, team).move(key);
            ms.getRoom(roomId).moveShip(username,key);
        } catch (MasterException e) {
            throw new MasterException(e.getMessage());
        }

    }

    /**
     * This method set as online the given ship and remove offline ships of a room
     * @param roomId Id of the room to check
     * @param username Username of the ship to check
     * @throws BattleGroundGameException
     */
    @Override
    public synchronized void playerOnline(int roomId, String username) throws BattleGroundGameException {
        try {
            BattleGroundGame room =ms.getRoom(roomId);
            ArrayList<Ship> ships= room.getAllShips();
            for(int i=0; i<ships.size();i++){
                if(ships.get(i).getUsername().equals(username)){
                    ships.get(i).isOnline();
                }
                else if(ships.get(i).notOnline()){
                    try {
                        ms.removePlayerFromRoom(roomId,ships.get(i),room.findShipTeam(ships.get(i)));
                    } catch (BattleGroundGameException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (MasterException e) {
            //throw new BattleGroundGameException(e.getMessage());
        }
    }

    /**
     * This method gets an specific ship from a given room
     * @param roomId ID of the room
     * @param username username of the ship to be get
     * @return An Ship object fetched from the given room
     * @throws BattleGroundGameException
     */
    @Override
    public Ship getPlayer(int roomId, String username) throws BattleGroundGameException {
        try {
            BattleGroundGame room = ms.getRoom(roomId);
            return room.getShip(username);
        } catch (MasterException e) {
            throw new BattleGroundGameException(e.getMessage());
        }
    }
    public void shoot(int roomId,String username)throws BattleGroundGameException{
        try {
            ms.shoot(roomId,username);
        } catch (MasterException e) {
            throw new BattleGroundGameException(e.getMessage());
        }
    }

    @Override
    public Set<Integer> getRooms() {
        return ms.getRoomsMap().keySet();
    }

    @Override
    public ArrayList<Meteorite> getMeteoriteFromRoom(int roomId) throws BattleGroundGameException, MasterException {
        return ms.getMeteoritesFromRoom(roomId);
    }
    


    @Override
    public int getNextTeam(int roomId) {
        return ms.getNextTeam(roomId);
    }

    @Override
    public ArrayList<LifeOrb> getLifeOrbFromRoom(int roomId) throws BattleGroundGameException, MasterException {
        return ms.getLifeOrbFromRoom(roomId);
    }

    public ArrayList<Flag> getFlagsFromRoom(int roomId) {
        return ms.getFlags(roomId);
    }

    @Override
    public ArrayList<Base> getBasesFromRoom(int roomId) {
        return ms.getBases(roomId);
    }


}
