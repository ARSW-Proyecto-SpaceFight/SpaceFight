

package edu.eci.arsw.spacefight.spacefight.Game;

import edu.eci.arsw.spacefight.spacefight.model.ModelException;
import edu.eci.arsw.spacefight.spacefight.model.Ship;
import edu.eci.arsw.spacefight.spacefight.restcontrollers.SpaceFightMessageController;

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

    @Override
    public boolean containsRoom(int roomid) {
     if(rooms.containsKey(roomid)){
         return true;
     }else{
         return false;
     }
    }
}