

package edu.eci.arsw.spacefight.spacefight.Game;

import edu.eci.arsw.spacefight.spacefight.model.*;
import edu.eci.arsw.spacefight.spacefight.restcontrollers.SpaceFightMessageController;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 *
 * @author ARSW Proyecto
 */
@Service
public class MasterImp implements Master{

    private HashMap<Integer, BattleGroundGame> rooms = new HashMap<>();

    @Autowired
    SpaceFightMessageController smc;
    
    public HashMap<Integer, BattleGroundGame> getRoomsMap() {
        return rooms;
    }

    /**
     * This method inserts a given room to the map container
     * @param roomid Id of the room to be indexed
     * @param bg Object of Room
     * @throws MasterException
     */
    public void insertRoom(int roomid,BattleGroundGame bg) throws MasterException {
        if(rooms.containsKey(roomid)) {
            throw  new MasterException("Room "+roomid+" already registered in the server.");
        }
        else {
            rooms.put(roomid, bg);
        }
    }

    /**
     * This method removes an specific room indexed in the map
     * @param roomid ID of the room to be removed
     * @throws MasterException
     */
    public void removeRoom(int roomid) throws MasterException{
        if(!rooms.containsKey(roomid)) {
            throw  new MasterException("Room "+roomid+" does not exist.");
        }
        else {
            rooms.remove(roomid);
        }
    }

    /**
     * This method registers a player to a given room
     * @param roomId ID of the room
     * @param ship Object of the ship to be registered
     * @param team Team of the Ship
     * @throws MasterException
     */
    public void registerPlayerToRoom(int roomId, Ship ship, int team) throws MasterException{
        if(!rooms.containsKey(roomId)) {
            throw  new MasterException("Room "+roomId+" does not exist.");
        }
        else {
            try {
                rooms.get(roomId).insertPlayerToTeam(ship, team);
            } catch (BattleGroundGameException e) {
                throw new MasterException("Could not Insert player to Team");
            }
        }


    }

    /**
     * This method Removes a player from a given room
     * @param roomId ID of the room
     * @param ship Object of the ship to be removed
     * @param team Team of the ship to be removed
     * @throws MasterException
     */
    public void removePlayerFromRoom(int roomId, Ship ship, int team)throws MasterException{
        if(!rooms.containsKey(roomId)) {
            throw  new MasterException("Room "+roomId+" does not exist.");
        }
        else {
            try {
                if(!smc.conectado(ship.getUsername(), roomId)){
                    rooms.get(roomId).removePlayerFromTeam(ship, team);
                }
            } catch (BattleGroundGameException e) {
                throw new MasterException("Could not remove player to Team");
            }
        }
    }

    /**
     * This method gets an specefic ship from a room and team
     * @param username Username of the ship
     * @param roomId ID of the room
     * @param team Number of the team
     * @return Returns a Ship Object
     * @throws MasterException
     */
    public Ship getShip(String username,int roomId,int team)throws MasterException{
        if(!rooms.containsKey(roomId)){
            throw  new MasterException("Room "+roomId+" does not exist.");
        }
        else{
            try {
                return rooms.get(roomId).getTeam(team).getShip(username);
            } catch (BattleGroundGameException e) {
                throw  new MasterException(e.getMessage());
            } catch (ModelException e) {
                throw  new MasterException(e.getMessage());
            }
        }

    }

    /**
     * This method gets an specific room
     * @param roomId ID Index of the room
     * @return The object of the Room
     * @throws MasterException
     */
    public BattleGroundGame getRoom(int roomId)throws MasterException{
        if(!rooms.containsKey(roomId)){
            throw  new MasterException("Room "+roomId+" does not exist.");
        }
        else{
            return rooms.get(roomId);
        }

    }
    
    /**
     * relation between shoots, roms and player
     * @param roomId number of created room
     * @param username player name to identify a ship
     * @throws MasterException 
     */
    public void shoot(int roomId,String username)throws MasterException{
        try {
            BattleGroundGame bg=getRoom(roomId);
            bg.shoot(username);
        } catch (MasterException e) {
            throw  new MasterException(e.getMessage());
        } catch (BattleGroundGameException e) {
            throw  new MasterException(e.getMessage());
        }

    }

    /**
     * verify if a room exist
     * @param roomid number that the person give to the room
     * @return boolean
     */
    @Override
    public boolean containsRoom(int roomid) {
     if(rooms.containsKey(roomid)){
         return true;
     }else{
         return false;
     }
    }

    
    /**
     * insert the meteorites that the battleground creates to the room
     * @param roomid number of the room
     * @param meteorite item
     * @throws MasterException 
     */
    @Override
    public void insertMeteoritesToRoom(int roomid, Meteorite meteorite) throws MasterException {
        if(!rooms.containsKey(roomid)){
            throw  new MasterException("Room "+roomid+" does not exist.");
        }
        else{
            try {
                rooms.get(roomid).insertItemInBatlleGround(meteorite);
            } catch (BattleGroundGameException e) {
                throw new MasterException("Could not Insert meteorite to background");
            }
        }
    }


    /**
     * get the list of meteorites on room
     * @param roomid number of the room
     * @return the list of meteorites
     * @throws MasterException 
     */
    @Override
    public ArrayList<Meteorite> getMeteoritesFromRoom(int roomid) throws MasterException {
        if(!rooms.containsKey(roomid)){
            throw  new MasterException("Room "+roomid+" does not exist.");
        }
        else{
            try {
                return rooms.get(roomid).getAllMeteorites();
            } catch (BattleGroundGameException e) {
                throw new MasterException("The background not contain any meteorite");
            }
        }
    }

    /**
     * return the number of the team on the room that have less players
     * @param roomId number of the room
     * @return the number of the team
     */
    @Override
    public int getNextTeam(int roomId) {
        int cant1 = rooms.get(roomId).getAllShipsFromTeam(1).size();
        int cant2 = rooms.get(roomId).getAllShipsFromTeam(2).size();
        return (cant1>cant2)?2:1;
    }

    /**
     * allows insert life orbs on the room
     * @param roomid nuber of room
     * @param lifeOrb item
     * @throws MasterException 
     */
    @Override
    public void insertLifesOrbToRoom(int roomid, LifeOrb lifeOrb) throws MasterException {
        if(!rooms.containsKey(roomid)){
            throw  new MasterException("Room "+roomid+" does not exist.");
        }
        else{
            try {
                rooms.get(roomid).insertItemInBatlleGround(lifeOrb);
            } catch (BattleGroundGameException e) {
                throw new MasterException("Could not Insert lifeorb to background");
            }
        }
    }



    /**
     * get the list of life orbs on room
     * @param roomid number of room
     * @return list of lifeorbs
     * @throws MasterException 
     */
    @Override
    public ArrayList<LifeOrb> getLifeOrbFromRoom(int roomid) throws MasterException {
        if(!rooms.containsKey(roomid)){
            throw  new MasterException("Room "+roomid+" does not exist.");
        }
        else{
                return rooms.get(roomid).getAllLifeOrbs();

        }
    }

    /**
     * get flags of rooms
     * @param roomId number of the room
     * @return list of flags
     */
    @Override
    public ArrayList<Flag> getFlags(int roomId) {
        return rooms.get(roomId).getFlags();
    }

    /**
     * get team bases of room
     * @param roomId number of room
     * @return list of bases
     */
    @Override
    public ArrayList<Base> getBases(int roomId) {
        return rooms.get(roomId).getBases();
    }


}
